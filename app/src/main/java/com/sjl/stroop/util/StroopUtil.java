package com.sjl.stroop.util;

import android.graphics.Color;

import com.sjl.stroop.model.pojo.ColorBean;
import com.sjl.stroop.model.pojo.StroopData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * StroopUtil
 *
 * @author 林zero
 * @date 2018/4/20
 */

public class StroopUtil {
    private static ColorBean[] colorBeans = {
            new ColorBean(Color.RED,"红"),
            new ColorBean(Color.GREEN,"红"),
            new ColorBean(Color.YELLOW,"红"),
            new ColorBean(Color.BLUE,"红"),
    };
    /**
     * 获取24种颜色
     */
    public static List<ColorBean> getColorBeanList(){
        List<ColorBean> list = new ArrayList<>();
        //先添加4种颜色各4个
        for (int i=0;i<colorBeans.length;i++){
            list.add(colorBeans[i]);
            list.add(colorBeans[i]);
            list.add(colorBeans[i]);
            list.add(colorBeans[i]);
        }
        Random random = new Random();
        //随机添加8种颜色
        for (int i=0;i<8;i++){
            list.add(colorBeans[random.nextInt(colorBeans.length)]);
        }
        return list;
    }
    /**
     * 构造Stroop测试对象
     */
    public static StroopData.StroopDataItem buildStroopDataItem(int boxColor,int textColor,String text){
        StroopData.StroopDataItem stroopDataItem = new StroopData.StroopDataItem();
        stroopDataItem.setTextColor(textColor);
        stroopDataItem.setText(text);
        stroopDataItem.setBoxColor(boxColor);
        return stroopDataItem;
    }
}
