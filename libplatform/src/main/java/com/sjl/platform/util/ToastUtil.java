package com.sjl.platform.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.sjl.platform.PlatformInit;

/**
 * Created by sjl on 2015/8/11.
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 显示提示文本
     *
     * @param text
     */
    public static void showToast(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        initToast();
        toast.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private static void initToast() {
        if (toast == null) {
            toast = Toast.makeText(PlatformInit.application, "", Toast.LENGTH_SHORT);
        }
    }

    public static void showToastLong(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        initToast();
        toast.setText(text);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
