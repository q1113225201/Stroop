package com.sjl.stroop.model.pojo;

import java.util.List;

/**
 * StroopData
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class StroopData {
    //正确次数
    private int trueCount;
    //错误次数
    private int falseCount;
    //总时间 秒
    private double totalTime;
    //平均时间 秒
    private double svgTime;
    //每次测试数据
    private List<StroopDataItem> list;

    public int getTrueCount() {
        return trueCount;
    }

    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }

    public int getFalseCount() {
        return falseCount;
    }

    public void setFalseCount(int falseCount) {
        this.falseCount = falseCount;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getSvgTime() {
        return svgTime;
    }

    public void setSvgTime(double svgTime) {
        this.svgTime = svgTime;
    }

    public List<StroopDataItem> getList() {
        return list;
    }

    public void setList(List<StroopDataItem> list) {
        this.list = list;
    }

    public static class StroopDataItem {
        //方块颜色
        private int boxColor;
        //文字颜色
        private int textColor;
        //文字
        private String text;
        //选择颜色
        private ColorBean selectColorBean;
        //测试结果
        private boolean result = false;
        //使用时间 毫秒
        private long time;

        public int getBoxColor() {
            return boxColor;
        }

        public void setBoxColor(int boxColor) {
            this.boxColor = boxColor;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public ColorBean getSelectColorBean() {
            return selectColorBean;
        }

        public void setSelectColorBean(ColorBean selectColorBean) {
            this.selectColorBean = selectColorBean;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
