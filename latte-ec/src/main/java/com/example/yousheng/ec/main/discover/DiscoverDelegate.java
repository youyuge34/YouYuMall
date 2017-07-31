package com.example.yousheng.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.latte.delegates.bottom.BottomItemDelegate;
import com.example.yousheng.latte.delegates.web.WebDelegateImpl;

/**
 * @function 发现子delegate，使用webview加载页面
 * Created by yousheng on 17/7/22.
 */

public class DiscoverDelegate extends BottomItemDelegate {

//    private static final String HTTP_URL = "http://123.206.230.157/";
    private static final String HTTP_URL = "http://m.baidu.com/";
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create(HTTP_URL);
        //获占据整个包括底部tab的父fragment
        delegate.setTopDelegate(this.getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }
}
