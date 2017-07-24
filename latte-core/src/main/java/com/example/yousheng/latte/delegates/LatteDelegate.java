package com.example.yousheng.latte.delegates;

/**
 * @function 所有fragment的基类
 * Created by yousheng on 17/7/15.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
