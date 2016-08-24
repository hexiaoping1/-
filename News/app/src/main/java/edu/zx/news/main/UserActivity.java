package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.zx.news.R;
import edu.zx.news.adapter.UserAdapter;
import edu.zx.news.base.BaseEntity;
import edu.zx.news.base.baseActivity;
import edu.zx.news.entity.Userlog1;
import edu.zx.news.parser.ParserUser;
import edu.zx.news.util.Constant;
import edu.zx.news.util.LogWrapper;
import edu.zx.news.util.SharePreUtils;
import edu.zx.news.util.UserManager;

public class UserActivity extends baseActivity {
    SharePreUtils sharePreUtils;
    @Bind(R.id.user_title_home)
    ImageView userTitleHome;//home 按钮
    @Bind(R.id.user_title_txt)
    TextView userTitleTxt;//标题
    @Bind(R.id.user_pic)
    ImageView userPic;//用户图片
    @Bind(R.id.user_id)
    TextView userId;//用户id
    @Bind(R.id.user_jifen)
    TextView userJifen;//用户积分
    @Bind(R.id.account_phone_icon)
    TextView accountPhoneIcon;
    @Bind(R.id.comment_count)
    TextView commentCount;//评论个数
    @Bind(R.id.account_phone_layout)
    LinearLayout accountPhoneLayout;
    @Bind(R.id.acount_phone_line)
    View acountPhoneLine;
    @Bind(R.id.login_log)
    TextView loginLog;
    @Bind(R.id.btn_exit)
    Button btnExit;//退出登录按钮
    @Bind(R.id.user_list)
    ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        init();

    }

    public void init() {
        sharePreUtils = new SharePreUtils(this);
        String token = sharePreUtils.getToken();
        Log.e("12345", token);
//        this.getSystemService();
        String imei = "864367021511124";
        Map<String, String> map = new HashMap<String, String>();
        map.put("ver", "1");
        map.put("imei", imei);
        map.put("token", token);
        Log.e("12345", "111");
        UserManager.getInstance(this).userLogin(Request.Method.GET, Constant.PATH_USER_HOME, map, new HomeListener(), new HomeErrorListener());
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new
                        Intent(UserActivity.this,MainActivity.class));
                finish();
            }
        });}

    @Override
    public void handleMsg(Message msg) {

    }

    @OnClick(R.id.user_title_home)
    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class HomeListener implements Response.Listener<Object> {

        @Override
        public void onResponse(Object obj) {
            BaseEntity<Userlog1> data = ParserUser.parserUser(obj.toString());
            data.getData().getPortrait();//拿到的图片
            String uidname = data.getData().getUid();//用户id
            userId.setText(uidname);
            int jifen = data.getData().getIntegration();//积分
            userJifen.setText("积分:" + jifen);
            int num = data.getData().getComnum();//跟帖个数
            commentCount.setText(num + "");
            UserAdapter userAdapter = new UserAdapter(UserActivity.this);
            List<Userlog1.LoginlogBean> ue = data.getData().getLoginlog();
            userAdapter.addNewData(ue);

            userList.setAdapter(userAdapter);

        }
    }

    class HomeErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            LogWrapper.e("注册", "注册失败");
        }
    }
}


