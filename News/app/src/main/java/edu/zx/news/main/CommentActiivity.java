package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
import edu.zx.news.adapter.ComAdapter;
import edu.zx.news.base.BaseEntity;
import edu.zx.news.base.baseActivity;
import edu.zx.news.entity.A123;
import edu.zx.news.entity.ComMsg;
import edu.zx.news.entity.Commit;
import edu.zx.news.entity.News;
import edu.zx.news.parser.ParserUser;
import edu.zx.news.util.Constant;
import edu.zx.news.util.LogWrapper;
import edu.zx.news.util.SharePreUtils;
import edu.zx.news.util.UserManager;

public class CommentActiivity extends baseActivity {
    String nid;
    String stamp;
    @Bind(R.id.com_ac_back)
    ImageView comAcBack;
    @Bind(R.id.com_ac_list)
    ListView comAcList;
    @Bind(R.id.com_ac_write)
    EditText comAcWrite;
    @Bind(R.id.com_ac_send)
    ImageView comAcSend;
    StringBuffer buf;
    News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_actiivity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        news= (News) intent.getSerializableExtra("news");
        nid = intent.getStringExtra("nid");
        String[] strs=intent.getStringExtra("stamp").split("-");
         buf=new StringBuffer();
        buf.append(strs[0]).append(strs[1]).append(strs[2]);
        init();
    }

    public void init() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ver", "1");
        map.put("nid", nid);
        map.put("type", "1");
        map.put("stamp","20160617");
        map.put("cid", "1");
        map.put("dir", "1");
        map.put("cnt", "20");
        UserManager.getInstance(this).userLogin(Request.Method.GET, Constant.PATH_USER_COM, map, new ComListener(), new ComErrorListener());


    }

    @Override
    public void handleMsg(Message msg) {

    }

    @OnClick({R.id.com_ac_back, R.id.com_ac_write, R.id.com_ac_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.com_ac_back:
                Intent intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                break;
            case R.id.com_ac_send:
                //cmt_commit?ver=版本号&nid=新闻编号&token=用户令牌&imei=手机标识符&ctx=评论内容
                String write = comAcWrite.getText().toString().trim();
                SharePreUtils sharePreUtils = new SharePreUtils(CommentActiivity.this);
                String token = sharePreUtils.getToken();
                if (token==null){
                    Toast.makeText(this,"请先登录再评论",Toast.LENGTH_SHORT);
                    return;
                }
                String imei = "864367021511124";
                Map<String, String> map = new HashMap<String, String>();
                map.put("ver", "1");
                map.put("nid", nid);
                map.put("token", token);
                map.put("imei", imei);
                map.put("ctx", write);
                UserManager.getInstance(this).userLogin(Request.Method.GET, Constant.PATH_USER_SEND, map, new sendListener(), new sendErrorListener());
                break;
        }
    }

    class ComListener implements Response.Listener<Object> {

        @Override
        public void onResponse(Object jsonObject) {
            BaseEntity<List<A123>> data = ParserUser.parserCom(jsonObject.toString());
            List<A123> list = data.getData();
            ComAdapter adapter = new ComAdapter(CommentActiivity.this);
            adapter.addNewData(list);
            comAcList.setAdapter(adapter);
        }
    }

    class ComErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {

            LogWrapper.e("评论", "获取失败");
        }
    }

    class sendListener implements Response.Listener<Object> {

        @Override
        public void onResponse(Object jsonObject) {
            BaseEntity<Commit> data = ParserUser.parserSend(jsonObject.toString());
            switch (data.getData().getResult()) {
                case 0:
                    Toast.makeText(CommentActiivity.this, "评论成功",
                            Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(CommentActiivity.this, "非法关键字",
                            Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(CommentActiivity.this, "禁止评论",
                            Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(CommentActiivity.this, "禁止评论(用户被禁言)",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    class sendErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {

            Toast.makeText(CommentActiivity.this, "禁止评论，请检查您的网络",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
