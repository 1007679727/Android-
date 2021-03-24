package com.example.chat.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.chat.data.dao.DaoMaster;
import com.example.chat.data.dao.DaoSession;


public class DbManager {
    private static DbManager mDbUtil;
    private static DaoSession daoSession;

    public static DbManager getInstance() {
        if (mDbUtil == null) {
            mDbUtil = new DbManager();
        }
        return mDbUtil;
    }

    public void init(Context context) {
        DbOpenHelper helper = new DbOpenHelper(context, "patrol.dba" , null);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqlDB);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoInstant(Context context) {
        if(daoSession==null){
            init(context);
        }
        return daoSession;
    }

    public void deleSQL(Context context){
        DbOpenHelper helper = new DbOpenHelper(context, "patrol.db" , null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster.dropAllTables(daoMaster.getDatabase(),true);
        DaoMaster.createAllTables(daoMaster.getDatabase(),true);
    }
}
