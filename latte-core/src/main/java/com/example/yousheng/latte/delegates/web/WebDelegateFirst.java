package com.example.yousheng.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.delegates.web.chromeClient.WebChromeClientImpl;
import com.example.yousheng.latte.delegates.web.client.WebViewClientFirst;
import com.example.yousheng.latte.delegates.web.route.RouteKeys;
import com.example.yousheng.latte.delegates.web.route.Router;

/**
 * @function 默认的特殊具体实现子类, 首次点击内部链接后会开启新的Delegate
 * Created by 尤晟 on 2017-07-30.
 */

public class WebDelegateFirst extends WebDelegateDefault {

    public static WebDelegateFirst create(String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateFirst delegate = new WebDelegateFirst();
        delegate.setArguments(bundle);
        return delegate;
    }


    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientFirst client = new WebViewClientFirst(this);
        return client;
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

}
