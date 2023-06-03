package com.shengx1ao.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.UserInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**用户相关的service**/
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**登录**/
    public UserInfo login(String name, String password){
        List<UserInfo> list=userInfoMapper.findByName(name);
        /**判断数据库中是否有该用户**/
        if(CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        /**判断密码是否正确**/
        if(!SecureUtil.md5(password).equals(list.get(0).getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return list.get(0);

    }

    public UserInfo resetPassword(String name){
        List<UserInfo> list=userInfoMapper.findByName(name);
        /**判断数据库中是否有该用户**/
        if(CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        list.get(0).setPassword(SecureUtil.md5("njupt"));
        userInfoMapper.updateByPrimaryKeySelective(list.get(0));
        return list.get(0);
    }

    /**分页查询用户列表**/
    public PageInfo<UserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> list=userInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**新增用户**/
    public UserInfo add(UserInfo userInfo){
        List<UserInfo> list=userInfoMapper.findByName(userInfo.getName());
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        if(StrUtil.isBlank(userInfo.getPassword())){
            userInfo.setPassword(SecureUtil.md5("njupt"));
        }else {
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword()));
        }
        userInfo.setLevel(3);
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }

    public void update(UserInfo userInfo){
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
    /**根据id删除用户**/
    public void delete(Long id){
        userInfoMapper.deleteByPrimaryKey(id);
    }

    public UserInfo findById(Long id){
        return userInfoMapper.selectByPrimaryKey(id);
    }


}
