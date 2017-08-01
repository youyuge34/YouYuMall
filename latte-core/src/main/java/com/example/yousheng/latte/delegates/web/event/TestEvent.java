package com.example.yousheng.latte.delegates.web.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by 尤晟 on 2017-08-01.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),params,Toast.LENGTH_LONG).show();
        if(getAction().equals("test")){

            //确保在主线程
            final WebView mWebView = getWebView();
         mWebView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    mWebView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
