package com.example.yousheng.youyumall;

import com.example.yousheng.ec.launcher.LauncherScrollDelegate;
import com.example.yousheng.latte.activities.ProxyActivity;
import com.example.yousheng.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
