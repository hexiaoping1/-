package edu.zx.news.main;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import edu.zx.news.R;
import edu.zx.news.base.baseActivity;
import edu.zx.news.cn.sharesdk.onekeyshare.OnekeyShare;
import edu.zx.news.db.NewsDBManager;
import edu.zx.news.entity.News;
import edu.zx.news.util.LogWrapper;

/**
 * Created by Administrator on 2016/7/24.
 */
public class WebActivity extends baseActivity {
    WebView webView;
    @Bind(R.id.webview_back)
    ImageView webviewBack;
    @Bind(R.id.webview_comment)
    TextView webviewComment;
    @Bind(R.id.webview_shezhi_shoucang)
    ImageView webviewShezhiShoucang;
    @Bind(R.id.webview)
    WebView webview;
    Intent intent1;
    String nid;
    String stamp;
    @Bind(R.id.web_down_share)
    ImageView webDownShare;
    @Bind(R.id.web_down_comment)
    ImageView webDownComment;
    @Bind(R.id.web_down_idea)
    TextView webDownIdea;
    PopupWindow popupWindow;
    News new1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        WebSettings Websetting = webview.getSettings();
        /***
         * LayoutAlgorithm是一个枚举，用来控制html的布局，总共有三种类型：
         NORMAL：正常显示，没有渲染变化。
         SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
         NARROW_COLUMNS：使所有列的宽度不超过屏幕宽度。
         */
        //支持javaScript
        Websetting.setJavaScriptEnabled( true );
        // 设置可以支持缩放
        Websetting.setSupportZoom( true );
        // 设置出现缩放工具
        Websetting.setBuiltInZoomControls( true );
        Websetting.setLayoutAlgorithm( WebSettings.LayoutAlgorithm.NARROW_COLUMNS );
        Websetting.setUseWideViewPort( true );
        Websetting.setLoadWithOverviewMode( true );
        webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
         new1 = (News) intent.getSerializableExtra("New");
        nid = intent.getStringExtra("nid");
        stamp = intent.getStringExtra("stamp");
        webView.loadUrl(intent.getStringExtra("name"));

    }

    @Override
    public void handleMsg(Message msg) {

    }

    @OnClick({R.id.webview_back, R.id.webview_comment, R.id.webview_shezhi_shoucang})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.webview_back:
                intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.webview_comment:
//                intent1 = new Intent(this, CommentActiivity.class);
//                intent1.putExtra("nid", nid);
//                intent1.putExtra("stamp", stamp);
//                startActivity(intent1);
                break;
            case R.id.webview_shezhi_shoucang:
                //显示 R.layout.item_pop_save 界面
                View popview = getLayoutInflater().inflate(R.layout.pup_window_shoucang, null);
                //对弹出窗口进行设置
                popupWindow = new PopupWindow(popview, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                TextView saveNews = (TextView) popview.findViewById(R.id.save_news);
                //设置点击监听，点击后添加新闻到数据库
                saveNews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        NewsDBManager manager = new NewsDBManager(WebActivity.this);
                        if (manager.saveLoveNews(WebActivity.this,new1)) {
                            Toast.makeText(WebActivity.this, "收藏成功，可在收藏列表查看", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(WebActivity.this, "已经收藏，请收藏其他的新闻", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                popupWindow.showAsDropDown(webviewShezhiShoucang);
//                popupWindow.showAtLocation(webviewShezhiShoucang, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @OnClick({R.id.web_down_share, R.id.web_down_gentie, R.id.web_down_idea})
    public void onClick1(View view) {
        switch (view.getId()) {
            case R.id.web_down_share:
                //初始化SDK
                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
//关闭 sso 授权
                oks.disableSSOWhenAuthorize();
// title 标题，印象笔记、邮箱、信息、微信、人人网和 QQ 空间使用
                oks.setTitle("分享");
// titleUrl 是标题的网络链接，仅在人人网和 QQ 空间使用
                oks.setTitleUrl("http://sharesdk.cn");
// text 是分享文本，所有平台都需要这个字段
                oks.setText("Tower 新闻客户端");
// imagePath 是图片的本地路径，Linked-In 以外的平台都支持此参数
                oks.setImagePath("/sdcard/test.jpg");
// url 仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
// comment 是我对这条分享的评论，仅在人人网和 QQ 空间使用
                oks.setComment("Tower 新闻客户端是一款好的新闻软件");
                LogWrapper.e("tag","show 之前");
                oks.show(this);
                LogWrapper.e("tag","show 之后");
                break;
            case R.id.web_down_gentie:
                intent1 = new Intent(this, CommentActiivity.class);
                intent1.putExtra("nid", nid);
                intent1.putExtra("news",new1);
                intent1.putExtra("stamp", stamp);
                startActivity(intent1);
                break;
            case R.id.web_down_idea:
                break;
        }
    }
}
