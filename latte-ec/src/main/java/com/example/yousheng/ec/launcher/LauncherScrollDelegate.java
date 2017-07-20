package com.example.yousheng.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.yousheng.ec.R;
import com.example.yousheng.latte.app.AccountManager;
import com.example.yousheng.latte.app.IUserChecker;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.ui.launcher.ILauncherListener;
import com.example.yousheng.latte.ui.launcher.LauncherHolderCreator;
import com.example.yousheng.latte.ui.launcher.OnLauncherFinishTag;
import com.example.yousheng.latte.ui.launcher.ScrollLauncherTag;
import com.example.yousheng.latte.util.storage.LattePreference;

import java.util.ArrayList;

/** @function 首次安装或更新后的引导页
 * Created by yousheng on 17/7/18.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private static final String TAG = "LauncherScrollDelegate";
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner  = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    private void initBanner() {
        if(INTEGERS.isEmpty()){
            INTEGERS.add(R.mipmap.launcher_01);
            INTEGERS.add(R.mipmap.launcher_02);
            INTEGERS.add(R.mipmap.launcher_03);
            INTEGERS.add(R.mipmap.launcher_04);
            INTEGERS.add(R.mipmap.launcher_05);
        }
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {

        //若点击了最后一张图
        if(position == INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    //首次轮播图结束的回调调用
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }
}
