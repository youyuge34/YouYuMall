package com.example.yousheng.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * @function 对Configurator的进一步封装
 * Created by yousheng on 17/7/14.
 */

public class Latte {
    public static final Configurator init(Context context){
        if(context!=null){
           getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
            getLatteConfigs().put(ConfigKeys.LOADER_DELAYED,500l);
        }

        return Configurator.getInstance();
    }

    public static final HashMap<Object,Object> getLatteConfigs(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static final <T> T getLatteConfiguration(Object key){
        return Configurator.getInstance().getConfiguration(key);
    }

    //必须用Configrator里的getConfiguration，它会做预处理
    public static final Context getAppContext(){
        return getLatteConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static final Handler getHandler() {
        return getLatteConfiguration(ConfigKeys.HANDLER);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
}
