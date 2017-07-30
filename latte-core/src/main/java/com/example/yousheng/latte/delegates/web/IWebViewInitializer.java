package com.example.yousheng.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @function
 * Created by 尤晟 on 2017-07-29.
 */

public interface IWebViewInitializer {

    // 对webView进行各种settings，返回setting好的webView
    WebView initWebViewSettings(WebView webView);

    //针对浏览器本身行为的控制，如前进后退的回调
    WebViewClient initWebViewClient();

    //针对页面的控制,如js交互
    WebChromeClient initWebChromeClient();
}
