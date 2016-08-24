package edu.zx.news.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import edu.zx.news.R;
import edu.zx.news.base.MyBaseAdapter;
import edu.zx.news.entity.News;
import edu.zx.news.util.LogWrapper;


public class NewsAdapter extends MyBaseAdapter<News> {

    public NewsAdapter(Context context) {

        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup arg2) {
        //ListView的优化
        viewhodle hodle = null;
        if (convertView == null) {
            hodle = new viewhodle();
            convertView = mInflater.inflate(R.layout.activity_news, null);
            hodle.img = (ImageView) convertView.findViewById(R.id.img_title);
            hodle.txt = (TextView) convertView.findViewById(R.id.txt_title);
            hodle.stam = (TextView) convertView.findViewById(R.id.txt_stamp);
            hodle.sum = (TextView) convertView.findViewById(R.id.txt_summary);
            convertView.setTag(hodle);
        } else {
            hodle = (viewhodle) convertView.getTag();
        }

        String pathString = mData.get(position).getIcon();
        //picasso 转换图片适应布局的大小并减小内存占用

        LogWrapper.e("TAG", pathString);
        Picasso picasso = Picasso.with(mContext);
        RequestCreator requestCreator = picasso.load(pathString).resize(200, 180);
        requestCreator.placeholder(R.mipmap.ic_launcher).error(R.mipmap.sorry);
        requestCreator.into(hodle.img);
        hodle.sum.setText(mData.get(position).getSummary());
        //设置摘要的字体大小
        hodle.sum.setTextSize(20);

        hodle.stam.setText(mData.get(position).getStamp());
        //设置日期的字体大小
        hodle.stam.setTextSize(18);
        hodle.txt.setText(mData.get(position).getTitle());
        //设置标题的字体大小
        hodle.txt.setTextSize(22);
        return convertView;

    }

    class viewhodle {
        ImageView img;
        TextView txt;
        TextView sum;
        TextView stam;
    }
}

