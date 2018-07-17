package com.liyahong.stickylayoutdemo;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.liyahong.stickylayoutdemo.adapter.RecyclerViewAdapter;
import com.liyahong.stickylayoutdemo.bean.GridItemBean;
import com.liyahong.stickylayoutdemo.bean.RecyclerItemBean;
import com.liyahong.stickylayoutdemo.common.Constans;
import com.liyahong.stickylayoutdemo.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_main;
    private TextView tv_title;
    private RecyclerView rv_main;

    private RecyclerViewAdapter adapter;

    private static final String[] TAB_TITLE = {"Android", "Kotlin", "IOS", "Dart"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bindEvent();
        initData();
    }

    private void initView() {
        tab_main = (TabLayout) findViewById(R.id.tab_main);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
    }

    private void bindEvent() {
        tab_main.addOnTabSelectedListener(mOnTabSelectedListener);
        rv_main.addOnScrollListener(mOnScrollListener);
    }

    private void initData() {
        //默认值
        tv_title.setText(TAB_TITLE[0]);

        rv_main.setLayoutManager(new LinearLayoutManager(this));

        //初始化tab
        tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[0]).setTag(Constans.TAB_ANDROID));
        tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[1]).setTag(Constans.TAB_KOTLIN));
        tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[2]).setTag(Constans.TAB_IOS));
        tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[3]).setTag(Constans.TAB_DART));

        //初始化数据
        initRecycler();
    }

    private void initRecycler() {
        List<RecyclerItemBean> data = new ArrayList<>();

        for (int i = 0; i < TAB_TITLE.length; i++) {
            RecyclerItemBean itemBean = new RecyclerItemBean();
            itemBean.setTitle(TAB_TITLE[i]);

            String[] imageUrl = null;
            String[] desc = null;

            switch (i) {
                case 0:
                    imageUrl = new String[]{
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2628548231/TB1TiNwbDJYBeNjy1zeXXahzVXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2598292358/TB1XP0camtYBeNjSspkXXbU8VXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/883133878/TB1GN2bklDH8KJjSszcXXbDTFXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/2548289425/TB122l3yN9YBuNjy0FfXXXIsVXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/2406931838/TB1iC9PXKLM8KJjSZFqXXa7.FXa_!!0-item_pic.jpg_250x250.jpg_.webp"};
                    desc = new String[]{"《第一行代码第二版》", "《Android高级进阶》", "《疯狂Android讲义》", "《零基础学Android》", "《深入理解Android内核设计思想》"};
                    break;
                case 1:
                    imageUrl = new String[]{
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/119707802/TB2yVCsbpmWBuNjSspdXXbugXXa_!!0-saturn_solar.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/2505196631/TB1G.x4eER1BeNjy0FmXXb0wVXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2505196631/TB1I7Anl4WYBuNjy1zkXXXGGpXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2406931838/TB1O9VNdTJYBeNjy1zeXXahzVXa_!!0-item_pic.jpg_250x250.jpg_.webp"
                    };
                    desc = new String[]{"《Kotlin程序开发入门精要》", "《Kotlin编程原理》", "《Kotlin从入门到精通》", "《疯狂Kotlin讲义》"};
                    break;
                case 2:
                    imageUrl = new String[]{
                            "https://g-search2.alicdn.com/img/bao/uploaded/i4/i2/721504898/TB1f7emmmfD8KJjSszhXXbIJFXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/111354297/TB2zK_ECKuSBuNjSsziXXbq8pXa_!!0-saturn_solar.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/2427605321/TB1YFchAAOWBuNjSsppXXXPgpXa_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/2889542302/TB1PHBuvsyYBuNkSnfoXXcWgVXa_!!0-item_pic.jpg_250x250.jpg_.webp"
                    };
                    desc = new String[]{"《IOS应用开发》", "《自己动手做IOS App》", "《IOS编程实战》", "《IOS应用逆向工程》"};
                    break;
                case 3:
                    imageUrl = new String[]{
                            "https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/352798170/TB1eG3PSXXXXXX3aXXXXXXXXXXX_!!0-item_pic.jpg_250x250.jpg_.webp",
                            "https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/2990382264/TB1PoTocb1YBuNjSszhXXcUsFXa_!!0-item_pic.jpg_250x250.jpg_.webp"};
                    desc = new String[]{"《Dart编程语言》", "《Dart语言程序设计》"};
                    break;
            }

            if (imageUrl != null) {
                List<GridItemBean> itemBeanList = new ArrayList<>();

                for (int j = 0; j < imageUrl.length; j++) {
                    itemBeanList.add(new GridItemBean(imageUrl[j], desc[j]));
                }

                itemBean.setGridItemBean(itemBeanList);
            }

            data.add(itemBean);
        }

        //刷新适配器
        refreshAdapter(data);
    }

    /**
     * 刷新适配器
     * @param itemBeans
     */
    private void refreshAdapter(List<RecyclerItemBean> itemBeans){
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(R.layout.adapter_recyclerview_item, this, itemBeans);
            adapter.setOnItemClickListener(mOnItemClickListener);
            rv_main.setAdapter(adapter);
        } else {
            adapter.refreshAdapter(itemBeans);
        }
    }

    /**
     * gridView点击回调
     */
    private OnItemClickListener<GridItemBean> mOnItemClickListener = new OnItemClickListener<GridItemBean>() {
        @Override
        public void onItemClick(GridItemBean gridItemBean) {
            showMsg(gridItemBean.getDesc(), false);
        }
    };

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
            tab_main.setScrollPosition(nowPosition, 0, true);
        }
    };

    /**
     * 可以放到BaseActivity里
     */
    private void showMsg(String msg, boolean isLong){
        Toast.makeText(this, msg, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
