# StickyLayoutDemo
一个仿支付宝“全部应用”模块，联动布局的demo

### 效果图如下：
![image](https://github.com/lyh1299259684/StickyLayoutDemo/blob/master/app/src/main/Gif/result_image.gif)

### 重要代码如下：

*1.动态创建TabLayout中的tab

'"Java
//初始化tab
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[0]).setTag(Constans.TAB_ANDROID));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[1]).setTag(Constans.TAB_KOTLIN));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[2]).setTag(Constans.TAB_IOS));
tab_main.addTab(tab_main.newTab().setText(TAB_TITLE[3]).setTag(Constans.TAB_DART));
'"
