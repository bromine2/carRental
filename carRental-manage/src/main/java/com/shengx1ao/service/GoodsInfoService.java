package com.shengx1ao.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.mapper.GoodsInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**汽车相关的service**/
@Service
public class GoodsInfoService {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    

    /**分页查询汽车列表**/
    public PageInfo<GoodsInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findByName(name,null);
        return PageInfo.of(list);
    }

    /**新增汽车**/
    public GoodsInfo add(GoodsInfo goodsInfo){
        converFileListToFields(goodsInfo);
        goodsInfoMapper.insertSelective(goodsInfo);
        return goodsInfo;
    }

    /**
     * 转换列表格式
     */
    private void converFileListToFields(GoodsInfo goodsInfo){
        List<Long> fileList=goodsInfo.getFileList();
        if(!CollectionUtil.isEmpty(fileList)){
            goodsInfo.setFields(fileList.toString());
        }
    }

    public void update(GoodsInfo goodsInfo){
        converFileListToFields(goodsInfo);
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }
    /**根据id删除汽车**/
    public void delete(Long id){
        goodsInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id获取汽车信息
     */
    public GoodsInfo findById(Long id){
        List<GoodsInfo> list=goodsInfoMapper.findByName(null,id);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 分页返回推荐商品
     */

    public PageInfo<GoodsInfo> findRecommendGoods(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findRecommendGoods();
        return PageInfo.of(list);
    }

    /**
     * 查询热门商品
     */
    public PageInfo<GoodsInfo> findHotSaleGoods(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findHotSaleGoods();
        return PageInfo.of(list);
    }

    public List<GoodsInfo> findByType(Integer typeId){
        return goodsInfoMapper.findByType(typeId);
    }
}
