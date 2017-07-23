package com.example.yousheng.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.R2;
import com.example.yousheng.latte.delegates.bottom.BottomItemDelegate;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.ui.recycler.MultipleFields;
import com.example.yousheng.latte.ui.recycler.MultipleItemEntity;
import com.example.yousheng.latte.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by yousheng on 17/7/22.
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        LatteLogger.d("onBInd IndexDelegate");
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
    }

    //同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
//        mRefreshHandler.getFirstPage("index_data.json",getContext());
        new RestClient.Builder().loader(getContext())
                .url("index_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        ArrayList<MultipleItemEntity> list = converter.convert();

                        final String url = list.get(1).getValue(MultipleFields.IMAGE_URL);
                        Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();

                    }
                }).build().get();
    }

    private void initRefreshLayout() {
        if(mRefreshLayout !=null){
            mRefreshLayout.setColorSchemeResources(
                    android.R.color.holo_blue_bright,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
            );
            mRefreshLayout.setProgressViewOffset(true,120,300);
        }

    }
}
