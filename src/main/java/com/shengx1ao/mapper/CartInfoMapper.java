package com.shengx1ao.mapper;

import com.shengx1ao.entity.CartInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CartInfoMapper extends Mapper<CartInfo> {

    List<CartInfo> findCartByUserId(Long userId);


    //删除购物车内某个商品
    @Delete("delete from cart_info where userId=#{userId} and goodsId=#{goodsId}")
    int deleteGoods(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    @Delete("delete from cart_info where userId=#{userId}")
    int deleteByUserId(@Param("userId")Long userId);

}