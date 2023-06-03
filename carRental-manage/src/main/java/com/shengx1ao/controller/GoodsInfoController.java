package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.GoodsInfo;
import com.shengx1ao.service.GoodsInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**汽车增删改查控制器**/
@RestController
@RequestMapping(value = "/goodsInfo")
public class GoodsInfoController {
    @Resource
    private GoodsInfoService goodsInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<GoodsInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return Result.success(goodsInfoService.findPage(pageNum,pageSize,name));
    }

    /**新增汽车**/
    @PostMapping
    public Result<GoodsInfo> add(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.add(goodsInfo);
        return Result.success(goodsInfo);
    }

    @PutMapping
    public Result<GoodsInfo> update(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.update(goodsInfo);
        return Result.success();
    }

    /**删除汽车**/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        goodsInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id){
        return Result.success(goodsInfoService.findById(id));
    }

    @GetMapping("/findRecommendGoods")
    public Result<PageInfo<GoodsInfo>> findRecommendGoods(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "100") Integer pageSize){
        return Result.success(goodsInfoService.findRecommendGoods(pageNum,pageSize));
    }


    @GetMapping("/findHotSaleGoods")
    public Result<PageInfo<GoodsInfo>> findHotSaleGoods(@RequestParam(defaultValue = "1") Integer pageNum,
                                                          @RequestParam(defaultValue = "9") Integer pageSize){
        return Result.success(goodsInfoService.findHotSaleGoods(pageNum,pageSize));
    }

    @GetMapping("/findByType/{typeId}")
    public Result<List<GoodsInfo>> findByType(@PathVariable Integer typeId){
        return Result.success(goodsInfoService.findByType(typeId));
    }
}
