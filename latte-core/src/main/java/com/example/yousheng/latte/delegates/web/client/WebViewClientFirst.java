package com.example.yousheng.latte.delegates.web.client;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yousheng.latte.delegates.web.WebDelegate;
import com.example.yousheng.latte.delegates.web.route.Router;
import com.example.yousheng.latte.util.log.LatteLogger;

/**
 * @function 一个client的具体实现
 * Created by 尤晟 on 2017-07-30.
 */

public class WebViewClientFirst extends WebViewClientDefault {
    //过滤重定向。应用内部页面是否加载完毕，因为重定向也会触发shouldOverrideUrlLoading方法
    private boolean isPageOk = false;

    public WebViewClientFirst(WebDelegate DELEGATE) {
        super(DELEGATE);
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        LatteLogger.d("shouldOverrideUrlLoading11111111", request.getUrl().toString());
//        if(Build.VERSION.SDK_INT>=21){
//            LatteLogger.d("shouldOverrideUrlLoading2222222222", request.getUrl().toString());
//            return Router.getInstance().handleWebUrl(DELEGATE, request.getUrl().toString());
//        }
//
//        return super.shouldOverrideUrlLoading(view, request);
//    }

    // 复写shouldOverrideUrlLoading()方法，
    //若没有设置 WebViewClient 则在点击链接之后由系统处理该 url，通常是使用浏览器打开或弹出浏览器选择对话框。
    // 若设置 WebViewClient 且该方法返回 true ，则说明由应用的代码处理该 url，WebView 不处理。
    // 若设置 WebViewClient 且该方法返回 false，则说明由 WebView 处理该 url，即用 WebView 加载该 url。
    // return true使得打开网页时不调用系统浏览器,而是在本WebView中显示
    //使用旧方法，兼容旧机型,在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        //js的重定向都由原生来接管
        //比如location.href或者a标签，全部以这种方式拦截下来，强制跳转,打开新Delegate
        if (isPageOk) {
            return Router.getInstance().handleWebUrl(DELEGATE, url);
        } else
            return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        LatteLogger.d("onPageStart:" + url);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        LatteLogger.d("onPageFinish: " + url);
        isPageOk = true;
        super.onPageFinished(view, url);
    }
}
