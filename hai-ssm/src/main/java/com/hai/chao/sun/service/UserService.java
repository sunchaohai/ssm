package com.hai.chao.sun.service;

import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.vo.EasyUiPageResult;

public interface UserService {

    EasyUiPageResult<User> querAllUser(Integer pageNum, Integer pageSize);

}
