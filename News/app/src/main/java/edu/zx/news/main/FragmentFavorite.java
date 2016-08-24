package edu.zx.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.zx.news.R;
import edu.zx.news.adapter.NewsAdapter;
import edu.zx.news.db.NewsDBManager;
import edu.zx.news.entity.News;

/**
 * Created by Administrator on 2016/7/31.
 */
public class FragmentFavorite  extends Fragment {
    private View view;
    private ListView listView;
    NewsAdapter adapter;
    NewsDBManager db;
    private ArrayList<News> data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favorite,container,false);
        listView=(ListView) view.findViewById(R.id.favorite);
        adapter=new NewsAdapter(getActivity());
        db=new NewsDBManager(getActivity());
        data=db.queryLoveNews();
        adapter.addNewData(data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //打开显示当前选中的新闻
                String link=data.get(position).getLink();
                News news = (News) data.get(position);
                Intent intent=new Intent(getActivity(), WebActivity.class);
                intent.putExtra("link",link);
                getActivity().startActivity(intent);
            }
        });
/*  加载数据库*/

        return view;

    }
    /**
     *
     * 从数据库中加载保存的新闻
     * */
//    private ArrayList<News> loadLoveNews() {
//        ArrayList<News> data=new
//                NewsDBManager(getActivity()).queryLoveNews();
//        adapter.addNewData(data);
//        return data;
//    }
}
