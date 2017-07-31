package com.example.yousheng.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yousheng.latte.delegates.web.chromeClient.WebChromeClientImpl;
import com.example.yousheng.latte.delegates.web.route.RouteKeys;
import com.example.yousheng.latte.delegates.web.route.Router;

/**
 * @function WebDelegate的默认子类，点击链接会在webView内部跳转
 * Created by 尤晟 on 2017-07-31.
 */

public class WebDelegateDefault extends WebDelegate implements IWebViewInitializer {

    public static WebDelegateDefault create(String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateDefault delegate = new WebDelegateDefault();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public WebView initWebViewSettings(WebView webView) {
        return new WebViewSettingsInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        };
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟web跳转并且进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public boolean onBackPressedSupport() {

        if (getWebView().canGoBack()) {  //表示按返回键时的操作
            getWebView().goBack();   //后退
            //webview.goForward();//前进
            return true;    //已处理
        }
        return false;
    }
}
