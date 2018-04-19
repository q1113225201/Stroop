package com.sjl.stroop.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sjl.platform.util.DensityUtil;

/**
 * CustomItemDecoration
 *
 * @author 林zero
 * @date 2018/4/19
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int offset;

    public CustomItemDecoration(Context context) {
        this.context = context;
        offset = DensityUtil.dp2px(context,5);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //第一个需要在上面绘制
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = offset;
        }else{
            outRect.top = 0;
        }
        outRect.left = offset;
        outRect.right = offset;
        outRect.bottom = offset;
    }
}
