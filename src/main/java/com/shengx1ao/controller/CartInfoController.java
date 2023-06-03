package com.shengx1ao.controller;

import com.shengx1ao.common.Result;
import com.shengx1ao.entity.CartInfo;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.service.CartInfoService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//购物车控制器
@RestController
@RequestMapping(value = "/cartInfo")
public class CartInfoController {

    @Resource
    private CartInfoService cartInfoService;

    //添加购物车
    @PostMapping
    public Result<CartInfo> add(@RequestBody CartInfo cartInfo){
        return Result.success(cartInfoService.add(cartInfo));
    }

    //查询某用户的购物车
    @GetMapping
    public Result<List<GoodsInfo>> findAll(@RequestParam Long userId){
        return Result.success(cartInfoService.findAll(userId));
    }
    @DeleteMapping("/goods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable  Long userId,@PathVariable Long goodsId){
        cartInfoService.deleteGoods(userId,goodsId);
        return Result.success();
    }
}
