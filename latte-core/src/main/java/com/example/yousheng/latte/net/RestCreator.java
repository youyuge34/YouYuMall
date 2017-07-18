package com.example.yousheng.latte.net;

import com.example.yousheng.latte.app.ConfigKeys;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yousheng on 17/7/15.
 */

public class RestCreator {

    /**
     * 参数容器
     */
    //为了避免线程不安全，在初始化每个网络请求client时，初始化一个hashmap
//    private static final class ParamsHolder {
//        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
//    }
//
//    public static WeakHashMap<String, Object> getParams() {
//        return ParamsHolder.PARAMS;
//    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getLatteConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //转换结果为字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    //构建okhttp3
    private static final class OkHttpHolder {
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getLatteConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor () {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }


        private static final long TIME_OUT = 40;
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
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

    private static final class RxRestServiceHolder {
        private static final RxRestService SERVICE = RetrofitHolder
                .RETROFIT.create(RxRestService.class);
    }

    public static RxRestService getRxService() {
        return RxRestServiceHolder.SERVICE;
    }
}
