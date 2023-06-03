package com.shengx1ao.mapper;

import com.shengx1ao.entity.OrderGoods;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderGoodsMapper extends Mapper<OrderGoods> {
    //根据订单id获取商品列表
    List<OrderGoods> findByOrderid(Long orderId);

    void deleteByOrderid(Long id);
}