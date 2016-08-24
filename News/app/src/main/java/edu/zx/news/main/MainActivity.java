package edu.zx.news.main;

import android.content.Intent;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import edu.zx.news.R;
import edu.zx.news.adapter.NewsAdapter;
import edu.zx.news.base.baseActivity;
import edu.zx.news.entity.News;
import edu.zx.slidingmenu.SlidingMenu;


public class MainActivity extends baseActivity {
    ImageView imgleft;
    ImageView imgright;
    SlidingMenu menu;
    ListView lst;//ListView新闻显示
    NewsAdapter adapter;
    List<News> news;

    /***
     * 新闻主界面
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniSlidingmenu();
        this.getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id, new NewsFragment()).commit();

        //设置进入页面进度条
        showPgbDialog();

        mHandle.sendEmptyMessageDelayed(1, 2000);



    }



    public void showRegister() {
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_id, new RegistryFragment())
                .commit();
    }

    //点击事件的方法
    public void onclik() {
        imgleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.showMenu();
            }
        });
        imgright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.showSecondaryMenu();
            }
        });
        //listView子条目的点击事件
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String str = news.get(position).getLink();
                intent.putExtra("name", str);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public void iniSlidingmenu() {
        menu = new SlidingMenu(this);
        //设置模式 三种模式
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置菜单距离边界的距离
        menu.setBehindOffset(150);
        //设置全屏可以触摸
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //关联Activity
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置一级菜单
        menu.setMenu(R.layout.menu_left);
        //设置二级菜单
        menu.setSecondaryMenu(R.layout.menu_right);
        //设置模式 三种模式 LEFT_RIGHT  RIGHT    LEFT

        //左侧添加菜单
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.menu_left, new leftFragment())
                .commit();

        //右侧添加菜单
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.menu_right, new rightFragment())
                .commit();

    }

    //设置主页面显示键的点击事件
    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    public void setContext() {
        menu.setContent(R.layout.activity_main);
    }

    //跳转到主页面的方法
    public void showContents() {

        if (menu.isShown())
            menu.showContent();
    }

    public void show1() {
        menu.showMenu();
    }

    public void show2() {
        menu.showSecondaryMenu();
    }

    @Override
    public void handleMsg(Message msg) {

        disMissDlg();
    }
}


