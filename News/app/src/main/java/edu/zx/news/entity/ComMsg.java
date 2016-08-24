package edu.zx.news.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ComMsg {

    /**
     * uid : 98765432
     * content : 123
     * stamp : 2016-07-28 17:25:04
     * cid : 3613
     * portrait : http://118.244.212.82:9092/Images/image.png
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String uid;
        private String content;
        private String stamp;
        private int cid;
        private String portrait;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }
}
