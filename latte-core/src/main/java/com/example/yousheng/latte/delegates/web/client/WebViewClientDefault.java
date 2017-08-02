package com.example.yousheng.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.delegates.IPageLoadListener;
import com.example.yousheng.latte.delegates.web.WebDelegate;
import com.example.yousheng.latte.ui.LatteLoader;

/**
 * @function 默认的 WebViewClient实体类,页面内跳转，带loading
 * Created by 尤晟 on 2017-08-02.
 */

public class WebViewClientDefault extends WebViewClient {
    protected final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private Handler HANDLER = Latte.getHandler();

    public void setIPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }

    public WebViewClientDefault(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(mIPageLoadListener != null){
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        },600);

    }
}
