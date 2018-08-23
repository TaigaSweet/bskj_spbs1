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
    int cheackUseremail(String email);
    int cheackUserphone(String phone);

    User selectLogin(@Param("username") String username, @Param("password")String password);
}