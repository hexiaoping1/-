package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Created by Administrator on 2016/7/27.
 */
public class PwdFragment extends Fragment {
    @Bind(R.id.pwd_reg_left)
    ImageView pwdRegLeft;
    @Bind(R.id.pwd_reg_right)
    ImageView pwdRegRight;
    @Bind(R.id.pwd_edt_email)
    EditText pwdEdtEmail;
    @Bind(R.id.ped_btn)
    Button pedBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.retrieve_password, null);
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

    @OnClick({R.id.pwd_reg_left, R.id.pwd_reg_right, R.id.ped_btn})
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.pwd_reg_left:
                mainActivity.show1();
                break;
            case R.id.pwd_reg_right:
                mainActivity.show2();
                break;
            case R.id.ped_btn:
                String pwdEmail = pwdEdtEmail.getText().toString().trim();
                Map<String, String> map = new HashMap<String, String>();
                map.put("ver", "1");
                map.put("email", pwdEmail);
                UserManager.getInstance(getActivity()).userLogin(Request.Method.GET, Constant.PATH_PWD, map, new pwdListener(), new pwdErrorListener());
                break;
        }
    }

    class pwdListener implements Response.Listener<Object> {

        @Override
        public void onResponse(Object jsonObject) {

            BaseEntity<LogMsg1> data = ParserUser.parserRegister(jsonObject.toString());
            switch (data.getData().getResult()) {
                case 0:

                    Toast.makeText(getActivity(), "发送邮箱成功", Toast.LENGTH_LONG).show();
                    break;
                case -1:

                    Toast.makeText(getActivity(), "该邮箱未注册", Toast.LENGTH_LONG).show();
                    break;
                case -2:

                    Toast.makeText(getActivity(), "邮箱不存在或被封号", Toast.LENGTH_LONG).show();
                    break;
            }

        }
    }

    class pwdErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(), "网络异常！",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
