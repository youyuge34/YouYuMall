package com.example.yousheng.latte.net.callback;

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

    public RequestCallbacks(IRequest REQUEST, ISuccess SUCCESS, IFailure FAILURE, IError ERROR) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
