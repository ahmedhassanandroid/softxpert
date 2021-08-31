package com.hassan.softxperttask.presentation;

import android.app.Application;

import com.hassan.softxperttask.presentation.di.AppModuleKt;


class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppModuleKt.start(this);
    }
}
