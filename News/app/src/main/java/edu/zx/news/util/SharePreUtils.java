package edu.zx.news.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/7/25.
 * 共享参数的工具类
 */
public class SharePreUtils {
    private static final String PRE_NAME = "login";
    private SharedPreferences mPreferences;

    public SharePreUtils(Context context) {
        mPreferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }

    //保存数据的
    public void save(String token, String explain) {
        mPreferences.edit()
                .putString("token", token)
                .putString("explain", explain)
                .commit();
    }

    public String getToken() {

        return mPreferences.getString("token", "没有数据");
    }

    public String getExplain() {
        return mPreferences.getString("explain", "没有数据");
    }
}


