package com.spbs.controller;

import com.spbs.servers.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "product/")
public class ProductController {
    @Autowired
    private ProductServer productServer;
    //添加商品---后台

}
