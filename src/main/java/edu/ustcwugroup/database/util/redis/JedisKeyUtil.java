package edu.ustcwugroup.database.util.redis;

/**
 * Created by Haozk on 2019/12/10
 */
public class JedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    public static String getEventQueueKey() {
        return BIZ_EVENTQUEUE;
    }


}
