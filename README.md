# StickyLayoutDemo
一个仿支付宝“全部应用”模块，联动布局的demo

效果图如下：
![image](https://github.com/lyh1299259684/StickyLayoutDemo/blob/master/app/src/main/Gif/result_image.gif)

重要代码如下：
1.如何动态创建tabLayout的Tab
//初始化tab
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[0]).setTag(Constans.TAB_ANDROID));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[1]).setTag(Constans.TAB_KOTLIN));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[2]).setTag(Constans.TAB_IOS));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[3]).setTag(Constans.TAB_DART));

2.tabLayout监听
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

3.RecyclerView滑动监听
private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int nowPosition = manager.findFirstVisibleItemPosition();
        tv_title.setText(TAB_TITLE[nowPosition]);
        //切换tab到指定位置
        tab_main.setScrollPosition(nowPosition, 0, true);
    }
};

4.处理内容不够情况，不能滑动问题
/**
 * 根据支付宝效果模仿的思路
 * 思路是这样的：如果是最后一条就设置为充满全屏，否则就是自适应。
 * 解决的问题：如果都是自适应会发现根据滑动无法切换最后两条，如果数据过少有可能就不能切换。
 */
 ViewGroup.LayoutParams layoutParams = recyclerHolder.ll_recycler_item_group.getLayoutParams();
 if (position == getItemCount() - 1) {
      layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
 } else {
      layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
 }
 recyclerHolder.ll_recycler_item_group.setLayoutParams(layoutParams);
