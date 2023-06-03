package com.shengx1ao.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.TypeInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.mapper.TypeInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**汽车型号相关的service**/
@Service
public class TypeInfoService {

    @Resource
    private TypeInfoMapper typeInfoMapper;
    

    /**分页查询汽车型号列表**/
    public PageInfo<TypeInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<TypeInfo> list=typeInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**新增汽车型号**/
    public TypeInfo add(TypeInfo typeInfo){
        typeInfoMapper.insertSelective(typeInfo);
        return typeInfo;
    }

    public void update(TypeInfo typeInfo){
        typeInfoMapper.updateByPrimaryKeySelective(typeInfo);
    }
    /**根据id删除汽车型号**/
    public void delete(Long id){
        typeInfoMapper.deleteByPrimaryKey(id);
    }

    public TypeInfo findById(Long id){
        return typeInfoMapper.selectByPrimaryKey(id);
    }

}
