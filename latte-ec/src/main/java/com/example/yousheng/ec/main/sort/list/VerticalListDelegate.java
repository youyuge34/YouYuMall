package com.example.yousheng.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.R2;
import com.example.yousheng.ec.main.sort.SortDelegate;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * @function 分类的左侧列表
 * Created by yousheng on 17/7/25.
 */

public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_list)
    RecyclerView mRecyclerView = null;


    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        new RestClient.Builder()
                .url("sort_list_data.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //获取到转换后的数据
                        final List<MultipleItemEntity> data = new VerticalListDataConverter()
                                .setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build().get();
    }
}
