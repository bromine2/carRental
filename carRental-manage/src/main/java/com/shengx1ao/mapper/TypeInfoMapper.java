package com.shengx1ao.mapper;

import com.shengx1ao.entity.TypeInfo;
import com.shengx1ao.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TypeInfoMapper extends Mapper<TypeInfo> {

    List<TypeInfo> findByName(@Param("name") String name);
}