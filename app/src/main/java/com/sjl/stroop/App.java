package com.sjl.stroop;

import android.app.Application;

import com.sjl.stroop.model.GlobalData;

/**
 * App
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class App extends Application {
    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        GlobalData.getInstance().init();
    }
}
