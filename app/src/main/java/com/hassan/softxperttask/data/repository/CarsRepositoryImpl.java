package com.hassan.softxperttask.data.repository;

import com.hassan.softxperttask.data.datasource.CarsAPI;
import com.hassan.softxperttask.data.entity.CarsResponseEntity;
import com.hassan.softxperttask.presentation.model.CarModel;
import com.hassan.softxperttask.presentation.repository.CarsRepository;

import java.util.List;

import io.reactivex.Single;

public class CarsRepositoryImpl implements CarsRepository {

    private final CarsAPI carsAPI;
    public CarsRepositoryImpl(CarsAPI carsAPI){
        this.carsAPI = carsAPI;
    }


    @Override
    public Single<List<CarModel>> getCars(int page) {
        return carsAPI.getCars(page).map(CarsResponseEntity::getData);
    }
}
