package com.findx.wifiindoorpositioning.wifiindoorpositioning;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by sankalp on 13/10/19.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
