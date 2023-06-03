package com.shengx1ao.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.shengx1ao.entity.CartInfo;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.mapper.CartInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 购物车服务层
 */
@Service
public class CartInfoService {
    @Resource
    private CartInfoMapper cartInfoMapper;

    @Resource
    private GoodsInfoService goodsInfoService;
    /**
     * 加入购物车
     */
    public CartInfo add(CartInfo detailInfo){
        Long userId=detailInfo.getUserid();
        Long goodsId=detailInfo.getGoodsid();
        //先查询购物车是否存在该商品，若存在就更新数量，没有就新建
        Example example=new Example(CartInfo.class);
        example.createCriteria().andEqualTo("userid",userId).andEqualTo("goodsid",goodsId);
        List<CartInfo> infos= cartInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(infos)){
            detailInfo.setCreatetime(DateUtil.formatTime(new Date()));
            cartInfoMapper.insertSelective(detailInfo);
        }else{
            CartInfo cartInfo=infos.get(0);
            cartInfo.setCount(cartInfo.getCount()+detailInfo.getCount());
            cartInfoMapper.updateByPrimaryKeySelective(cartInfo);
        }
        return detailInfo;
    }

    public List<GoodsInfo> findAll(Long userId){
        List<CartInfo> cartInfoList=cartInfoMapper.findCartByUserId(userId);
        List<GoodsInfo> goodsList=new ArrayList<>();
        for(CartInfo cartInfo:cartInfoList){
            long goodsId=cartInfo.getGoodsid();
            GoodsInfo goodsInfo=goodsInfoService.findById(goodsId);
            if(goodsInfo !=null){
                //此处的count是用户加入购物车的商品数量，不是商品的库存
                goodsInfo.setCount(cartInfo.getCount());
                //getGoodsId而不是getId
                goodsInfo.setId(cartInfo.getGoodsid());
                //
                goodsList.add(goodsInfo);
            }
        }
        return goodsList;
    }

    public void deleteGoods(Long userId,Long goodsId){
        cartInfoMapper.deleteGoods(userId,goodsId);
    }

    public void empty(Long userId){
        cartInfoMapper.deleteByUserId(userId);
    }
}