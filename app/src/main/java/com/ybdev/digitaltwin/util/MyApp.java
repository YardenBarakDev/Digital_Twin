package com.ybdev.digitaltwin.util;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySP.initHelper(this);
    }


}
