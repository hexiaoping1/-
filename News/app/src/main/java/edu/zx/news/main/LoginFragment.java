package edu.zx.news.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;


import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.zx.news.R;
import edu.zx.news.base.BaseEntity;
import edu.zx.news.entity.LogMsg1;
import edu.zx.news.parser.ParserUser;
import edu.zx.news.util.Constant;
import edu.zx.news.util.LogWrapper;
import edu.zx.news.util.SharePreUtils;
import edu.zx.news.util.UserManager;

/**
 * Created by Administrator on 2016/7/25.
 */
public class LoginFragment extends Fragment {
    @Bind(R.id.main_title_home)
    ImageView mainTitleHome;
    @Bind(R.id.main_title_share)
    ImageView mainTitleShare;
    @Bind(R.id.login_title_txt)
    TextView loginTitleTxt;
    @Bind(R.id.login_edt_name)
    EditText loginEdtName;
    @Bind(R.id.login_edt_pwd)
    EditText loginEdtPwd;
    @Bind(R.id.login_zhuce)
    Button loginZhuce;
    @Bind(R.id.login_pwd)
    Button loginPwd;
    @Bind(R.id.login_login)
    Button loginLogin;
    MainActivity mainActivity;
    String userName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.main_title_home, R.id.main_title_share, R.id.login_edt_name, R.id.login_edt_pwd, R.id.login_zhuce, R.id.login_pwd, R.id.login_login})
    public void onClick(View view) {
        mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.main_title_home:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.main_title_share:
                mainActivity.show2();
                break;
            case R.id.login_edt_name:
                break;
            case R.id.login_edt_pwd:
                break;
            case R.id.login_zhuce:
                MainActivity m = (MainActivity) getActivity();
                m.showRegister();
                break;
            case R.id.login_pwd:
                MainActivity q = (MainActivity) getActivity();
                q.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_id, new PwdFragment()).commit();
                break;
            case R.id.login_login:
                //获取输入的内容
                 userName = loginEdtName.getText().toString().trim();
                String userPwd = loginEdtPwd.getText().toString().trim();
                //验证数据
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getActivity(), "请输入用户名",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userPwd)) {
                    Toast.makeText(getActivity(), "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userPwd.length() < 6 || userPwd.length() > 16) {
                    Toast.makeText(getActivity(), "密码长度错误",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //添加数据
                Map<String, String> values = new HashMap<String, String>();
                values.put("ver", "1");
                values.put("uid", userName);
                values.put("pwd", userPwd);
                values.put("device", "0");
                UserManager.getInstance(getActivity())
                        .userLogin(Request.Method.GET, Constant.PATH_LOGIN, values, new LoginListener(), new LoginErrorListener());
                break;
        }
    }

    class LoginListener implements Response.Listener<Object> {

        @Override
        public void onResponse(Object jsonObject) {
            BaseEntity<LogMsg1> data = ParserUser.parserRegister(jsonObject.toString());
            SharePreUtils spu = new SharePreUtils(getActivity());
            if (data.getStatus() == 0) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_LONG);
                SharedPreferences preferences=getActivity().getSharedPreferences("sad", Context.MODE_PRIVATE);
                preferences.edit().putBoolean("login",true).commit();
                  preferences.edit().putString("user",userName).commit();
                spu.save(data.getData().getToken(), data.getData().getExplain());
            } else if (data.getStatus() == -3) {
                Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_LONG);
            } else {
                Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_LONG);
            }
        }
    }

    class LoginErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(), "登录异常！",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
