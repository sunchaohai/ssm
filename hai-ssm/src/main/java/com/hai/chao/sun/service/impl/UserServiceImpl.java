package com.hai.chao.sun.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hai.chao.sun.mapper.UserMapper;
import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.service.UserService;
import com.hai.chao.sun.vo.EasyUiPageResult;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public EasyUiPageResult<User> querAllUser(Integer pageNum, Integer pageSize) {
        EasyUiPageResult<User> uiPage = new EasyUiPageResult<User>();
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        List<User> users = userMapper.querAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        uiPage.setRows(users);
        uiPage.setTotal(pageInfo.getTotal());

        return uiPage;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}) 
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
        
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer deleteUsers(String ids) {
        Integer[] idArr = getIntegerArr(ids);
        
        return userMapper.deleteUsers(idArr);
    }

    private Integer[] getIntegerArr(String ids) {
        Integer[] arr = new Integer[ids.length()];
        String[] idsArrStr = ids.split(",");
        for (int i = 0; i<idsArrStr.length; i++) {
            String id = idsArrStr[i];
            arr[i]=Integer.valueOf(id);
        }
        return arr;
    }


}
