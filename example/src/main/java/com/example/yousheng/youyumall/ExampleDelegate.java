package com.example.yousheng.youyumall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.IError;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.ui.LoaderStyle;

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
        testNetwork();
//        LatteLoader.showLoading(getContext());
    }


    private void testNetwork(){
         RestClient client = new RestClient.Builder()
                .url("order")
//            .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                    Toast.makeText(Latte.getAppContext(),response.substring(0,40),Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
//                    Toast.makeText(Latte.getAppContext(),"error:"+msg,Toast.LENGTH_LONG).show();
                    }
                })
                .loader(getContext(), LoaderStyle.CubeTransitionIndicator)
                .build()
                ;

        client.get();
    }

}
