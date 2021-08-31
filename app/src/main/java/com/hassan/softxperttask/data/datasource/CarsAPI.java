package com.hassan.softxperttask.data.datasource;

import com.hassan.softxperttask.data.entity.CarsResponseEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarsAPI {
    @GET("cars")
    public Single<CarsResponseEntity> getCars(@Query("page") int page);
}
