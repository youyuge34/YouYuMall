package com.example.yousheng.latte.net;

import com.example.yousheng.latte.app.ConfigKeys;
import com.example.yousheng.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yousheng on 17/7/15.
 */

public class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getLatteConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //转换结果为字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OkHttpHolder {
        private static final long TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)

                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService SERVICE = RetrofitHolder
                .RETROFIT.create(RestService.class);
    }


    public static RestService getService() {
        return RestServiceHolder.SERVICE;
    }
}
