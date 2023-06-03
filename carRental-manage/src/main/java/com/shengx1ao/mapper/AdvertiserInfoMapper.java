package com.shengx1ao.mapper;

import com.shengx1ao.entity.AdvertiserInfo;
import com.shengx1ao.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdvertiserInfoMapper extends Mapper<AdvertiserInfo> {
    //根据公告标题查询
    List<AdvertiserInfo> findByName(@Param("name") String name);
}