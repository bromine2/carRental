package com.shengx1ao.mapper;

import com.shengx1ao.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderInfoMapper extends Mapper<OrderInfo> {
    @Select("select * from order_info where orderId=#{orderId}")
    List<OrderInfo> findByOrderId(@Param("orderId") String orderId);

//    根据终端用户id查询订单列表
    List<OrderInfo> findByEndUserId(@Param("userId")Long userId,@Param("state")String state);

    @Select("select * from order_info where Id=#{id}")
    OrderInfo findById(@Param("id") Long id);

    @Update("update order_info set state=#{state} where id=#{Id}")
    void updateState(@Param("Id") Long id,@Param("state") String state);

    void deleteById(Long id);


}