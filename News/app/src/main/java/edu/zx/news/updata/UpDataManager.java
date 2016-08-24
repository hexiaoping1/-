package edu.zx.news.updata;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import edu.zx.news.entity.Updata;
import edu.zx.news.util.Constant;
import edu.zx.news.util.LogWrapper;
import edu.zx.news.util.UserManager;


/**
 * Created by Administrator on 2016/7/29.
 */
public class UpDataManager {
    Context mContext;
    int newverson;
    String link;

    public UpDataManager(Context context) {
        mContext = context;
    }

    public void download() {
        if (getCurrentVersion() < newVer()) {
            LogWrapper.e("下载", "if成立1");
            //下载
            DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            //请求
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
            //设置下载的网络类型
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            //设置文件下载的目标路径
            request.setDestinationInExternalFilesDir(mContext, null, "sdcard/android/data/edu.zx.news/files");
            //显示下载通知
            LogWrapper.e("下载", "if成立2");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            LogWrapper.e("下载", "if成立3");
            downloadManager.enqueue(request);
            LogWrapper.e("下载", "if成立4");

        } else {
            LogWrapper.e("下载", "if不成立");
            Toast.makeText(mContext, "当前已是最新版本", Toast.LENGTH_SHORT).show();
        }
    }


//    boolean isUpdata() {
//        if (true) {
//            return true;
//        }
//        return false;
//    }

    //获取当前版本
    public int getCurrentVersion() {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo pi = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            LogWrapper.e("下载", "版本信息 old" + pi.versionCode);
            return pi.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    //获取最新版本
    //update?imei=唯一识别号&pkg=包名&ver=版本
    public int newVer() {
        LogWrapper.e("new", "map 1");
        Map<String, String> map = new HashMap<String, String>();
        LogWrapper.e("new", "map 2");
        map.put("imei", getImei());
        LogWrapper.e("new", "map 3");
        map.put("pkg", mContext.getPackageName());
        LogWrapper.e("new", "map 4");
        map.put("ver",getCurrentVersion()+"");
        LogWrapper.e("new", "map 5");
        UserManager.getInstance(mContext).userLogin(Request.Method.GET, Constant.PATH_USER_UPDATA, map, new Response.Listener() {
            @Override
            public void onResponse(Object o) {
                Gson g = new Gson();
                LogWrapper.e("监听", "成立1");

                Updata data = g.fromJson(o.toString(), new TypeToken<Updata>() {
                }.getType());
                LogWrapper.e("监听", "成立2");
                String newVer = data.getVersion();
                LogWrapper.e("监听", "成立3" + "------" + newVer);
                link = data.getLink();
                LogWrapper.e("监听", "成立4");
                newverson = Integer.parseInt(newVer);
                LogWrapper.e("监听", "成立5" + "------" +newverson );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        LogWrapper.e("new", "map 1");
        return newverson;
    }
}
