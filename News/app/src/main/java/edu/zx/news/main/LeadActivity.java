package edu.zx.news.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.zx.news.R;
import edu.zx.news.adapter.LeadAdapter;
import edu.zx.news.base.baseActivity;


public class LeadActivity extends baseActivity {

    int[] img = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};
    ImageView[] imgs;
    @Bind(R.id.img_point1)
    ImageView imgPoint1;
    @Bind(R.id.img_point2)
    ImageView imgPoint2;
    @Bind(R.id.img_point3)
    ImageView imgPoint3;
    @Bind(R.id.img_point4)
    ImageView imgPoint4;
//    @Bind(R.id.btn_start)
//    Button btnStart;
    @Bind(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = this.getSharedPreferences("bbs", Context.MODE_PRIVATE);
        Boolean isFirs = sharedPreferences.getBoolean("isFirs", true);
        if (!isFirs) {
            this.toActivity(LogActivity.class, null);
            this.finish();
        } else {
            isFirs = false;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isFirs", false);
            edit.commit();
        }

        LeadAdapter adapter = new LeadAdapter(LeadActivity.this, img);
        viewPager.setAdapter(adapter);
        imgs = new ImageView[4];
        imgs[0] = imgPoint1;
        imgs[1] = imgPoint2;
        imgs[1].setAlpha(100);
        imgs[2] = imgPoint3;
        imgs[2].setAlpha(100);
        imgs[3] = imgPoint4;
        imgs[3].setAlpha(100);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

//            @Override
//            public void onPageSelected(int position) {
//                if (position == img.length - 1) {
//                    btnStart.setVisibility(View.VISIBLE);
//                } else {
//                    btnStart.setVisibility(View.GONE);
//                }
//                for (int i = 0; i < imgs.length; i++) {
//                    if (position == i) {
//                        imgs[i].setAlpha(255);
//                    } else {
//                        imgs[i].setAlpha(100);
//                    }
//
//                }
//            }
public void onPageSelected(int position) {
    for (int i = 0; i < imgs.length; i++){
        if (i == position) {
            imgs[i].setAlpha( 1.0F );
        } else {
            imgs[i].setAlpha( 0.5F );
        }}
    if (position>=3){
//                           Intent intent1=new Intent(LeadActivity.this,LogActivity.class );
//                           startActivity( intent1 );
        toActivity( LogActivity.class );
        finish();
    }

}
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LeadActivity.this, LogActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void handleMsg(Message msg) {

    }
}
