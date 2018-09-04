package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.entity.User;
import com.spbs.servers.CategoryService;
import com.spbs.servers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    //查询子节点的category信息,并且不递归,保持平级
    @RequestMapping(value = "/items_cate.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse item_category(HttpSession session,@RequestParam(value = "parent",defaultValue = "0") Integer parent){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        return categoryService.getChildrenParallelCategory(parent);
    }
    //通过他的主键ID查询到他对应的子类,查询当前节点的id和递归子节点的id,0->10000->100000
    @RequestMapping(value = "/items_child_cate.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse item_childCategory(HttpSession session,@RequestParam(value = "parent",defaultValue = "0") Integer parent){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        return categoryService.getChildrenParallelCategory(parent);
    }
    //添加商品分类
    @RequestMapping(value = "/add_items.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> addCategoryItem(HttpSession session,String cateName,@RequestParam(value = "cateId",defaultValue = "0") Integer cateId){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        if (user.getRole()==Coust.Role.ROLE_ADMIN){
            return categoryService.addCategoryItems(cateName,cateId);
        }
        return ServerSponse.createByErrorMessage("没有权限");
    }
    //更新商品分类
    @RequestMapping(value = "/update_items.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> updateCateItem(HttpSession session,String cateName,Integer categoryId){
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        if (user.getRole()==Coust.Role.ROLE_ADMIN){
            return categoryService.updateCategoryItems(cateName, categoryId);
        }
        return ServerSponse.createByErrorMessage("没有权限");
    }
    //删除商品分类
    @RequestMapping(value = "/delete_Items.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> deleteCategoryItem(HttpSession session,Integer categoryId){
        System.out.println(categoryId);
        User user=(User) session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"您没有登录，需要强制登录");
        }
        if (user.getRole()==Coust.Role.ROLE_ADMIN){
            return categoryService.deleteCategoryItems(categoryId);
        }
        return ServerSponse.createByErrorMessage("没有权限");
    }
}
