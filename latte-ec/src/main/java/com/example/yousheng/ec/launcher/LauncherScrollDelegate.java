package com.example.yousheng.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.yousheng.ec.R;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.ui.launcher.LauncherHolderCreator;

import java.util.ArrayList;

/** @function 首次安装或更新后的引导页
 * Created by yousheng on 17/7/18.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private static final String TAG = "LauncherScrollDelegate";
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        mConvenientBanner  = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        Log.i(TAG, "onItemClick: "+position);
    }
}
