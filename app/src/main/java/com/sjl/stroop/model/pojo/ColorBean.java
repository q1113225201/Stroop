package com.sjl.stroop.model.pojo;

/**
 * ColorBean
 *
 * @author 林zero
 * @date 2018/4/20
 */

public class ColorBean {
    private int color;
    private String colorText;

    public ColorBean(int color, String colorText) {
        this.color = color;
        this.colorText = colorText;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }
}
