package edu.zx.news.util;


import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.Map;

/**
 * 用户登陆的管理类
 */

public class UserManager {
    private static UserManager mUserManager;
    private Context mContext;

    private UserManager(Context context) {
        mContext = context;
    }

    public static UserManager getInstance(Context context) {
        if (mUserManager == null)
            synchronized (UserManager.class) {

                mUserManager = new UserManager(context);

            }
        return mUserManager;
    }

    //用户的登陆
    public void userLogin(int method, String path, Map<String, String> map,
                          Response.Listener listener, Response.ErrorListener error) {

        StringBuffer buf = new StringBuffer();
        buf.append(path).append("?");
        LogWrapper.e("登陆", "" + map.entrySet().size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            buf.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        buf.deleteCharAt(buf.length() - 1);
//                String str="http://118.244.212.82:9092/newsClient/user_login?ver=1&uid=98765432&pwd=98765432&device=0";
//        LogWrapper.e("登陆",str);
//        LogWrapper.e("登陆",buf.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, buf.toString(), null, listener, error);

        HttpVolley.addRequest(mContext, jsonObjectRequest);

    }
}
