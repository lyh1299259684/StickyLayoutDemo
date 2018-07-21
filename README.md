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

* 3.RecyclerView滑动监听
```Java
private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
   @Override
   public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
   }

   @Override
   public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
       super.onScrolled(recyclerView, dx, dy);
       LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
       //获取当前显示的Item位置
       int nowPosition = manager.findFirstVisibleItemPosition();
       tv_title.setText(TAB_TITLE[nowPosition]);
       //使TabLayout切换到指定位置
       tab_main.setScrollPosition(nowPosition, 0, true);
   }
};
```

* 4.RecyclerView最后一条数据处理，重点。
```Java
//获取第一个Item高度，由于所有Item的高度一致，所以获取第一个Item即可
if (mIsFirstLoad && gridItemBean != null && gridItemBean.size() > 0) {
    Runnable runnable = new Runnable(){
       @Override
       public void run() {
           //这个必须在子线程中获取，否则获取不到
           mView = gridLayoutManager.findViewByPosition(0);
           mIsFirstLoad = false;
        }
     };
     mHandler.postDelayed(runnable, 200);
 }

/**
 * 根据支付宝效果模仿的思路
 * 思路是这样的：如果是最后一条就设置为充满全屏，否则就是自适应。
 * 解决的问题：如果都是自适应会发现根据滑动无法切换最后两条，如果数据过少有可能就不能切换。
 *
 * 修改
 *     #versionCode 2
 *     #versionName 1.1
 *     1.自动计算最后一条状态，如果计算高度 >= maxHeight 就设置为wrap_content, 否则设置为match_content
 */
ViewGroup.LayoutParams layoutParams = recyclerHolder.ll_recycler_item_group.getLayoutParams();
   if (position == getItemCount() - 1) {
       if (mView != null) {
          //获取item总数
          int count = gridItemBean == null ? 0 : gridItemBean.size();
          //计算行数
          int rowCount = count % GRID_SPACE_COUNT == 0 ? count / GRID_SPACE_COUNT : (count / GRID_SPACE_COUNT) + 1;
          //计算RecyclerView的纯高度，不包括间距
          int itemHeight = mView.getBottom() * rowCount;
          //计算间距的总高度
          int spaceHeight = (rowCount - 1) * GRID_ITEM_SPACE;
          if ((itemHeight + spaceHeight) >= mRecyclerViewMaxHeight) {  //说明以及超出屏幕范围，需设置warp_content
              layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
          } else {   //未超出，直接设置match_parent
              layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
          }
        } else {       //如果为空，直接设置为match_parent
          layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
        }
     } else {
        layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
     }
recyclerHolder.ll_recycler_item_group.setLayoutParams(layoutParams);
```
