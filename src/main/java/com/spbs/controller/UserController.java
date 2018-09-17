package com.spbs.controller;

import com.spbs.common.*;
import com.spbs.entity.User;
import com.spbs.servers.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "user/")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录问题
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> login(String userName, String userPsw, HttpSession session) {
        System.out.println(userName + " " + userPsw);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPsw)) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.USER_PSW_NULL.getCode(), "用户名或者密码为空");
        }
        ServerSponse<User> response = userService.login(userName, userPsw);
        if (response.isSuccess()) {
            Date date = response.getData().getUpdateTime();
            DateTimeUtil.dateToStr(date);
            System.out.println(date + "  " + DateTimeUtil.dateToStr(date));
            session.setAttribute(Coust.CURRENT_USER, response.getData());
        }
        return response;
    }

    //检查用户名是否重复
    @RequestMapping(value = "check_username.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> check_username(String username) {
        System.out.println(username);
        ServerSponse<String> response = userService.checkUsernames(username);
        System.out.println(response);
        return response;
    }

    //检查邮箱是否重复
    @RequestMapping(value = "check_email.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> check_User_email(String email) {
        // String email=(String)request.getAttribute("email");
        System.out.println(email);
        ServerSponse<String> email_1 = userService.checkUseremail(email);
        System.out.println(email);
        System.out.println(email_1);
        return email_1;
    }

    //检查电话号码是否重复
    @RequestMapping(value = "check_phone.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> check_phone(String phone) {
        ServerSponse<String> check_user_phone = userService.checkUserphone(phone);
        return check_user_phone;
    }

    //注册用户
    @RequestMapping(value = "reg_user.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<User> reg_user_(String username, String password, String email, String phone, String question, String answer, String role) throws ParseException {
        User user = new User();
        String pass_1 = MD5Code.MD5EncodeUtf8(password);
        System.out.println(pass_1);
        int role_number = Integer.parseInt(role);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒

        user.setUsername(username);
        user.setPassword(pass_1);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role_number);
        user.setQuestion(question);
        user.setAnswer(answer);
        System.out.println(user.getUsername() + "\n" + pass_1 + "\t" + user.getPassword());
        ServerSponse<User> reg_user = userService.reg(user);
        return reg_user;
    }

    public ServerSponse<String> isAdmin(HttpSession session) {
        User user = (User) session.getAttribute(Coust.CURRENT_USER);
        if (user == null) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(), "您好需要登录才可以操作");
        }
        return userService.checkUserAdmin(user);
    }

    //检查用户名、密保问题和答案是否一致
    @RequestMapping(value = "check_userAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> checkAnswer(HttpSession session, String username, String question, String answer) {

        return userService.checkUserAnswer(username, question, answer);
    }

    //重置密码-->忘记密码的时候，直接重置
    @RequestMapping(value = "forget_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    //修改密码
    @RequestMapping(value = "set_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> updateSetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Coust.CURRENT_USER);
        if (user == null) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(), "需要登录才可以操作");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }

    //登录出去
    @RequestMapping(value = "login_out.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> loginOut(HttpSession session) {
        User user = (User) session.getAttribute(Coust.CURRENT_USER);
        if (user != null) {
            session.removeAttribute(Coust.CURRENT_USER);
            return ServerSponse.createBySuccessMessage("退出登录成功");
        }
        return ServerSponse.createByErrorMessage("退出失败，需要登录才可以操作");
    }

    //用户修改
    public ServerSponse<User> updateUsers(String username, String email, String phone, String question, String answer, HttpSession session) {
        User user = (User) session.getAttribute(Coust.CURRENT_USER);
        if (user == null) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(), "需要强制登录");
        }
        User user1 = new User();
        user1.setUsername(username);
        user1.setPhone(phone);
        user1.setEmail(email);
        user1.setQuestion(question);
        user1.setAnswer(answer);
        ServerSponse<User> serverSponse = userService.reg(user1);
        return null;
    }

}

