package edu.zx.news.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public List<T> mData;
    public Context mContext;
    public LayoutInflater mInflater;

    public MyBaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /***
     * 添加新数据
     *
     * @param data
     */
    public void addNewData(List<T> data) {
        if (mData != null)
            mData.clear();
        mData = data;
    }

    /***
     * 追加数据
     *
     * @param data
     */
    public void appenData(List<T> data) {
        mData.addAll(data);
    }

    /***
     * 刷新适配器
     */
    public void upData() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return mData.size();
    }

    @Override
    //获取每个子条目的内容
    public T getItem(int position) {

        return mData.get(position);
    }

    @Override
    public long getItemId(int arg0) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {

        return getMyView(position, convertView, arg2);
    }

    //抽象的方法
    public abstract View getMyView(int position, View convertView, ViewGroup arg2);


}


