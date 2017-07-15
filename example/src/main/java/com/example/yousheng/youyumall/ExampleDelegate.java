package com.example.yousheng.youyumall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yousheng.latte.delegates.LatteDelegate;

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

    }
}
