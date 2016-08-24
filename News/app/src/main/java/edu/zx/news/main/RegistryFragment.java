package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

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


public class RegistryFragment extends Fragment {
    @Bind(R.id.btn_reg_left)
    ImageView btnRegLeft;
    @Bind(R.id.btn_reg_right)
    ImageView btnRegRight;
    @Bind(R.id.edt_reg_name)
    EditText edtRegName;
    @Bind(R.id.edt_reg_pass)
    EditText edtRegPass;
    @Bind(R.id.btn_reg_now)
    Button btnRegNow;
    MainActivity mainActivity;
    @Bind(R.id.edt_reg_user)
    EditText edtRegUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_reg_left, R.id.btn_reg_right, R.id.btn_reg_now})
    public void onClick(View view) {
        mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            //左边一级菜单显示点击事件
            case R.id.btn_reg_left:
                mainActivity.show1();
                break;

            //右边二级菜单显示点击事件
            case R.id.btn_reg_right:
                mainActivity.show2();
                break;
            //注册按钮点击事件
            case R.id.btn_reg_now:
                String useName = edtRegUser.getText().toString().trim();
                String useEmai = edtRegName.getText().toString().trim();
                String pwd = edtRegPass.getText().toString().trim();
                Map<String, String> value = new HashMap<String, String>();

                //验证注册用户的信息是否符合格式
                if (TextUtils.isEmpty(useName)) {
                    Toast.makeText(getActivity(), "请输入用户昵称", Toast.LENGTH_LONG).show();
                    return;
                }

                if (pwd.length() < 6 || pwd.length() > 16) {
                    Toast.makeText(getActivity(), "密码长度错误", Toast.LENGTH_LONG).show();
                    return;
                }

                value.put("ver", "1");
                value.put("uid", useName);
                value.put("email", useEmai);
                value.put("pwd", pwd);
                UserManager.getInstance(getActivity()).userLogin(Request.Method.GET, Constant.PATH_REGISTER, value, new RegisListener(), new ResgisErrorListener());

                break;
            //chockbox与协议绑定点击事件

        }
    }

    //接收服务器返回的数据
    class RegisListener implements Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject obj) {
            BaseEntity<LogMsg1> data = ParserUser.parserRegister(obj.toString());
            int a = data.getData().getResult();
            SharePreUtils pre = new SharePreUtils(getActivity());
            switch (data.getData().getResult()) {
                case 0:
                    pre.save(data.getData().getToken(), data.getData().getExplain());
                    Toast.makeText(getActivity(), "用户注册成功", Toast.LENGTH_LONG).show();
                    break;
                case -1:
                    Toast.makeText(getActivity(), "服务器用户已满", Toast.LENGTH_LONG).show();
                    break;
                case -2:
                    Toast.makeText(getActivity(), "用户名已占用", Toast.LENGTH_LONG).show();
                    break;
                case -3:
                    Toast.makeText(getActivity(), "邮箱已占用", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    class ResgisErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            LogWrapper.e("注册", "注册失败");
        }
    }

}
