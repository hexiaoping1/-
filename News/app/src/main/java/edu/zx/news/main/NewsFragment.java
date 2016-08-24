package edu.zx.news.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.zx.news.ListView.XListView;
import edu.zx.news.R;
import edu.zx.news.adapter.NewsAdapter;
import edu.zx.news.base.BaseEntity;
import edu.zx.news.entity.News;
import edu.zx.news.util.Constant;
import edu.zx.news.util.HttpVolley;
import edu.zx.news.util.LogWrapper;
import edu.zx.news.util.UserManager;


public class NewsFragment extends Fragment {
    List<News> news;
    List<News> news1;
    NewsAdapter adapter;
    ListView lst;//ListView新闻显示
    @Bind(R.id.btn_left)
    ImageView btnLeft;
    @Bind(R.id.btn_right)
    ImageView btnRight;
    @Bind(R.id.lin_layout)
    LinearLayout linLayout;
    @Bind(R.id.hor_scr_view_txt1)
    TextView horScrViewTxt1;
    @Bind(R.id.hor_scr_view_txt2)
    TextView horScrViewTxt2;
    @Bind(R.id.hor_scr_view_txt3)
    TextView horScrViewTxt3;
    @Bind(R.id.hor_scr_view_txt4)
    TextView horScrViewTxt4;
    @Bind(R.id.hor_scr_view)
    HorizontalScrollView horScrView;
    @Bind(R.id.txt_news)
    XListView txtNews;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        /* 添加请求 */
        HttpVolley.addRequest(getActivity(), new JsonObjectRequest(Request.Method.GET, Constant.PATH_NEWS, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject obj) {

                LogWrapper.e("tag", obj + "obj");
                Gson gson = new Gson();
                BaseEntity<List<News>> entity = gson.fromJson(obj.toString(), new TypeToken<BaseEntity<List<News>>>() {
                }.getType());
                String msg = entity.getMessage();
                int status = entity.getStatus();
                news = entity.getData();

                adapter = new NewsAdapter(getActivity());
                adapter.addNewData(news);
                txtNews.setAdapter(adapter);
                for (News n : news) {

                    LogWrapper.e("Tag", n.getSummary() + "***" + n.getTitle() + "****" + status + n.getIcon());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        }));
        txtNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                String str = news.get(position).getLink();
                String nid = news.get(position).getNid();
                String stamp = news.get(position).getStamp();
                News new1 = news.get(position);
                intent.putExtra("New", new1);
                intent.putExtra("name", str);
                intent.putExtra("nid", nid);
                intent.putExtra("stamp", stamp);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_left, R.id.btn_right})
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.btn_left:
                mainActivity.show1();
                break;
            case R.id.btn_right:
                mainActivity.show2();
                break;
        }
    }

    public void init() {

        txtNews.setPullRefreshEnable(true);
        txtNews.setPullLoadEnable(true);
        //XListViewListener：下拉刷新
        txtNews.setXListViewListener(new XListView.IXListViewListener() {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time=simpleDateFormat.format(System.currentTimeMillis());

            @Override

            public void onRefresh() {
                //   "/news_list?ver=1&subid=1&dir=2&nid=1&stamp=20140321&cnt=20"
                Map<String,String > map=new HashMap<String, String>();
                map.put("ver","1");
                map.put("subid","1");
                map.put("dir","2");
                map.put("nid",news.get(0).getNid());
                map.put("stamp","20140321");
                map.put("cnt","20");
                UserManager.getInstance(getActivity()).userLogin(Request.Method.GET, Constant.PATH_NEWS_1, map, new Response.Listener() {
                    @Override
                    public void onResponse(Object o) {
                        Gson gson1 = new Gson();
                        BaseEntity<List<News>> entity = gson1.fromJson(o.toString(), new TypeToken<BaseEntity<List<News>>>() {
                        }.getType());
                        String msg = entity.getMessage();
                        int status = entity.getStatus();
                        news = entity.getData();

                        adapter.addNewData(news);
                        txtNews.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });


                txtNews.stopRefresh();
                txtNews.setRefreshTime(time);
            }

            @Override
            public void onLoadMore() {
                Map<String,String > map=new HashMap<String, String>();
                map.put("ver","1");
                map.put("subid","1");
                map.put("dir","2");
                map.put("nid","20");
                map.put("stamp","20140321");
                map.put("cnt","20");
                UserManager.getInstance(getActivity()).userLogin(Request.Method.GET, Constant.PATH_NEWS_1, map, new Response.Listener() {
                    @Override
                    public void onResponse(Object o) {
                        Gson gson1 = new Gson();
                        BaseEntity<List<News>> entity = gson1.fromJson(o.toString(), new TypeToken<BaseEntity<List<News>>>() {
                        }.getType());
                        String msg = entity.getMessage();
                        int status = entity.getStatus();
                        news1 = entity.getData();
                        news.addAll(news1);
                        adapter.appenData(news1);
                        txtNews.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });

                txtNews.stopLoadMore();
            }
        });
    }
}

