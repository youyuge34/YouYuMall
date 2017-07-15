package com.example.yousheng.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/** @fuction 基activity
 * Created by yousheng on 17/7/15.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    //初始化容器布局，添加根fragment
    private void initContainer(Bundle savedInstanceState) {
        final ContentFrameLayout frameLayout = new ContentFrameLayout(this);
        frameLayout.setId(R.id.delegate_container);
        setContentView(frameLayout);

        if(savedInstanceState == null){
            //传入根delegate
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    //既然是单activity架构，则活动结束后整个app结束
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
