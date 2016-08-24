package edu.zx.news.util;


/**
 * 存放常量：数据库名、表名、列名、网络地址
 *
 * @author Administrator
 *         http://118.244.212.82:9029/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20
 */
public class Constant {
    /**
     * base  path
     */
    public static final String PATH_BASE = "http://118.244.212.82:9092/newsClient";
    /**
     * login  path
     */
    public static final String PATH_LOGIN = PATH_BASE + "/user_login";
    /**
     * register  path
     */
    public static final String PATH_REGISTER = PATH_BASE + "/user_register";
    /**
     * news  list
     */
    public static final String PATH_NEWS = PATH_BASE + "/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    public static final String PATH_NEWS_1 = PATH_BASE + "/news_list";
    /**
     * Retrieve password
     */
    public static final String PATH_PWD = PATH_BASE + "/user_forgetpass";
    /**
     * user_home
     */
    public static final String PATH_USER_HOME = PATH_BASE + "/user_home";
    /**
     * comment
     */
    public static final String PATH_USER_COM = PATH_BASE + "/cmt_list";
    //http://118.244.212.82:9092/newsClient/cmt_list?ver=1&nid=1&type=1&stamp=20160617&cid=1&dir=1&cnt=20
    /**
     * send comment
     */
    public static final String PATH_USER_SEND = PATH_BASE + "/cmt_commit";

    /**
     * updata
     */
    public static final String PATH_USER_UPDATA = PATH_BASE + "/update";

}
