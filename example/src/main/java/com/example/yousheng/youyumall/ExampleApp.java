package com.example.yousheng.youyumall;

import android.app.Application;

import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.net.interceptors.DebugInterceptor;
import com.example.yousheng.latte.net.interceptors.LoggingInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

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
                .withApiHost("http://59.110.69.182/api/")
//                .withLoaderDelayed(1000)
                .withJavascriptInterface("latte")
                .withWeChatAppId("")
                .withWeChatAppSecret("")
//                .withLoaderDelayed(3000)
                .withInterceptor(new LoggingInterceptor())
                .withInterceptor(new DebugInterceptor("59.110.69.182",R.raw.test1))
                .configure();


    }


}
