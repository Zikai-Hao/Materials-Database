package edu.ustcwugroup.database.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Haozk on 2019/12/10
 */
public class JsonUtil {
    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        /*for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }*/
        json.put("data",map);
        return json.toJSONString();
    }
}
