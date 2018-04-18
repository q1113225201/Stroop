package com.sjl.stroop.model.pojo;

import org.greenrobot.greendao.annotation.Entity;

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
    private float totalTime;
    //平均时间 秒
    private float svgTime;
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

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public float getSvgTime() {
        return svgTime;
    }

    public void setSvgTime(float svgTime) {
        this.svgTime = svgTime;
    }

    public List<StroopDataItem> getList() {
        return list;
    }

    public void setList(List<StroopDataItem> list) {
        this.list = list;
    }

    public class StroopDataItem {
        //测试结果
        private boolean result;
        //使用时间 毫秒
        private int time;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}