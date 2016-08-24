package edu.zx.news.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.zx.news.R;
import edu.zx.news.updata.UpDataManager;
import edu.zx.news.util.SharePreUtils;
import edu.zx.news.util.UserManager;


public class rightFragment extends Fragment {
    MainActivity mainActivity;
    GridView grvied;
    @Bind(R.id.lst_img_right)
    ImageView lstImgRight;
    @Bind(R.id.txt_right_load)
    TextView txtRightLoad;
    @Bind(R.id.txt_right_up)
    TextView txtRightUp;
    @Bind(R.id.fragment_right)
    LinearLayout fragmentRight;
    Boolean login;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null);
        ButterKnife.bind(this, view);
        init();
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void init() {
        preferences = getActivity().getSharedPreferences("sad", Context.MODE_PRIVATE);
        login = preferences.getBoolean("login", false);
        if (login){txtRightLoad.setText(preferences.getString("user", "unknow"));}


        lstImgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转 FragmentLOGIN
                if (!login) {
                    login = true;
                    mainActivity = (MainActivity) getActivity();
                    mainActivity.setContext();
                    mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_id, new LoginFragment()).commit();
                } else {
                    txtRightLoad.setText(preferences.getString("user", "unknow"));
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    startActivity(intent);
                }
            }


        });
    }


    @OnClick(R.id.txt_right_up)
    public void onClick() {
        UpDataManager upDataManager = new UpDataManager(getActivity());
        upDataManager.download();
    }
}


