package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.entity.OrderInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.service.OrderInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//订单控制器
@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping
    public Result<OrderInfo> add(@RequestBody OrderInfo orderInfo){
        Long userId=orderInfo.getUserid();
        List<GoodsInfo> goodsList=orderInfo.getGoodsList();
        if(userId==null||goodsList==null||goodsList.size()==0){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        orderInfo.setState("待付款");
        return Result.success(orderInfoService.add(orderInfo));

    }

    @GetMapping("/page")
    public Result<PageInfo<OrderInfo>> findFrontPage(@RequestParam(required = false) Long userId,
                                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest request){
        return Result.success(orderInfoService.findPages(userId,pageNum,pageSize,request));
    }

    @GetMapping("/page/front")
    public Result<PageInfo<OrderInfo>> findFrontPage(@RequestParam(required = false) Long userId,
                                                     @RequestParam(required = false) String state,
                                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return Result.success(orderInfoService.findFrontPages(userId,state,pageNum,pageSize));
    }

    @PostMapping("/state/{id}/{state}")
    public Result state(@PathVariable Long id,@PathVariable String state){
        orderInfoService.changeState(id,state);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        orderInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/order/{id}")
    public Result<OrderInfo> findById(@PathVariable Long id){
        OrderInfo orderInfo=orderInfoService.findById(id);
        return Result.success(orderInfo);
    }
}
