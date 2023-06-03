package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.AdvertiserInfo;
import com.shengx1ao.service.AdvertiserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**公告增删改查控制器**/
@RestController
@RequestMapping(value = "/advertiserInfo")
public class AdvertiserInfoController {
    @Resource
    private AdvertiserInfoService advertiserInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<AdvertiserInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return Result.success(advertiserInfoService.findPage(pageNum,pageSize,name));
    }

    /**新增公告**/
    @PostMapping
    public Result<AdvertiserInfo> add(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserInfoService.add(advertiserInfo);
        return Result.success(advertiserInfo);
    }

    @PutMapping
    public Result<AdvertiserInfo> update(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserInfoService.update(advertiserInfo);
        return Result.success();
    }

    /**删除公告**/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        advertiserInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id){
        return Result.success(advertiserInfoService.findById(id));
    }
}
