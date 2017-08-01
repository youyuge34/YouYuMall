package com.example.yousheng.latte.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.yousheng.latte.delegates.web.event.Event;
import com.example.yousheng.latte.delegates.web.event.EventManager;

/**
 * @function 用来和原生进行交互，js代码中通过反射调用此类中的方法
 * Created by 尤晟 on 2017-07-30.
 */

public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }
    //简单工厂模式
    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    //js中会调用此方法
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        //根据params参数，去获取对应的event任务实例
        final Event event = EventManager.getInstance().getEvent(action);
        if(event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
