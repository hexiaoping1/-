package edu.zx.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import edu.zx.news.R;
import edu.zx.news.entity.*;
import edu.zx.news.base.MyBaseAdapter;
import edu.zx.news.util.LogWrapper;

/**
 * Created by Administrator on 2016/7/27.
 */
public class UserAdapter extends MyBaseAdapter<Userlog1.LoginlogBean> {
    Context mContext;

    TextView loginTime;
    TextView loginAddress;
    TextView loginType;

    public UserAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup arg2) {
        View view = mInflater.inflate(R.layout.item_login_log, null);
        loginTime = (TextView) view.findViewById(R.id.login_time);
        loginTime.setText(mData.get(position).getTime());
        loginAddress = (TextView) view.findViewById(R.id.login_address);
        loginAddress.setText(mData.get(position).getAddress());
        loginType = (TextView) view.findViewById(R.id.login_type);
        loginType.setText("" + mData.get(position).getDevice());
        return view;
    }
}
