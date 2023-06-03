package com.shengx1ao.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.entity.CommentInfo;
import com.shengx1ao.mapper.CommentInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

//    new 评价
    public CommentInfo add(CommentInfo commentInfo){
        commentInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
        commentInfoMapper.insertSelective(commentInfo);
        return commentInfo;
    }

    public PageInfo<CommentInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<CommentInfo> list=commentInfoMapper.findByContent(name);
        return PageInfo.of(list);
    }
}
