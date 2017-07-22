package com.example.yousheng.latte.delegates.bottom;

import android.widget.Toast;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.delegates.LatteDelegate;

/** @function 每个子fragment的父类
 * Created by yousheng on 17/7/21.
 */

public abstract class BottomItemDelegate extends LatteDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getAppContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
