package com.spbs.servers;

import com.spbs.common.ServerSponse;
import com.spbs.entity.User;
import net.sf.jsqlparser.schema.Server;
import org.apache.ibatis.annotations.Param;

public interface UserServer {
    ServerSponse<User> login(String username, String password);

    ServerSponse<String> checkUsernames(String username);

    ServerSponse<String> checkUseremail(String email);
    ServerSponse<String> checkUserAdmin(User user);
    ServerSponse<String> checkUserphone(String phone);
    ServerSponse<String> checkUserAnswer(String username,String question,String answer);
    ServerSponse<User> reg(User user);
    ServerSponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServerSponse<String> resetPassword(String passwordOld,String passwordNew,User user);

}
