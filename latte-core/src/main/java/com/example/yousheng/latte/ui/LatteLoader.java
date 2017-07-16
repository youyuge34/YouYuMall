package com.example.yousheng.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @function 加载loading dialog的接口工具类,需要在dialog传入上下文context，在某些布局比如webview中全局context会出错
 * Created by yousheng on 17/7/16.
 */

public class LatteLoader {
    public final static int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Enum<LoaderStyle> style, Context context) {
        showLoading(style.name(), context);
    }

    public static void showLoading(Context context) {
        showLoading(DEFAULT_LOADER, context);
    }

    public static void showLoading(String type, Context context) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        dialog.setContentView(avLoadingIndicatorView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Window dialogWindow = dialog.getWindow();

        int width = DimenUtil.getScreenWidth();
        int height = DimenUtil.getScreenHeight();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.height = height / LOADER_SIZE_SCALE;
            lp.height += height / LOADER_OFFSET_SCALE;
            lp.width = width / LOADER_SIZE_SCALE;
            dialogWindow.setAttributes(lp);
        }

        dialog.show();
        LOADERS.add(dialog);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
