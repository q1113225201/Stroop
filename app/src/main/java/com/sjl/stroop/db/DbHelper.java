package com.sjl.stroop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sjl.stroop.dao.DaoMaster;
import com.sjl.stroop.dao.DaoSession;

/**
 * DbHelper
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class DbHelper {
    private static final String TAG = "DbHelper";
    private static final String DB_NAME = "stroop";
    private static DbHelper dbHelper;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    public static DbHelper getInstance(Context context){
        synchronized (TAG){
            if (dbHelper == null) {
                dbHelper = new DbHelper(context);
            }
            return dbHelper;
        }
    }
    private DbHelper(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }
}
