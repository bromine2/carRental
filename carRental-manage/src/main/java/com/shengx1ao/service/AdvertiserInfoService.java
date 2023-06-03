package com.shengx1ao.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.AdvertiserInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.mapper.AdvertiserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**公告相关的service**/
@Service
public class AdvertiserInfoService {

    @Resource
    private AdvertiserInfoMapper advertiserInfoMapper;
    

    /**分页查询公告列表**/
    public PageInfo<AdvertiserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<AdvertiserInfo> list=advertiserInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**新增公告**/
    public AdvertiserInfo add(AdvertiserInfo advertiserInfo){
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        advertiserInfoMapper.insertSelective(advertiserInfo);
        return advertiserInfo;
    }

    public void update(AdvertiserInfo advertiserInfo){
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        advertiserInfoMapper.updateByPrimaryKeySelective(advertiserInfo);
    }
    /**根据id删除公告**/
    public void delete(Long id){
        advertiserInfoMapper.deleteByPrimaryKey(id);
    }
    /**根据id查询公告**/
    public AdvertiserInfo findById(Long id){
        return advertiserInfoMapper.selectByPrimaryKey(id);
    }

}
