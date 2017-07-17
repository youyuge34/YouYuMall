package com.example.yousheng.latte.net;

import android.content.Context;

import com.example.yousheng.latte.net.callback.IError;
import com.example.yousheng.latte.net.callback.IFailure;
import com.example.yousheng.latte.net.callback.IRequest;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.net.callback.RequestCallbacks;
import com.example.yousheng.latte.ui.LatteLoader;
import com.example.yousheng.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 构建者模式
 * Created by yousheng on 17/7/15.
 */

public class RestClient {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String url,
               Map<String, Object> params,
               String downloadDir,
               String extension,
               String name,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               RequestBody body,
               File file,
               Context context,
               LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //真正的请求方法
    public final void request(HttpMethod method) {
        final RestService restService = RestCreator.getService();
        Call<String> call = null;

        //预处理回调操作
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        //显示读取界面
        if (LOADER_STYLE != null) {
            if (CONTEXT != null) {
                LatteLoader.showLoading(LOADER_STYLE, CONTEXT);
            } else throw new NullPointerException("context in showLoading() is null");

        }

        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL, BODY);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = restService.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    //构建回调接口实例
    private RequestCallbacks getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }


    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }


    /**
     * 构建者模式的构建类
     */
    public final static class Builder {
        private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
        private String mUrl = null;
        private IRequest mIRequest = null;
        private ISuccess mISuccess = null;
        private IFailure mIFailure = null;
        private IError mIError = null;
        private RequestBody mBody = null;
        private Context mContext = null;
        private LoaderStyle mLoaderStyle = null;
        private File mFile = null;
        private String mDownloadDir = null;
        private String mExtension = null;
        private String mName = null;


        public Builder() {
        }

        public final RestClient.Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public final RestClient.Builder params(WeakHashMap<String, Object> params) {
            PARAMS.putAll(params);
            return this;
        }

        public final RestClient.Builder params(String key, Object value) {
            PARAMS.put(key, value);
            return this;
        }

        public final RestClient.Builder file(File file) {
            this.mFile = file;
            return this;
        }

        public final RestClient.Builder file(String file) {
            this.mFile = new File(file);
            return this;
        }

        public final RestClient.Builder name(String name) {
            this.mName = name;
            return this;
        }

        public final RestClient.Builder dir(String dir) {
            this.mDownloadDir = dir;
            return this;
        }

        public final RestClient.Builder extension(String extension) {
            this.mExtension = extension;
            return this;
        }

        public final RestClient.Builder raw(String raw) {
            this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
            return this;
        }

        public final RestClient.Builder onRequest(IRequest iRequest) {
            this.mIRequest = iRequest;
            return this;
        }

        public final RestClient.Builder success(ISuccess iSuccess) {
            this.mISuccess = iSuccess;
            return this;
        }

        public final RestClient.Builder failure(IFailure iFailure) {
            this.mIFailure = iFailure;
            return this;
        }

        public final RestClient.Builder error(IError iError) {
            this.mIError = iError;
            return this;
        }

        public final RestClient.Builder loader(Context context, LoaderStyle style) {
            this.mContext = context;
            this.mLoaderStyle = style;
            return this;
        }

        public final RestClient.Builder loader(Context context) {
            this.mContext = context;
            this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
            return this;
        }

        public final RestClient build() {
            return new RestClient(mUrl, PARAMS,
                    mDownloadDir, mExtension, mName,
                    mIRequest, mISuccess, mIFailure,
                    mIError, mBody, mFile, mContext, mLoaderStyle);
        }
    }
}
