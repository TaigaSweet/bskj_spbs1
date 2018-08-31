package com.spbs.controller;

import com.google.gson.Gson;
import com.spbs.common.Coust;
import com.spbs.common.MD5Code;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.dao.UserMapper;
import com.spbs.entity.User;
import com.spbs.servers.UserServer;
import net.sf.jsqlparser.schema.Server;
import org.codehaus.jackson.map.util.JSONPObject;
import org.joda.time.DateTime;
import org.omg.CORBA.ServerRequest;
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
import java.util.UUID;

@Controller
@RequestMapping(value = "user/")
public class UserController {
    @Autowired
    private UserServer userServer;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> login(String username, String password, HttpSession session)throws Exception{
        System.out.println(username+" "+password);
        ServerSponse<User> response = userServer.login(username,password);
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

    @RequestMapping(value = "check_phone.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> check_phone(String phone){
        ServerSponse<String> check_user_phone=userServer.checkUserphone(phone);
        return check_user_phone;
    }
    @RequestMapping(value = "reg_user.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> reg_user_(String username, String password, String email, String phone, String question, String answer,String role,String create_time,String update_time) throws ParseException {
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
        ServerSponse<User> reg_user=userServer.reg(user);
        return reg_user;
    }
    @RequestMapping(value = "upload_path.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> upload_path_(String img_path,HttpServletRequest request) throws ParseException {
        System.out.println("进入控制层...");
        System.out.println(img_path);
        String path=request.getSession().getServletContext().getRealPath("uploadfile\\");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        return null;
    }
    public ServerSponse<String> isAdmin(HttpSession session){
        User user=(User)session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您好需要登录才可以操作");
        }
        return userServer.checkUserAdmin(user);
    }
    @RequestMapping(value = "check_userAnswer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> checkAnswer(HttpSession session,String username,String question,String answer){

        return userServer.checkUserAnswer(username,question,answer);
    }
    @RequestMapping(value = "forget_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        return userServer.forgetResetPassword(username, passwordNew, forgetToken);
    }
    @RequestMapping(value = "set_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> updateSetPassword(HttpSession session,String username,String passwordNew,String forgetToken){
        User user=(User)session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"需要登录才可以操作");
        }
        return userServer.resetPassword(username, passwordNew,user);
    }
}














