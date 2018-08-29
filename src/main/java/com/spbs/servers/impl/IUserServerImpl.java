package com.spbs.servers.impl;

import com.spbs.common.Coust;
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
        //String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";String ph = "^[1][34578]\\d{9}$"; phoneNo.matches(ph)
        String login_Email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String login_Phone = "^[1][34578]\\d{9}$";
        User user  =null;
        if (username.matches(login_Email)){
            user  = userMapper.selectLoginByEmail(username,md5Password);
        }else if(username.matches(login_Phone)){
            user  = userMapper.selectLoginByPhone(username,md5Password);
        }else {
            user  = userMapper.selectLoginByName(username,md5Password);
        }
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
    public ServerSponse<String> checkUserAdmin(User user) {
        if (user.getRole().intValue()==Coust.Role.ROLE_ADMIN){
            return ServerSponse.createBySuccessMessage("管理员");
        }
        return ServerSponse.createBySuccessMessage("普通用户");
    }

    @Override
    public ServerSponse<String> checkUserphone(String phone) {
        int count =userMapper.checkUserphone(phone);
        if (count>0){
            return ServerSponse.createByErrorMessage("电话号码已存在，");
        }
        return ServerSponse.createBySuccessMessage("电话号码不存在");
    }

    @Override
    public ServerSponse<User> reg(User user) {
        if (user.getQuestion().equalsIgnoreCase(user.getAnswer())){
            return ServerSponse.createByErrorMessage("问题和答案不能一致！");
        }
        else{
            int count=userMapper.insert(user);
            if (count>0){
                return ServerSponse.createBySuccessMessage("注册成功！");
            }
            return ServerSponse.createByErrorMessage("注册失败");
        }
    }
}
