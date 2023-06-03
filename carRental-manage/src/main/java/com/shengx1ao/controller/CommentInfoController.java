package com.shengx1ao.controller;

import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.CommentInfo;
import com.shengx1ao.entity.OrderInfo;
import com.shengx1ao.service.CommentInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/commentInfo")
public class CommentInfoController {
    @Resource
    CommentInfoService commentInfoService;

    @PostMapping
    public Result<CommentInfo> add(@RequestBody CommentInfo commentInfo){
        commentInfoService.add(commentInfo);
        return Result.success(commentInfo);
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CommentInfo>> findFrontPage(
                                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                                     @PathVariable String name){
        return Result.success(commentInfoService.findPage(pageNum,pageSize,name));
    }
}
