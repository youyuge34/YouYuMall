package com.example.yousheng.latte.net.download;

import android.os.AsyncTask;

import com.example.yousheng.latte.net.RestCreator;
import com.example.yousheng.latte.net.callback.IError;
import com.example.yousheng.latte.net.callback.IFailure;
import com.example.yousheng.latte.net.callback.IRequest;
import com.example.yousheng.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yousheng on 17/7/17.
 */

public class DownloadHandler {
    private final WeakHashMap<String, Object> PARAMS ;
    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    //后缀名
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(WeakHashMap<String, Object> params, String URL, IRequest REQUEST,
                           String DOWNLOAD_DIR, String EXTENSION,
                           String NAME, ISuccess SUCCESS,
                           IFailure FAILURE, IError ERROR) {
        this.PARAMS = params;
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    public final void handleDownload() {
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }

        RestCreator.getService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    if(call.isExecuted()){
                        final ResponseBody body = response.body();
                        SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                DOWNLOAD_DIR, EXTENSION, body, NAME);


                        //这里一定要注意判断，否则文件下载不全
                        if (task.isCancelled()) {
                            if (REQUEST != null) {
                                REQUEST.onRequestEnd();
                            }
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });
    }
}
