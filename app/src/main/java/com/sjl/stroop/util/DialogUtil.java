package com.sjl.stroop.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by SJL on 2018/4/22.
 */

public class DialogUtil {
    private static AlertDialog dialog;
    public static void showDialog(Context context, String msg, final DialogInterface.OnClickListener onClickListener){
        dialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(onClickListener!=null){
                            onClickListener.onClick(dialog, which);
                        }
                    }
                })
                .show();
    }
}
