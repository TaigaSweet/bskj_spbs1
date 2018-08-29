package com.spbs.servers;

import com.spbs.common.ServerSponse;
import com.spbs.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserServer {
    ServerSponse<User> login(String username, String password);

    ServerSponse<String> checkUsernames(String username);

    ServerSponse<String> checkUseremail(String email);
    ServerSponse<String> checkUserAdmin(User user);
    ServerSponse<String> checkUserphone(String phone);
    ServerSponse<User> reg(User user);

}
