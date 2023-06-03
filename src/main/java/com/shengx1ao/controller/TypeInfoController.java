package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.TypeInfo;
import com.shengx1ao.service.TypeInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**汽车类型增删改查控制器**/
@RestController
@RequestMapping(value = "/typeInfo")
public class TypeInfoController {
    @Resource
    private TypeInfoService typeInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<TypeInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return Result.success(typeInfoService.findPage(pageNum,pageSize,name));
    }

    /**新增汽车类型**/
    @PostMapping
    public Result<TypeInfo> add(@RequestBody TypeInfo typeInfo){
        typeInfoService.add(typeInfo);
        return Result.success(typeInfo);
    }

    @PutMapping
    public Result<TypeInfo> update(@RequestBody TypeInfo typeInfo){
        typeInfoService.update(typeInfo);
        return Result.success();
    }

    /**删除汽车类型**/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        typeInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id){
        return Result.success(typeInfoService.findById(id));
    }
}
