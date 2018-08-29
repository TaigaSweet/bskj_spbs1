package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.entity.Category;
import com.spbs.entity.User;
import com.spbs.servers.CategoryServer;
import com.spbs.servers.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private UserServer userServer;
    @Autowired
    private CategoryServer categoryServer;

    @RequestMapping(value = "/items_cate.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse item_category(HttpSession session, Integer parent){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        return categoryServer.getChildrenParallelCategory(parent);
    }
}
