package com.example.yousheng.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.R2;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * @function 分类子Delegate的右侧布局
 * Created by yousheng on 17/7/25.
 */

public class ContentDelegate extends LatteDelegate {

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initData() {
        new RestClient.Builder()
                //实际可在url中加入contentId，目前测试中写死
                .url("sort_content_data_1.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content
                                        , R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build().get();
    }
}
