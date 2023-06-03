package com.shengx1ao.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.shengx1ao.common.Result;
import com.shengx1ao.entity.NxSystemFileInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.service.NxSystemFileInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**文件增删改查控制器**/
@RestController
@RequestMapping(value = "/files")
public class NxSystemFileController {
    @Resource
    private NxSystemFileInfoService nxSystemFileInfoService;

    private static final String BASE_PATH=System.getProperty("user.dir")+"/src/main/resources/static/file/";

    /**新增文件**/
    @PostMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalName=file.getOriginalFilename();
        if(originalName==null){
            return Result.error("1001","文件不能为空");
        }
        if(!originalName.contains("png")&&!originalName.contains("jpg")&&!originalName.contains("jpeg")&&!originalName.contains("gif")){
            return Result.error("1002","只能上传图片");
        }
        String fileName= FileUtil.mainName(originalName)+System.currentTimeMillis()+"."+FileUtil.extName(originalName);
        //文件上传
        FileUtil.writeBytes(file.getBytes(),BASE_PATH+fileName);
        //信息入库
        NxSystemFileInfo info=new NxSystemFileInfo();
        info.setOriginname(originalName);
        info.setFilename(fileName);
        NxSystemFileInfo addInfo=nxSystemFileInfoService.add(info);
        if(addInfo!=null){
            return Result.success(addInfo);
        }
        return Result.error("1003","上传失败");
    }


    /**删除文件**/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        nxSystemFileInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id){
        return Result.success(nxSystemFileInfoService.findById(id));
    }

    /**
     * 下载文件
     */
    @GetMapping("download/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {
        if(StrUtil.isBlank(id)||"null".equals(id)){
            throw new CustomException("1001","您还未上传文件");
        }
        NxSystemFileInfo nxSystemFileInfo=nxSystemFileInfoService.findById(Long.parseLong(id));
        if(nxSystemFileInfo==null){
            throw new CustomException("1001","没找到该文件");
        }
        byte[] bytes=FileUtil.readBytes(BASE_PATH+nxSystemFileInfo.getFilename());
        response.reset();
        //设置response的header
        response.addHeader("Content-Disposition","attachment;filename="+
                URLEncoder.encode(nxSystemFileInfo.getOriginname(),"UTF-8"));
        response.addHeader("Content-Length",""+bytes.length);
        OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(bytes);
        toClient.flush();
        toClient.close();
    }
}
