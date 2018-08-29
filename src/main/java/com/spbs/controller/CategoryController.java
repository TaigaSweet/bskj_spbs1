package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("category")
public class CategoryController {
    public ServerSponse<String> add_category(HttpSession session,int parent,String parentName){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }

        return null;
    }
}
