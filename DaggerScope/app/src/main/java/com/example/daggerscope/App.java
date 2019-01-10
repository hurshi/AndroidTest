package com.example.daggerscope;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        appComponent = DaggerAppComponent.builder().build();
    }
}
