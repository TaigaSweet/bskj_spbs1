package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.MD5Code;
import com.spbs.common.ServerSponse;
import com.spbs.dao.UserMapper;
import com.spbs.entity.User;
import com.spbs.servers.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "user/")
public class UserController {
    @Autowired
    private UserServer userServer;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> login(String username, String userPsw, HttpSession session){
        System.out.println(username+" "+userPsw);
        ServerSponse<User> response = userServer.login(username,userPsw);
        if(response.isSuccess()){
            session.setAttribute(Coust.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value = "check_username.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> check_username(String username){
        System.out.println(username);
        ServerSponse<String> response = userServer.checkUsernames(username);
        System.out.println(response);
        return response;
    }
    @RequestMapping(value = "check_email.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> cheack_Useremail(String email){
       // String email=(String)request.getAttribute("email");
        System.out.println(email);
        ServerSponse<String> email_1= userServer.checkUseremail(email);
        System.out.println(email);
        System.out.println(email_1);
        return email_1;
    }
}














