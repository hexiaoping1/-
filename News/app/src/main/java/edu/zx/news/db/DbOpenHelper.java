package edu.zx.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "news.db";
    private static final String TBL_DETAILNEWS = "news";
    private static final String TBL_DETAILNEWS_COLUMN_TYPE ="type" ;
    private static final String TBL_DETAILNEWS_COLUMN_NID ="nid";
    private static final String TBL_DETAILNEWS_COLUMN_STAMP ="stamp";
    private static final String TBL_DETAILNEWS_COLUMN_ICON ="icon";
    private static final String TBL_DETAILNEWS_COLUMN_TITLE ="title";
    private static final String TBL_DETAILNEWS_COLUMN_SUMMARY ="summary";
    private static final String TBL_DETAILNEWS_COLUMN_LINK ="link";

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DbOpenHelper(Context context){
        super(context,DBNAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer strbf=new StringBuffer();
        strbf.append("create table if not exists ");
        strbf.append(TBL_DETAILNEWS+"(");
        strbf.append(TBL_DETAILNEWS_COLUMN_TYPE+" integer,");
        strbf.append(TBL_DETAILNEWS_COLUMN_NID+" varchar(100)primary key,");
        strbf.append(TBL_DETAILNEWS_COLUMN_STAMP+" varchar(100),");
        strbf.append(TBL_DETAILNEWS_COLUMN_ICON+" varchar(100),");
        strbf.append(TBL_DETAILNEWS_COLUMN_TITLE+" varchar(100),");
        strbf.append(TBL_DETAILNEWS_COLUMN_SUMMARY+" varchar(4000),");
        strbf.append(TBL_DETAILNEWS_COLUMN_LINK+" varchar(100)");
        strbf.append(")");
        db.execSQL(strbf.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sq="drop table if exist"+TBL_DETAILNEWS;
        db.execSQL(sq);
        onCreate(db);
    }
}