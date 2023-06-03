package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.UserInfo;
import com.shengx1ao.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**用户增删改查控制器**/
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<UserInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return Result.success(userInfoService.findPage(pageNum,pageSize,name));
    }

    /**新增用户**/
    @PostMapping
    public Result<UserInfo> add(@RequestBody UserInfo userInfo){
        userInfoService.add(userInfo);
        return Result.success(userInfo);
    }

    @PutMapping
    public Result<UserInfo> update(@RequestBody UserInfo userInfo){
        userInfoService.update(userInfo);
        return Result.success();
    }

    /**删除用户**/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        userInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserInfo> detail(@PathVariable Long id){
        UserInfo userInfo=userInfoService.findById(id);
        return Result.success(userInfo);
    }
}
