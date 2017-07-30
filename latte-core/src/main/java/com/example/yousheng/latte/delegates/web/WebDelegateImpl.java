package com.example.yousheng.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.delegates.web.chromeClient.WebChromeClientImpl;
import com.example.yousheng.latte.delegates.web.client.WebViewClientImpl;
import com.example.yousheng.latte.delegates.web.route.RouteKeys;
import com.example.yousheng.latte.delegates.web.route.Router;

/**
 * @function 默认的具体实现子类
 * Created by 尤晟 on 2017-07-30.
 */

public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer{

    public static WebDelegateImpl create(String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
            if(getUrl() != null){
                //用原生的方式模拟web跳转并且进行页面加载
                Router.getInstance().loadPage(this,getUrl());
            }
    }

    //自身实现接口，直接返回自身
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebViewSettings(WebView webView) {
        return new WebViewSettingsInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
