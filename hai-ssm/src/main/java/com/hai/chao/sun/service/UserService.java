package com.hai.chao.sun.service;

import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.vo.EasyUiPageResult;

public interface UserService {

    /**
     * 分页查询用户
     * @param pageNum页码
     * @param pageSize每页显示条数
     * @return
     */
    public EasyUiPageResult<User> querAllUser(Integer pageNum, Integer pageSize);

    /**
     * 保存用户
     * @param user
     * @return 
     */
    public Integer saveUser(User user);

}
