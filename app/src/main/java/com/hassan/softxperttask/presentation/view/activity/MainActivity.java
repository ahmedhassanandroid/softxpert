package com.hassan.softxperttask.presentation.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hassan.softxperttask.R;
import com.hassan.softxperttask.presentation.view.adapter.CarsAdapter;
import com.hassan.softxperttask.presentation.viewmodel.CarsViewModel;

import org.koin.java.KoinJavaComponent;

import java.util.ArrayList;

import kotlin.Lazy;

public class MainActivity extends AppCompatActivity {

    private CarsAdapter adapter;
    private Lazy<CarsViewModel> viewModel = KoinJavaComponent.inject(CarsViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        observeViewModel();
        fetchData();
    }

    private void fetchData() {
        viewModel.getValue().resetPagination();
        viewModel.getValue().getCars();
    }

    private void observeViewModel() {
        viewModel.getValue().cars.observe(this, carModels -> {
            int start = adapter.mCars.size();
            adapter.mCars.addAll(carModels);
            adapter.notifyItemRangeInserted(start, carModels.size());
        });
    }

    private void setupRecyclerView() {
        adapter = new CarsAdapter(new ArrayList<>());
        RecyclerView rvCars = findViewById(R.id.rvCars);
        rvCars.setAdapter(adapter);
        rvCars.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rvCars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = ((LinearLayoutManager)rvCars.getLayoutManager()).findLastVisibleItemPosition();
                if(!viewModel.getValue().isLoading()
                        && !viewModel.getValue().isEndOfData()
                        && lastPosition == adapter.mCars.size() - 1)
                    viewModel.getValue().getCars();
            }
        });

        SwipeRefreshLayout srl = findViewById(R.id.srlCarsMainView);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.mCars.clear();
                adapter.notifyDataSetChanged();
                viewModel.getValue().resetPagination();
                viewModel.getValue().getCars();
            }
        });
    }
}