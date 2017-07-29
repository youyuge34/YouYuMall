package com.example.yousheng.youyumall;

import android.app.Application;

import com.example.yousheng.ec.database.DatabaseManager;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.net.interceptors.LoggingInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by yousheng on 17/7/15.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //初始化配置项目
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("https://sh1a.qingstor.com/android-test/app/mall/api/")
//                .withLoaderDelayed(1000)
                .withJavascriptInterface("latte")
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withLoaderDelayed(500)
                .withInterceptor(new LoggingInterceptor())
//                .withInterceptor(new DebugInterceptor("59.110.69.182",R.raw.test1))
                .configure();

        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());

        //初始化greenDao
        DatabaseManager.getInstance().init(this);

        //初始化fb的sttho
//        initStetho();
    }

//    private void initStetho() {
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
//    }
}

