package com.hai.chao.sun.service;

import java.util.List;

import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.vo.EasyUiPageResult;

public interface UserService {

    /**
     * 分页查询用户
     * @param pageNum页码
     * @param pageSize每页显示条数
     * @return
     */
    public EasyUiPageResult<User> queryAllUser(Integer pageNum, Integer pageSize);

    /**
     * 保存用户
     * @param user:user对象
     * @return 
     */
    public Integer saveUser(User user);

    /**
     * 批量删除用户
     * @param ids 所要删除用户的id集合
     * @return
     */
    public Integer deleteUsers(String ids);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public Integer updateUserByUserId(User user);

//    public Integer deleteUsers2(Integer[] ids);
    public Integer deleteUsers2(List<Object> ids);


}
