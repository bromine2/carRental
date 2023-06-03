package com.shengx1ao.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Common;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.entity.OrderGoods;
import com.shengx1ao.entity.OrderInfo;
import com.shengx1ao.entity.UserInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.mapper.OrderGoodsMapper;
import com.shengx1ao.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderInfoService {
//    下单
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private CartInfoService cartInfoService;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;


    @Transactional
    public OrderInfo add(OrderInfo orderInfo){
        //1.生成最基本的订单信息放入OrderInfo
        Long userId=orderInfo.getUserid();
        String orderId=userId+ DateUtil.format(new Date(),"yyyyMMddHHmm")+ RandomUtil.randomNumbers(4);
        orderInfo.setOrderid(orderId);
        UserInfo userInfo=userInfoService.findById(userId);
        orderInfo.setLinkaddress(userInfo.getAddress());
        orderInfo.setLinkman(userInfo.getName());
        orderInfo.setLinkphone(userInfo.getPhonenumber());


        //2.保存订单表
        orderInfo.setCreatetime(DateUtil.formatDate(new Date()));
        orderInfoMapper.insertSelective(orderInfo);
        List<OrderInfo> orderInfo1=orderInfoMapper.findByOrderId(orderId);

        //3.查询订单中的商品列表
        List<GoodsInfo> goodsList=orderInfo.getGoodsList();
        for(GoodsInfo orderGoodsVO: goodsList){
            Long goodsId=orderGoodsVO.getId();
            GoodsInfo goodsDetail=goodsInfoService.findById(goodsId);
            if(goodsDetail==null){
                continue;
            }
            Integer orderCount=orderGoodsVO.getCount()==null?0:orderGoodsVO.getCount();
            Integer goodsCount=goodsDetail.getCount()==null?0:goodsDetail.getCount();
            //4.减库存
            if(orderCount>goodsCount){
                throw new CustomException(ResultCode.ORDER_PAY_ERROR);
            }
            goodsDetail.setCount(goodsCount-orderCount);

            //5.增加销量
            int sales=goodsDetail.getSales()==null?0:goodsDetail.getSales();
            goodsDetail.setSales(sales+orderCount);
            goodsInfoService.update(goodsDetail);
            //6.订单关联
            OrderGoods orderGoods=new OrderGoods();
            orderGoods.setOrderid(orderInfo1.get(0).getId());
            orderGoods.setGoodsid(goodsId);
            orderGoods.setCount(orderCount);
            orderGoodsMapper.insertSelective(orderGoods);
        }
        //7.清空购物车
        cartInfoService.empty(userId);

        return orderInfo;

    }

//    根据用户id查询订单状态
    public PageInfo<OrderInfo> findFrontPages(Long userId,String state,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OrderInfo> orderInfos;
        if(userId==null){
            orderInfos=new ArrayList<>();
        }else {
            orderInfos=orderInfoMapper.findByEndUserId(userId,state);
        }
        for(OrderInfo orderInfo:orderInfos){
            packOrder(orderInfo);
        }
        return PageInfo.of(orderInfos);
    }

//    包装订单用户与商品信息
    private void packOrder(OrderInfo orderInfo){
        //用户信息
        orderInfo.setUserInfo(userInfoService.findById(orderInfo.getUserid()));
        List<GoodsInfo> goodsInfoList=new ArrayList<>();
        //商品信息
        Long orderId=orderInfo.getId();
        List<OrderGoods> rels = orderGoodsMapper.findByOrderid(orderId);
        for(OrderGoods rel:rels){
            GoodsInfo goodsInfo=goodsInfoService.findById(rel.getGoodsid());
            if(goodsInfo!=null){
                goodsInfo.setCount(rel.getCount());
                goodsInfoList.add(goodsInfo);
            }
        }
        orderInfo.setGoodsList(goodsInfoList);

    }
    //改变订单状态
    public void changeState(Long id,String state){
        OrderInfo order=orderInfoMapper.findById(id);
        Long userId=order.getUserid();
        UserInfo user=userInfoService.findById(userId);
        if(state.equals("待提车")){
            Double account=user.getAccount();
            Double totalPrice=order.getTotalprice();
            if(account<totalPrice){
                throw new CustomException("-1","账户余额不足");
            }
            user.setAccount(user.getAccount()-order.getTotalprice());
            userInfoService.update(user);

        }
        orderInfoMapper.updateState(id,state);
    }

//    分页查询订单列表
    public PageInfo<OrderInfo> findPages(Long userId, Integer pageNum, Integer pageSize, HttpServletRequest request){
        UserInfo user=(UserInfo)request.getSession().getAttribute(Common.USER_INFO);
        if(user==null){
            throw new CustomException("1001","session失效，需重新登陆");
        }
        Integer level=user.getLevel();
        PageHelper.startPage(pageNum,pageSize);
        List<OrderInfo> orderInfos;
        if(level==1){
            orderInfos=orderInfoMapper.selectAll();
        }else if(userId!=null){
            orderInfos=orderInfoMapper.findByEndUserId(userId,null);
        }else {
            orderInfos=new ArrayList<>();
        }
        for(OrderInfo orderInfo:orderInfos){
            packOrder(orderInfo);
        }
        return PageInfo.of(orderInfos);
    }

    @Transactional
    public void delete(Long id){
        orderInfoMapper.deleteById(id);
        orderGoodsMapper.deleteByOrderid(id);
    }

    public OrderInfo findById(Long id) {
        OrderInfo orderInfo=orderInfoMapper.selectByPrimaryKey(id);
        packOrder(orderInfo);
        return orderInfo;
    }
}
