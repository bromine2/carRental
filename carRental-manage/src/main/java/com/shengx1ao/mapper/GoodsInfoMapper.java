package com.shengx1ao.mapper;

import com.shengx1ao.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GoodsInfoMapper extends Mapper<GoodsInfo> {

    List<GoodsInfo> findByName(@Param("name") String name,@Param("id") Long id);

    @Select("select * from goods_info where recommend='是'")
    List<GoodsInfo> findRecommendGoods();
//    查询热门商品，即根据销量排序

    @Select("select * from goods_info order by sales desc ")
    List<GoodsInfo> findHotSaleGoods();

    @Select("select * from goods_info where typeId=#{typeId}")
    List<GoodsInfo> findByType(@Param("typeId") Integer typeId);
}