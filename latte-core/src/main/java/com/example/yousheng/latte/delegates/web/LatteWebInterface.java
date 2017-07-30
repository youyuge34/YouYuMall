package com.example.yousheng.latte.delegates.web;

import com.alibaba.fastjson.JSON;

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
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("data");
        return null;
    }

}
