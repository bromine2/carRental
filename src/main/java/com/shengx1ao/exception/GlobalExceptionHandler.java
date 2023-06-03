package com.shengx1ao.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.shengx1ao.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**全局异常拦截**/
@ControllerAdvice(basePackages = "com.shengx1ao.controller")
public class GlobalExceptionHandler {

    private static final Log log= LogFactory.get();

    /**统一异常处理**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request,Exception e){
        log.error("异常信息",e);
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request,CustomException e){
        return Result.error(e.getCode(),e.getMsg());
    }
}
