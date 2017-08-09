package com.hai.chao.sun.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.parsing.ParsingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hai.chao.sun.mapper.UserMapper;
import com.hai.chao.sun.mapper.UserMapperCommon;
import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.service.UserService;
import com.hai.chao.sun.vo.EasyUiPageResult;

@Service
public class UserServiceImpl implements UserService{
    /*@Autowired
    private UserMapper userMapper;

    @Override
    public EasyUiPageResult<User> queryAllUser(Integer pageNum, Integer pageSize) {
        EasyUiPageResult<User> uiPage = new EasyUiPageResult<User>();
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        List<User> users = userMapper.queryAllUser();
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

    @Override
    @Transactional(rollbackFor = {Exception.class}) 
    public Integer updateUserByUserId(User user) {
        return userMapper.updateUserByUserId(user);
    }

    @Override
    public Integer deleteUsers2(Integer[] ids) {
        return userMapper.deleteUsers(ids);
    }*/
    
    
    @Autowired
    private UserMapperCommon userMapperCommon;

    @Override
    public EasyUiPageResult<User> queryAllUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(User.class);
        example.setOrderByClause("created desc");
        List<User> users = userMapperCommon.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        
        EasyUiPageResult<User> easyuiPage = new EasyUiPageResult<>();
        easyuiPage.setRows(users);
        easyuiPage.setTotal(pageInfo.getTotal());
        return easyuiPage;
    }

    @Override
    public Integer saveUser(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return userMapperCommon.insert(user);
    }

    @Override
    public Integer deleteUsers(String ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateUserByUserId(User user) {
        user.setUpdated(new Date());
        return userMapperCommon.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer deleteUsers2(List<Object> ids) {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        
        return userMapperCommon.deleteByExample(example);
    }


}
