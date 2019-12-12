package edu.ustcwugroup.database.controller;

import edu.ustcwugroup.database.service.MoleculeService;
import edu.ustcwugroup.database.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Haozk on 2019/12/11
 */
@Controller
public class EditController {
    private final static Logger logger = LoggerFactory.getLogger(EditController.class);

    @Autowired
    MoleculeService moleculeService;

    @RequestMapping({"/dataEdit"})
    public String adminSearch(Model model, @RequestParam("id") String id){
        try {
            model.addAttribute("id",id);
        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }

        return "dataEdit";
    }

    @PostMapping(path = {"/adminDataEdit/"})
    @ResponseBody
    public String adminSearch(Model model,@RequestParam("id") int id,@RequestParam("elements") String elements,
                              @RequestParam("formula") String formula){
        try {
            int result= moleculeService.updateMolecule(id, elements, formula);
            return JsonUtil.getJSONString(result,result+"");
        }catch (Exception e){
            logger.error("keyword搜索失败"+e.getMessage());
        }

        return "error";
    }
}
