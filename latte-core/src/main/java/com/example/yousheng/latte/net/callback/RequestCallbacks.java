package com.example.yousheng.latte.net.callback;

import android.os.Handler;

import com.example.yousheng.latte.app.ConfigKeys;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.ui.LatteLoader;
import com.example.yousheng.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yousheng on 17/7/16.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle loaderStyle;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest REQUEST, ISuccess SUCCESS, IFailure FAILURE, IError ERROR, LoaderStyle LOADER_STYLE) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.loaderStyle = LOADER_STYLE;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    //停止loading dialog
    private final void stopLoading(){
        final long delayed = Latte.getLatteConfiguration(ConfigKeys.LOADER_DELAYED);
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loaderStyle!=null){
                    LatteLoader.stopLoading();
                }
            }
        },delayed);
    }
}
