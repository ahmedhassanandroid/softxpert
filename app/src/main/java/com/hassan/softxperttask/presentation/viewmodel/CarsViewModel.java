package com.hassan.softxperttask.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hassan.softxperttask.presentation.model.CarModel;
import com.hassan.softxperttask.presentation.repository.CarsRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CarsViewModel extends ViewModel {
    private final CarsRepository carsRepository;
    private boolean isEndOfData = false;
    private int page = 1;
    public final MutableLiveData<List<CarModel>> cars = new MutableLiveData<>();
    public final MutableLiveData<Boolean> error = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public CarsViewModel(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public void getCars(){
        loading.postValue(true);
        carsRepository.getCars(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(() -> loading.postValue(false))
                .subscribe(new SingleObserver<List<CarModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull List<CarModel> cars) {
                        CarsViewModel.this.cars.postValue(cars);
                        page++;
                        if (cars.isEmpty())
                            isEndOfData = true;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.postValue(true);
                    }
                });
    }

    public boolean isLoading(){
        return loading.getValue();
    }

    public void resetPagination(){
        isEndOfData = false;
        page = 1;
    }

    public boolean isEndOfData() {
        return isEndOfData;
    }
}
