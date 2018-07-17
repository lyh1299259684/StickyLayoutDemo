package com.liyahong.stickylayoutdemo.bean;

import android.view.View;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 09:35
 */

public class RecyclerItemBean {

    private String title;
    private List<GridItemBean> gridItemBean;
    /**
     * 注：此变量为手动添加，不作为json字段，为了辨别是否隐藏头部使用。
     *     故：如果用户手动滑动到某条position，则将改字段修改为true，然后刷新适配器即可
     *     注意：在for循环时一定要考虑到其他因素，因为只可能有一个实体里面的bean为true，也就是说
     *           循环的时候，判断是否是当前呈现在用户面前的position，如果是将该字段修改为true，否则
     *           修改为false。
     *  一开始的初步设想，暂时没有使用到。
     */
    private boolean isInvisibleTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GridItemBean> getGridItemBean() {
        return gridItemBean;
    }

    public void setGridItemBean(List<GridItemBean> gridItemBean) {
        this.gridItemBean = gridItemBean;
    }

    public boolean isInvisibleTitle() {
        return isInvisibleTitle;
    }

    public void setInvisibleTitle(boolean invisibleTitle) {
        isInvisibleTitle = invisibleTitle;
    }
}
