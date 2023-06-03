package com.shengx1ao.mapper;

import com.shengx1ao.entity.CommentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentInfoMapper extends Mapper<CommentInfo> {
    List<CommentInfo> findByContent(@Param("name") String name);

}