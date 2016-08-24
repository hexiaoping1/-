package edu.zx.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.zx.news.R;
import edu.zx.news.base.MyBaseAdapter;
import edu.zx.news.entity.A123;
import edu.zx.news.entity.ComMsg;
import edu.zx.news.util.LogWrapper;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ComAdapter extends MyBaseAdapter<A123> {
    public ComAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup arg2) {
        View view = mInflater.inflate(R.layout.comment_list, null);
//        ImageView pic= (ImageView) view.findViewById(R.id.com_user_pic);
        TextView uid = (TextView) view.findViewById(R.id.com_uid);
        uid.setText(mData.get(position).getUid());
        TextView time = (TextView) view.findViewById(R.id.com_time);
        time.setText(mData.get(position).getStamp());
        TextView pinglun = (TextView) view.findViewById(R.id.com_pinglun);
        pinglun.setText(mData.get(position).getContent());
        return view;
    }
}
