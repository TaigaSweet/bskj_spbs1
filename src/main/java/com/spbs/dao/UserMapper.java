package com.spbs.dao;

import com.spbs.entity.User;
import org.apache.ibatis.annotations.Param;

//点击userMapper接口的左边图标按钮，出现no matching bean found
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkUseremail(String email);

    int checkUserphone(String phone);

    int checkUserPassword(String passwordOld, int id);

    User selectLoginByName(@Param("username") String username, @Param("password") String password);

    User selectLoginByPhone(@Param("username") String username, @Param("password") String password);

    User selectLoginByEmail(@Param("username") String username, @Param("password") String password);

    int regUser(User user);

    int checkUserAdmin(@Param("username") String username);

    int checkUserAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param(value = "password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param(value = "email") String email, @Param(value = "userId") Integer userId);
}