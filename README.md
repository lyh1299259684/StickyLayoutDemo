# StickyLayoutDemo
一个仿支付宝“全部应用”模块，联动布局的demo

### 效果图如下：
![image](https://github.com/lyh1299259684/StickyLayoutDemo/blob/master/app/src/main/Gif/result_image.gif)

### 重要代码如下：

* 1.动态创建TabLayout中的tab

```Java
//初始化tab
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[0]).setTag(Constans.TAB_ANDROID));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[1]).setTag(Constans.TAB_KOTLIN));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[2]).setTag(Constans.TAB_IOS));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[3]).setTag(Constans.TAB_DART));
```

* 2.TabLayout选中监听

```Java
/**
 * tabLayout 选择监听
 */
private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
   @Override
   public void onTabSelected(TabLayout.Tab tab) {
      tv_title.setText(tab.getText());

      int position = tab.getPosition();

      if (tab.getPosition() != -1) {  //切换RecyclerView位置
          rv_main.scrollToPosition(tab.getPosition());
          LinearLayoutManager mLayoutManager = (LinearLayoutManager) rv_main.getLayoutManager();
          mLayoutManager.scrollToPositionWithOffset(position, 0);
       }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
};
```
