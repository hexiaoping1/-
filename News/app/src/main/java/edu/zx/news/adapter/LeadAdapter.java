package edu.zx.news.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class LeadAdapter extends PagerAdapter {
    int[] mImag;
    Context mContext;

    public LeadAdapter(Context context, int[] imags) {
        mContext = context;
        mImag = imags;

    }

    @Override
    public int getCount() {
        return mImag.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化子选项的视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imangview = new ImageView(mContext);
        imangview.setImageResource(mImag[position]);
        container.addView(imangview);
        return imangview;
    }

    //销毁子选项
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

