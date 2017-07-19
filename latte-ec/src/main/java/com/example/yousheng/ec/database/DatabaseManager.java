package com.example.yousheng.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by yousheng on 17/7/19.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mUserDao = null;

    private DatabaseManager() {
    }

    //内部静态类单例模式
    private final static class Holder {
        private final static DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public final DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "youyumall_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserDao = mDaoSession.getUserProfileDao();
    }

    public final DaoSession getDao() {
        return mDaoSession;
    }

    public final UserProfileDao getUserDao() {
        return mUserDao;
    }
}
