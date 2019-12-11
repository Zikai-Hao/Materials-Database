package edu.ustcwugroup.database.async;

import java.util.List;

/**
 * Created by Haozk on 2019/12/10
 */

public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}

