package com.example.yousheng.youyumall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.IError;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.net.rx.RxRestClient;
import com.example.yousheng.latte.ui.LatteLoader;
import com.example.yousheng.latte.ui.LoaderStyle;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yousheng on 17/7/15.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testNetwork();
//        testRxNetwork();
    }

    //TODO: 测试方法，提醒可删除
    private void testNetwork() {
        RestClient client = new RestClient.Builder()
                .url("order")
//            .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getAppContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
//                    Toast.makeText(Latte.getAppContext(),"error:"+msg,Toast.LENGTH_LONG).show();
                    }
                })
                .loader(getContext(), LoaderStyle.CubeTransitionIndicator)
                .build();

        client.get();
    }

    //TODO: 测试方法，提醒可删除
    private void testRxNetwork() {
        new RxRestClient.Builder()
                .url("order")
                .loader(getContext(),LoaderStyle.LineScalePartyIndicator)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        LatteLoader.stopLoading();
                        Toast.makeText(Latte.getAppContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
