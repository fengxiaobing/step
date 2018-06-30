package com.example.step;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.step.data.db.greendao.DaoMaster;
import com.example.step.data.db.greendao.DaoSession;
import com.example.step.data.utils.DBOpenHelper;


/**
 * Created by RF
 * on 2018/1/8.
 */

public class App extends Application {
    private static Context mContext;

    private DBOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = (App) getApplicationContext();
        setDatabase();
    }
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DBOpenHelper(this, "notes-db", null,DaoMaster.SCHEMA_VERSION);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);

        mDaoSession = mDaoMaster.newSession();
        Log.e("TAG","");
    }
    public DaoSession getDaoSession() {
        Log.e("TAG","");
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }


    public static App getContext() {
        return (App) App.mContext;
    }
}
