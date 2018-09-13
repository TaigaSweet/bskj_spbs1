package com.spbs.servers.impl;

import com.spbs.common.Coust;
import com.spbs.common.ForgetToken;
import com.spbs.common.MD5Code;
import com.spbs.common.ServerSponse;
import com.spbs.dao.UserMapper;
import com.spbs.entity.User;
import com.spbs.servers.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "iUserService")
public class IUserServerImpl implements UserService {

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

            int count=userMapper.insert(user);
            if (count>0){
                return ServerSponse.createBySuccessMessage("注册成功！");
            }
            return ServerSponse.createByErrorMessage("注册失败");

    }

    @Override
    public ServerSponse<String> checkUserAnswer(String username, String question, String answer) {
        int count=userMapper.checkUserAnswer(username,question,answer);
        if (count>0){
            String tokenId=UUID.randomUUID().toString();
            ForgetToken.setKey(ForgetToken.TOKEN_PREFIX+username,tokenId);
            return ServerSponse.createBySuccessMessage(tokenId);
        }
        return ServerSponse.createByErrorMessage("输入错误请仔细核对");
    }

    @Override
    public ServerSponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)){
            return ServerSponse.createByErrorMessage("需要传递参数，表示需要进行答案密码校验");
        }
        int resultCount = userMapper.checkUsername(username);
        if (resultCount<1){
            return ServerSponse.createByErrorMessage("用户名不存在，请仔细核对");
        }
        String tokenName=ForgetToken.getKey(ForgetToken.TOKEN_PREFIX+username);
        if (StringUtils.isBlank(tokenName)){
            return ServerSponse.createByErrorMessage("您当前的Token不存在或者以失效");
        }
        if (StringUtils.equals(forgetToken,tokenName)){
            String passwordMD5=MD5Code.MD5EncodeUtf8(passwordNew);
            int countByUpdate=userMapper.updatePasswordByUsername(username,passwordMD5);
            if (countByUpdate>0){
                return ServerSponse.createBySuccessMessage("修改密码成功");
            }
            return ServerSponse.createByErrorMessage("token失效");
        }
        return ServerSponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerSponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        String oldPassword=MD5Code.MD5EncodeUtf8(passwordOld);
        int countOld=userMapper.checkPassword(oldPassword,user.getId());
        if (countOld<=0){
            return ServerSponse.createByErrorMessage("密码错误，请仔细核对在输入");
        }
        user.setPassword(MD5Code.MD5EncodeUtf8(passwordNew));
        user.setId(user.getId());
        int countNew=userMapper.updateByPrimaryKeySelective(user);
        if (countNew>0){
            return ServerSponse.createBySuccessMessage("密码修改成功");
        }
        return ServerSponse.createByErrorMessage("密码修改失败");
    }
}
