package com.sjl.platform.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sjl.platform.R;
import com.sjl.platform.util.AppUtil;

/**
 * PopWindow
 *
 * @author 林zero
 * @date 2018/3/18
 */

public class PopWindow {
    private Activity activity;
    private int layoutId;
    private int width;
    private int height;
    private int animationStyle;
    private PopWindowListener popWindowListener;
    private PopupWindow popupWindow;

    public PopWindow(Activity activity, int layoutId,int width,int height,int animationStyle, PopWindowListener popWindowListener) {
        this.activity = activity;
        this.layoutId = layoutId;
        this.width = width;
        this.height = height;
        this.animationStyle = animationStyle;
        this.popWindowListener = popWindowListener;
        initPopupWindow();
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        popupWindow = new PopupWindow(view,width,height);
        popupWindow.setAnimationStyle(animationStyle);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (popWindowListener != null) {
                    popWindowListener.onDismiss();
                }
                AppUtil.changeAlpha(activity,1f);
            }
        });
        if (popWindowListener != null) {
            popWindowListener.onInit(view);
        }
    }

    public void showAsDropDown(View view) {
        popupWindow.showAsDropDown(view);
        AppUtil.changeAlpha(activity,0.3f);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        popupWindow.showAtLocation(parent, gravity, x, y);
        AppUtil.changeAlpha(activity,0.3f);
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    public interface PopWindowListener {
        void onInit(View view);

        void onDismiss();
    }

}
