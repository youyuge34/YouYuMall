package com.example.yousheng.latte.net.interceptors;

import android.annotation.SuppressLint;

import com.example.yousheng.latte.util.log.LatteLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** @function 打印出请求和返回的信息的拦截器
 * Created by yousheng on 17/7/17.
 */

public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "LoggingInterceptor";
    @SuppressLint("DefaultLocale")
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
       LatteLogger.d("intercept:   "+String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        LatteLogger.d("intercept:   "+String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
