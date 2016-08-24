package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.zx.news.R;
import edu.zx.news.base.baseActivity;


public class LogActivity extends baseActivity {
    Animation animation;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_anim);
        img = (ImageView) this.findViewById(R.id.img_log);
        animation = AnimationUtils.loadAnimation(this, R.anim.logi_anima);
        animation.setAnimationListener(animationlister);
        img.setAnimation(animation);
    }

    private Animation.AnimationListener animationlister = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(LogActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    public void handleMsg(Message msg) {

    }
}