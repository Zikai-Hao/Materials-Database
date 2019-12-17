package edu.ustcwugroup.database.controller;

import edu.ustcwugroup.database.model.Molecule;
import edu.ustcwugroup.database.model.ViewObject;
import edu.ustcwugroup.database.service.SearchService;
import edu.ustcwugroup.database.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Haozk on 2019/12/5
 */
@Controller
public class SearchController {
    private final static Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @RequestMapping({"/result"})
    @ResponseBody
    public String index(Model model, @RequestParam("keyword") String keyword,
                        @RequestParam("page") int page,
                        @RequestParam("limit") int limit){
        try {
            ViewObject viewObject = searchService.searchMolecules(keyword,(page-1)*limit,limit,"<em>","</em>");
            model.addAttribute("vo",viewObject);
            logger.info(""+viewObject.get("count"));
            return JsonUtil.getJSONString(0,viewObject.getObjs());

        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());

        }
        return "error";
    }
    @RequestMapping({"/search"})

    public String search(Model model, @RequestParam("keyword") String keyword){
        try {
            model.addAttribute("keyword",keyword);

        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }
        return "result";
    }

    @RequestMapping({"/adminsearch"})
    public String adminSearch(Model model, @RequestParam("keyword") String keyword){
        try {
            model.addAttribute("keyword",keyword);
        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }
        return "adminsearch";
    }

    @RequestMapping({"/detail"})
    public String detail(Model model){
        try {
            //model.addAttribute("keyword",);
        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }
        return "resultDetail";
    }

}
