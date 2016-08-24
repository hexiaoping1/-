package edu.zx.news.entity;


import java.io.Serializable;

public class News implements Serializable{
    String summary;//摘要
    String icon;//图标
    String stamp;//时间
    String title;//标题
    String nid;//新闻的id
    String link;//新闻链接
    int type;//新闻类型
    public News(){}
public  News(int type,String nid,String stamp,String icon,String title,String summary,String link){
    this.type=type;
    this.nid=nid;
    this.stamp=stamp;
    this.icon=icon;
    this.title=title;
    this.summary=summary;
    this.link=link;
}
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}

