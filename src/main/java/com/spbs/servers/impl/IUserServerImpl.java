package com.spbs.servers.impl;

import com.spbs.common.MD5Code;
import com.spbs.common.ServerSponse;
import com.spbs.dao.UserMapper;
import com.spbs.entity.User;
import com.spbs.servers.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class IUserServerImpl implements UserServer{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerSponse<User> login(String username, String password) {

        String md5Password = MD5Code.MD5EncodeUtf8(password);
        User user  = userMapper.selectLogin(username,md5Password);
        if(user == null){
            return ServerSponse.createByErrorMessage("密码错误");
        }

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerSponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerSponse<String> checkUsernames(String username) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0 ){
            return ServerSponse.createByErrorMessage("用户名不存?在");
        }
        return ServerSponse.createBySuccessMessage("存在！");
    }

    @Override
    public ServerSponse<String> checkUseremail(String email) {
        int count =userMapper.checkUseremail(email);
        if (count>0){
            return ServerSponse.createByErrorMessage("电子邮箱已存在，");
        }
        return ServerSponse.createBySuccessMessage("电子邮箱不存在");
    }

    @Override
    public ServerSponse<String> checkUserphone(String phone) {
        int count =userMapper.cheackUserphone(phone);
        if (count>0){
            return ServerSponse.createByErrorMessage("电话号码已存在，");
        }
        return ServerSponse.createBySuccessMessage("电话号码不存在");
    }


    /*public User login(String username, String userPsw) throws Exception {
        System.out.println("IUserServerImpl:"+username+"  " +userPsw);
        return userMapper.login(username,userPsw);
    }*/
}
