package com.spbs.controller;

import com.google.gson.Gson;
import com.spbs.common.Coust;
import com.spbs.common.MD5Code;
import com.spbs.common.ServerSponse;
import com.spbs.dao.UserMapper;
import com.spbs.entity.User;
import com.spbs.servers.UserServer;
import net.sf.jsqlparser.schema.Server;
import org.codehaus.jackson.map.util.JSONPObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public ServerSponse<String> check_User_email(String email){
       // String email=(String)request.getAttribute("email");
        System.out.println(email);
        ServerSponse<String> email_1= userServer.checkUseremail(email);
        System.out.println(email);
        System.out.println(email_1);
        return email_1;
    }
    @RequestMapping(value = "reg_user.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> reg_user_(String username, String password, String email, String phone, String question, String answer,String role,String create_time,String update_time) throws ParseException {
        User user=new User();
        String pass_1=MD5Code.MD5EncodeUtf8(password);
        int role_number=Integer.parseInt(role);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        Date utilDate = sdf.parse(create_time);
        Date utilDate_ = sdf.parse(update_time);
        System.out.println(utilDate);//查看utilDate的值
        user.setUsername(username);user.setPassword(pass_1);user.setEmail(email);user.setPhone(phone);user.setRole(role_number);
        user.setQuestion(question);user.setAnswer(answer);user.setCreateTime(utilDate);user.setUpdateTime(utilDate_);
        System.out.println(user.getUsername()+"\n"+pass_1+"\t"+user.getPassword());
        ServerSponse<String> reg_user=userServer.reg(user);
        return reg_user;
    }
}














