package edu.zx.news.main;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.zx.news.R;


public class progessDialog extends Dialog {
    ImageView mImag;

    public progessDialog(Context context) {
        super(context, R.style.progess);
        setContentView(R.layout.dailog_progess);
        mImag = (ImageView) this.findViewById(R.id.img_progess);


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.img_progess_anim);
        mImag.setAnimation(animation);
    }
}
