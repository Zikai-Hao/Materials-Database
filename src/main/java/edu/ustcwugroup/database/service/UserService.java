package edu.ustcwugroup.database.service;

import edu.ustcwugroup.database.dao.LoginTicketDAO;
import edu.ustcwugroup.database.dao.UserDAO;
import edu.ustcwugroup.database.model.LoginTicket;
import edu.ustcwugroup.database.model.User;
import edu.ustcwugroup.database.util.DemoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Haozk on 2019/12/27
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    LoginTicketDAO loginTicketDAO;

    public User selectUserById(int id){
        return userDAO.selectByid(id);
    }

    public User selectUserByName(String name){
        return userDAO.selectByName(name);
    }

    //用户注册
    public Map<String,String> register(String username, String password){
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("msg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        if(user!=null){
            map.put("msg","用户名已经被注册");
            return map;
        }
        if(!username.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")){
            map.put("msg","邮箱格式不正确");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(DemoUtil.MD5(password+user.getSalt()));
        user.setEmail(username);
        user.setStatus(0);
        user.setCreatedDate(new Date());
        userDAO.addUser(user);
        int userId=userDAO.selectByName(username).getId();
        String ticket = addLoginTicket(userId);
        map.put("ticket", ticket);
        return map;

    }
    //登录ticket添加
    public String addLoginTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();
        now.setTime(3600*24*1000*15+now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDAO.addTicket(loginTicket);
        return loginTicket.getTicket();
    }

}
