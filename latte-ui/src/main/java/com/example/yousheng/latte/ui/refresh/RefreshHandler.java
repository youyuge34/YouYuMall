package com.example.yousheng.latte.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.ISuccess;

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

    public void getFirstPage(String url, final Context context){
        new RestClient.Builder()
                .loader(context)
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                    }
                })
                .build().get();
    }
}
