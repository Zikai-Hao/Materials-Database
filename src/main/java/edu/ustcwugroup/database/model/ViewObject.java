package edu.ustcwugroup.database.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haozk on 2019/12/10
 */
public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();

    public void set(String key,Object value){
        objs.put(key,value);
    }

    public Object get(String key){
        return objs.get(key);
    }

    public Map<String,Object> getObjs(){
        return objs;
    }
}
