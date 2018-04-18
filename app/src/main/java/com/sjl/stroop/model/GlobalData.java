package com.sjl.stroop.model;

import com.sjl.stroop.App;
import com.sjl.stroop.db.DbHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * GlobalData
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class GlobalData {
    private static GlobalData globalData = new GlobalData();
    private static ExecutorService executorService;

    private static DbHelper dbHelper;

    private GlobalData() {

    }

    public static GlobalData getInstance() {
        return globalData;
    }

    public void init() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //耗时操作
                dbHelper = DbHelper.getInstance(App.getApp());
            }
        });
    }

    public static DbHelper getDbHelper() {
        return dbHelper;
    }
}
