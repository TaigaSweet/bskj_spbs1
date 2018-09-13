package com.spbs.controller;

import com.spbs.common.Coust;
import com.spbs.common.ServerSponse;
import com.spbs.common.Sta_Type;
import com.spbs.entity.Product;
import com.spbs.entity.User;
import com.spbs.servers.ProductManagerService;
import com.spbs.servers.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping(value = "product/Manager/")
public class ProductManagerController {
    @Autowired
    private ProductManagerService productManagerService;
    @Autowired
    private UserService userService;

    public ServerSponse<Product> addProduct(HttpSession session){
        User user=(User)session.getAttribute(Coust.CURRENT_USER);
        if (user==null){
            return  ServerSponse.createByErrorCodeMessage(Sta_Type.NEED_LOGIN.getCode(),"需要强制登录");
        }
        if (user.getRole()==Coust.Role.ROLE_ADMIN){
        }
        return ServerSponse.createByErrorMessage("你没有权限");
    }
    @RequestMapping(value = "uploadPicture.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerSponse<String> addPicture(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request){
        File targetFile=null;
        String msg="";//返回存储路径
        int code=1;
        System.out.println(file);
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        System.out.println(fileName);
        if(fileName!=null&&fileName!=""){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"uploadfile/img/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("uploadfile/img"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
            //先判断文件是否存在
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(date);
            File file1 =new File(path+"/"+fileAdd);
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }
            targetFile = new File(file1, fileName);
//          targetFile = new File(path, fileName);
            try {
                file.transferTo(targetFile);
//              msg=returnUrl+fileName;
                msg=returnUrl+fileAdd+"/"+fileName;
                code=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }return null;
    }
}
