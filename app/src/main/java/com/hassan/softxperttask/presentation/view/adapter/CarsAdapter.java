package com.hassan.softxperttask.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hassan.softxperttask.R;
import com.hassan.softxperttask.presentation.model.CarModel;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

    public final ArrayList<CarModel> mCars;

    public CarsAdapter(ArrayList<CarModel> mCars) {
        this.mCars = mCars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.bind(mCars.get(position));
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CarModel carModel){
            Glide.with(this.itemView)
                    .load(carModel.getImageUrl())
                    .placeholder(R.color.white)
                    .into((ImageView) itemView.findViewById(R.id.imgCarImage));
        }
    }
}
