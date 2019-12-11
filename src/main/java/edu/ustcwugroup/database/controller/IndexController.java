package edu.ustcwugroup.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Haozk on 2019/12/5
 */

@Controller
public class IndexController {

    @RequestMapping({"/index","/"})
    public String index(){
        return "index";
    }
}
