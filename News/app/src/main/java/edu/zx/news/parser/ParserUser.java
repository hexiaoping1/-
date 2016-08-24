package edu.zx.news.parser;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import edu.zx.news.base.BaseEntity;
import edu.zx.news.entity.A123;
import edu.zx.news.entity.ComMsg;
import edu.zx.news.entity.Commit;
import edu.zx.news.entity.LogMsg1;
import edu.zx.news.entity.Userlog1;

public class ParserUser {
    /**
     * 解析用户注册返回信息
     * 登录数据
     *
     * @param json 老师的parserLogin
     * @return BaseEntity<LoginMsg>对象
     */
    public static BaseEntity<LogMsg1> parserRegister(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new
                TypeToken<BaseEntity<LogMsg1>>() {
                }.getType());
    }

    //    /**
//     *  解析用户中心数据
//     * @param json
//     * @return BaseEntity<User>对象
//     */
    public static BaseEntity<Userlog1> parserUser(String json) {
        return new Gson().fromJson(json, new
                TypeToken<BaseEntity<Userlog1>>() {
                }.getType());
    }

    public static BaseEntity<List<A123>> parserCom(String json) {
        return new Gson().fromJson(json, new
                TypeToken<BaseEntity<List<A123>>>() {
                }.getType());
    }

    public static BaseEntity<Commit> parserSend(String json) {
        return new Gson().fromJson(json, new
                TypeToken<BaseEntity<Commit>>() {
                }.getType());
    }
//    /**
//     *  解析上传用户头像
//     * @param json
//     * @return BaseEntity<Register>
//     */
//    public static BaseEntity<Register> parserUploadImage(String json){
//        return new Gson().fromJson(json, new
//                TypeToken<BaseEntity<Register>>(){}.getType());
//    }
}
