package com.example.yousheng.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.yousheng.latte.app.Latte;

/**
 * Created by yousheng on 17/7/22.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout mSwipeRefreshLayout) {
        this.REFRESH_LAYOUT = mSwipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
