package edu.ustcwugroup.database.async.handler;

import edu.ustcwugroup.database.async.EventHandler;
import edu.ustcwugroup.database.async.EventModel;
import edu.ustcwugroup.database.async.EventType;
import edu.ustcwugroup.database.model.Molecule;
import edu.ustcwugroup.database.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Haozk on 2019/12/10
 */
public class SearchHandler implements EventHandler {
    private final static Logger logger = LoggerFactory.getLogger(SearchHandler.class);

    @Autowired
    SearchService searchService;

    @Override
    public void doHandle(EventModel model) {
        logger.info("开始搜索");
        Map<String,String> map = new HashMap<>();



    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(new EventType[]{EventType.SEARCH});
    }
}
