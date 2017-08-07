package com.hai.chao.sun.mapper;

import java.util.List;

import com.hai.chao.sun.pojo.User;

public interface UserMapper {

    public List<User> querAllUser();

    public Integer saveUser(User user);

    public Integer deleteUsers(Integer[] idArr);

}
