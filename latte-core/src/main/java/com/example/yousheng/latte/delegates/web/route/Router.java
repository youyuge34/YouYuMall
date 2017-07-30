package com.example.yousheng.latte.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.delegates.web.WebDelegate;
import com.example.yousheng.latte.delegates.web.WebDelegateImpl;

/**
 * @function 路由截断,线程安全的惰性单例模式
 * Created by 尤晟 on 2017-07-30.
 */

public class Router {
    private Router() {
    }

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate webDelegate,String url) {
        //如果js里包含电话协议
        if (url.contains("tel:")) {
            callPhone(webDelegate.getContext(), url);
            return true;
        }
        //不是电话,原生跳转
//        final LatteDelegate parentDelegate = webDelegate.getParentDelegate();
//
//        //如果有父布局fragment容器，则让父fragment跳转
//        final WebDelegateImpl toWebDelegate = WebDelegateImpl.create(url);
//        if (parentDelegate == null){
//            webDelegate.start(toWebDelegate);
//        }else {
//            parentDelegate.start(toWebDelegate);
//        }

        final LatteDelegate topDelegate = webDelegate.getTopDelegate();

        final WebDelegateImpl toWebDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(toWebDelegate);

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    //在assets文件夹中的本地页面（和res文件夹同级）
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
