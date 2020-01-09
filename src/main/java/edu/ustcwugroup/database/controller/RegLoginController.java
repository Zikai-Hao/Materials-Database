package edu.ustcwugroup.database.controller;

import edu.ustcwugroup.database.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haozk on 2020/1/5
 */
@Controller
public class RegLoginController {
    private final static Logger logger = LoggerFactory.getLogger(RegLoginController.class);

    @Autowired
    UserService userService;




    /*
    @RequestMapping(path={"/reg/"},method = {RequestMethod.POST})
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("emailAddr") String emailAddr,
                      @RequestParam(value = "next",required = false) String next,
                      HttpServletResponse response){


        try {
            Map<String,String> map = userService.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                eventProducer.fireEvent(new EventModel(EventType.REGIS)
                        .setExt("username", username).setExt("email", emailAddr)
                        .setActorId(2));
                if(StringUtils.isNotBlank(next)){
                    return "redirect:"+next;
                }

                return "redirect:/";
            }else {
                model.addAttribute("msg", map.get("msg"));
                model.addAttribute("type", "reg");
                return "login";
            }


        }catch(Exception e){
            logger.error("注册异常"+e.getMessage());
            return "login";
        }

    }*/
    @RequestMapping({"/reg"})
    public String regPage(){
        return "reg";
    }

    @RequestMapping(path={"/reg/"},method ={RequestMethod.POST})
    public String reg(Model model,@RequestParam("username") String username,
                          @RequestParam("password") String password,
                      @RequestParam(value = "next",required = false) String next,
                      HttpServletResponse response){
        try {
            Map<String,String> map = userService.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                /*eventProducer.fireEvent(new EventModel(EventType.REGIS)
                        .setExt("username", username).setExt("email", emailAddr)
                        .setActorId(2));*/
                if(StringUtils.isNotBlank(next)){
                    return "redirect:"+next;
                }

                return "redirect:/";
            }else {
                model.addAttribute("msg", map.get("msg"));
                return "reg";
            }


        }catch(Exception e){
            logger.error("注册异常"+e.getMessage());
            return "reg";
        }
    }
}
