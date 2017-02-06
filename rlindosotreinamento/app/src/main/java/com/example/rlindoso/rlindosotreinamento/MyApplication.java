package com.example.rlindoso.rlindosotreinamento;

import android.app.Application;

/**
 * Created by rlindoso on 06/02/2017.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getContext() {
        return instance;
    }
}
