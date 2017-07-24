package com.example.yousheng.latte.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.ui.recycler.DataConverter;
import com.example.yousheng.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * @function ui层。加载首页，刷新页面的处理类，设置刷新的监听，index页面懒加载时调用此类的getFirstPage方法
 * 在getFirstPage方法中设置adapter，绑定视图与数据
 * <p>
 * Created by yousheng on 17/7/22.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PageNumberBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout mSwipeRefreshLayout, PageNumberBean bean, RecyclerView recyclerview, DataConverter converter) {
        this.REFRESH_LAYOUT = mSwipeRefreshLayout;
        BEAN = bean;
        RECYCLERVIEW = recyclerview;
        CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    //简单工厂模式
    public static RefreshHandler create(SwipeRefreshLayout mSwipeRefreshLayout, RecyclerView recyclerview, DataConverter converter) {
        return new RefreshHandler(mSwipeRefreshLayout, new PageNumberBean(), recyclerview, converter);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void getFirstPage(String url, final Context context) {
        BEAN.setmDelayed(1000);
        new RestClient.Builder()
                .loader(context)
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //String转成json对象
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setmTotal(object.getInteger("total"))
                                .setmPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();

                    }
                })
                .build().get();
    }

    //上拉加载更多接口回调
    @Override
    public void onLoadMoreRequested() {

    }
}
