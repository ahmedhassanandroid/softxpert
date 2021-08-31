package com.hassan.softxperttask.data.entity;

import com.hassan.softxperttask.presentation.model.CarModel;

import java.util.List;

public class CarsResponseEntity {
    private int status;
    private List<CarModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarModel> getData() {
        return data;
    }

    public void setData(List<CarModel> data) {
        this.data = data;
    }
}
