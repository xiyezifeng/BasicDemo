package com.example.lixiao.basicdemo.app.widget.bean;

public class BarItemBean {
    private int nomalRes;
    private int selectRes;
    private String title;
    private int nomalColor;
    private int selectColor;
    private int resSize;
    private int titleSize;

    private int gap;

    private boolean isSingleImage;

    public boolean isSingleImage() {
        return isSingleImage;
    }

    public void setSingleImage(boolean singleImage) {
        isSingleImage = singleImage;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getNomalRes() {
        return nomalRes;
    }

    public void setNomalRes(int nomalRes) {
        this.nomalRes = nomalRes;
    }

    public int getSelectRes() {
        return selectRes;
    }

    public void setSelectRes(int selectRes) {
        this.selectRes = selectRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNomalColor() {
        return nomalColor;
    }

    public void setNomalColor(int nomalColor) {
        this.nomalColor = nomalColor;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getResSize() {
        return resSize;
    }

    public void setResSize(int resSize) {
        this.resSize = resSize;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }
}
