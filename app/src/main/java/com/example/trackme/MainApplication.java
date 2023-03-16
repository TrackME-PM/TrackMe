package com.example.trackme;

import android.app.Application;

public class MainApplication extends Application {
    public static TrackMEApiManager TrackMEApiManager;




    @Override
    public void onCreate() {
        super.onCreate();
        TrackMEApiManager = TrackMEApiManager.getInstance();


    }
}
