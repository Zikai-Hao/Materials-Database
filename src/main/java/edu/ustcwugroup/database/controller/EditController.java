package edu.ustcwugroup.database.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Haozk on 2019/12/11
 */
@Controller
public class EditController {
    private final static Logger logger = LoggerFactory.getLogger(EditController.class);
    @RequestMapping({"/dataEdit"})
    public String adminSearch(Model model, @RequestParam("toolSceneId") String toolSceneId
            , @RequestParam("id") String id){
        try {
            model.addAttribute("id",id);
        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }

        return "dataEdit";
    }
}
