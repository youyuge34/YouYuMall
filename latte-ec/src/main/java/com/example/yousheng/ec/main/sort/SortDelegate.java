package com.example.yousheng.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.latte.delegates.bottom.BottomItemDelegate;

/**
 * Created by yousheng on 17/7/22.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        LatteLogger.d("onBInd SortDelegate");
    }
}
