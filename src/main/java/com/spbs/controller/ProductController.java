package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.entity.Product;
import com.spbs.entity.User;
import com.spbs.servers.ProductService;
import com.spbs.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "product/")
public class ProductController {
    @Autowired
    private ProductService productServer;

    //添加商品---后台
    //修改商品的状态-->属于管理员权限
    public ServerSponse<String> updateProductStatus(Integer status,
                                                    HttpSession session) {
        User user = (User) session.getAttribute(Coust.CURRENT_USER);
        if (user == null) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(), "需要强制登录才可查询");
        }
        if (user.getRole() == Coust.Role.ROLE_ADMIN) {

        }
        return ServerSponse.createByErrorMessage("没有权限");
    }

    //商品的详细信息
    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerSponse<ProductDetailVo> showDetailed(Integer id,
                                                      HttpSession session) {
        /*User user=(User)session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"需要强制登录才可查询");
        }*/
        return productServer.productDetail(id);
    }

    //商品分页
    public ServerSponse<List<Product>> listProduct(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return null;
    }
}
