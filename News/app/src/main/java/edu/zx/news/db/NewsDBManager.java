package edu.zx.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.zx.news.entity.News;
import edu.zx.news.main.MainActivity;
import edu.zx.news.main.WebActivity;
import edu.zx.news.util.LogWrapper;

/**
 * Created by Administrator on 2016/7/31.
 */
public class NewsDBManager {
DbOpenHelper dbHelper;
    Context context;
    public NewsDBManager(Context context){
        this.context=context;
    }
    public boolean saveLoveNews(Context context,News news){
        dbHelper =new DbOpenHelper(context);
        //通过读或者写获得SQlIteDabase的对象
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from news where nid="+news.getNid(),null);
        if (cursor.moveToNext()){
            cursor.close();
            return false;
        }
        cursor.close();
        ContentValues values=new ContentValues();
        values.put("type",news.getType());
        values.put("nid",news.getNid());
        values.put("stamp",news.getStamp());
        values.put("icon",news.getIcon());
        values.put("title",news.getTitle());
        values.put("summary",news.getSummary());
        values.put("link",news.getLink());
        long lon=db.insert("news",null,values);
        if (lon>0){
            db.close();
            return  true;
        }

        return false;
    }
    /**
     *  获取收藏新闻的列表
     * @return  新闻的列表
     */
    public ArrayList<News> queryLoveNews(){
        dbHelper =new DbOpenHelper(context);
        ArrayList<News> newsList=new ArrayList<News>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sqlt="select *from news order by nid desc";
        Cursor cursor=db.rawQuery(sqlt,null);
        if (cursor.moveToNext()){
            do {
                int type=cursor.getInt(cursor.getColumnIndex("type"));
                String nid=cursor.getString(cursor.getColumnIndex("nid"));
                String stamp=cursor.getString(cursor.getColumnIndex("stamp"));
                String icon=cursor.getString(cursor.getColumnIndex("icon"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String summary=cursor.getString(cursor.getColumnIndex("summary"));
                String link=cursor.getString(cursor.getColumnIndex("link"));
                News news=new News();
                news.setType(type);
                news.setIcon(icon);
                news.setLink(link);
                news.setStamp(stamp);
                news.setNid(nid);
                news.setSummary(summary);
                news.setTitle(title);
                newsList.add(news);
            }while (cursor.moveToNext());{
                cursor.close();
                db.close();
            }
        }
        return  newsList;
    }
}


