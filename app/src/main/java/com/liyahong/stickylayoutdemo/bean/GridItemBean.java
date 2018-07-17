package com.liyahong.stickylayoutdemo.bean;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 10:02
 */

public class GridItemBean {

    private String imageUrl;
    private String desc;

    public GridItemBean(String imageUrl, String desc) {
        this.imageUrl = imageUrl;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
