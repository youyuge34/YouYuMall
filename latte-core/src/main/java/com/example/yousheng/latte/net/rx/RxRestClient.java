package com.example.yousheng.latte.net.rx;

import android.content.Context;

import com.example.yousheng.latte.net.HttpMethod;
import com.example.yousheng.latte.net.RestCreator;
import com.example.yousheng.latte.ui.LatteLoader;
import com.example.yousheng.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 构建者模式
 * Created by yousheng on 17/7/15.
 */

public class RxRestClient {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final String URL;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    private RxRestClient(String url,
                         Map<String, Object> params,
                         RequestBody body,
                         File file,
                         Context context,
                         LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //真正的请求方法
    private final Observable<String> request(HttpMethod method) {
        final RxRestService restService = RestCreator.getRxService();
        Observable<String> observable = null;

        //显示读取界面
        if (LOADER_STYLE != null) {
            if (CONTEXT != null) {
                LatteLoader.showLoading(LOADER_STYLE, CONTEXT);
            } else throw new NullPointerException("context in showLoading() is null");

        }

        switch (method) {
            case GET:
                observable = restService.get(URL, PARAMS);
                break;
            case POST:
                observable = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = restService.postRaw(URL, BODY);
                break;
            case PUT:
                observable = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = restService.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        return observable;

    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }


    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxService().download(URL, PARAMS);
    }


    /**
     * 构建者模式的构建类
     */
    public final static class Builder {
        private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
        private String mUrl = null;
        private RequestBody mBody = null;
        private Context mContext = null;
        private LoaderStyle mLoaderStyle = null;
        private File mFile = null;


        public Builder() {
        }

        public final RxRestClient.Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public final RxRestClient.Builder params(WeakHashMap<String, Object> params) {
            PARAMS.putAll(params);
            return this;
        }

        public final RxRestClient.Builder params(String key, Object value) {
            PARAMS.put(key, value);
            return this;
        }

        public final RxRestClient.Builder file(File file) {
            this.mFile = file;
            return this;
        }

        public final RxRestClient.Builder file(String file) {
            this.mFile = new File(file);
            return this;
        }


        public final RxRestClient.Builder raw(String raw) {
            this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
            return this;
        }


        public final RxRestClient.Builder loader(Context context, LoaderStyle style) {
            this.mContext = context;
            this.mLoaderStyle = style;
            return this;
        }

        public final RxRestClient.Builder loader(Context context) {
            this.mContext = context;
            this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
            return this;
        }

        public final RxRestClient build() {
            return new RxRestClient(mUrl, PARAMS,
                     mBody, mFile, mContext, mLoaderStyle);
        }
    }
}
