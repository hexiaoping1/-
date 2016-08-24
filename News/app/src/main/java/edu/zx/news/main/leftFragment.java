package edu.zx.news.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import edu.zx.news.R;
import edu.zx.news.util.LogWrapper;


public class leftFragment extends Fragment {
    String city[] = {"新闻", "收藏", "跟帖", "本地", "图片"};
    int img[] = {R.mipmap.biz_navigation_tab_news, R.mipmap.biz_navigation_tab_read, R.mipmap.biz_navigation_tab_ties, R.mipmap.biz_navigation_tab_local_news, R.mipmap.biz_navigation_tab_pics};
    String english[] = {"NEWS", "FAVORITE", "LOCAL", "COMMENT", "PHOTO"};
    ListView mLst;
    MainActivity mainactivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left, null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        mLst = (ListView) getView().findViewById(R.id.lst_left);
        mLst.setAdapter(new fragAdapter());
        onclik();
    }

    //ListView的点击事件
    public void onclik() {
        mainactivity = (MainActivity) getActivity();
        mLst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mainactivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_id, new NewsFragment()).commit();
                        mainactivity.showContents();
                        break;
                    case 1:
                        mainactivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_id, new FragmentFavorite()).commit();
                        mainactivity.showContents();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    //适配器
    class fragAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return city.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.left_lst_item, null);
            ImageView imgs = (ImageView) view.findViewById(R.id.img_item_left);
            TextView txt = (TextView) view.findViewById(R.id.txt_item_left);
            TextView Eglish = (TextView) view.findViewById(R.id.txt_english);
            Eglish.setText(english[position]);
            Eglish.setTextColor(Color.WHITE);
            txt.setText(city[position]);
            txt.setTextSize(24);
            txt.setTextColor(Color.WHITE);
            imgs.setImageResource(img[position]);
            return view;
        }

    }
}
