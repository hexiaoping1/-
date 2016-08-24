package edu.zx.news.entity;

/**
 * Created by Administrator on 2016/7/25.
 */
public class LogMsg {
    /**
     * message : OK
     * status : 0
     * data : {"result":0,"token":"9186150f34e3434d5c307c2a2af90116","explain":"登录成功"}
     */

    private String message;
    private int status;
    /**
     * result : 0
     * token : 9186150f34e3434d5c307c2a2af90116
     * explain : 登录成功
     */

    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int result;
        private String token;
        private String explain;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
