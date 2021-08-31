package com.hassan.softxperttask.presentation.repository;

import com.hassan.softxperttask.data.entity.CarsResponseEntity;
import com.hassan.softxperttask.presentation.model.CarModel;

import java.util.List;

import io.reactivex.Single;

public interface CarsRepository {
    public Single<List<CarModel>> getCars(int page);
}
