package edu.ustcwugroup.database.async;

import com.alibaba.fastjson.JSONObject;
import edu.ustcwugroup.database.util.redis.JedisAdapter;
import edu.ustcwugroup.database.util.redis.JedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Haozk on 2019/12/10
 */
@Service
public class EventProducer {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel){
        try{
            String json = JSONObject.toJSONString(eventModel);
            String key = JedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key,json);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}