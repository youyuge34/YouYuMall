package com.example.yousheng.latte.app;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.WeakHashMap;

/**
 * Created by yousheng on 17/7/14.
 */

public class Configurator {

    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();


    public Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //线程安全的单例饿汉模式
    private static  class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //确认配置
    public final void configure() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 获取某具体配置的值
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfig();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    //检查是否配置确认完成，不完成则不能获取某一配置值
    private void checkConfig() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //----------下面是存放配置的方法---------------

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

}
