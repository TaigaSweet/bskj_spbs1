package com.spbs.controller;

import com.spbs.servers.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "product/")
public class ProductController {
    @Autowired
    private ProductService productServer;
    //添加商品---后台

}
