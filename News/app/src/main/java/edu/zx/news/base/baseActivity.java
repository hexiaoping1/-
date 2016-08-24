package edu.zx.news.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import edu.zx.news.main.progessDialog;


public abstract class baseActivity extends FragmentActivity {

    progessDialog pro;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    //跳转
    public void toActivity(Class cla) {
        toActivity(cla, null);
    }

    public void toActivity(Class cla, Bundle bundle) {
        Intent start = new Intent(this, cla);
        if (bundle != null) {
            start.putExtra("bundle", bundle);
        }
        this.startActivity(start);
    }

    //Handler
    public Handler mHandle = new Handler() {
        public void handleMessage(Message msg) {

            handleMsg(msg);
        }

        ;
    };

    public abstract void handleMsg(Message msg);

    //进度条
//显示对话框
    public void showPgbDialog() {
        pro = new progessDialog(this);
        pro.show();
    }

    //结束对话框
    public void disMissDlg() {
        if (pro != null)
            pro.dismiss();
    }


}
