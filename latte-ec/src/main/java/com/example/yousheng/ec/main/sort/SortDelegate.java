package com.example.yousheng.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.main.sort.content.ContentDelegate;
import com.example.yousheng.ec.main.sort.list.VerticalListDelegate;
import com.example.yousheng.latte.delegates.bottom.BottomItemDelegate;

/**
 * @function 分类子Delegate
 * Created by yousheng on 17/7/22.
 */

public class SortDelegate extends BottomItemDelegate {
    private static final int FIRST_PAGE = 1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        LatteLogger.d("onBInd SortDelegate");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(FIRST_PAGE));
    }
}
