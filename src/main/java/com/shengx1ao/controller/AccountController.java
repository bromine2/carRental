package com.shengx1ao.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.shengx1ao.common.Common;
import com.shengx1ao.common.Result;
import com.shengx1ao.common.ResultCode;
import com.shengx1ao.entity.UserInfo;
import com.shengx1ao.exception.CustomException;
import com.shengx1ao.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//登录退出相关的控制器
@RestController
public class AccountController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        if(StrUtil.isBlank(userInfo.getName())||StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        //从数据库查询账号密码是否正确
        UserInfo login=userInfoService.login(userInfo.getName(),userInfo.getPassword());
        HttpSession session=request.getSession();
        session.setAttribute(Common.USER_INFO,login);
        session.setMaxInactiveInterval(60*24);
        return Result.success(login);
    }

    /**重置密码**/
    @PostMapping("/resetPassword")
    public Result<UserInfo> resetPassword(@RequestBody UserInfo userInfo){
        return Result.success(userInfoService.resetPassword(userInfo.getName()));
    }

    /**登出**/
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success();
    }

    /**
     * 小程序端用户注册方式
     */
    @PostMapping("/register")
    public Result<UserInfo> register(@RequestBody UserInfo userInfo,HttpServletRequest request){
        if(StrUtil.isBlank(userInfo.getName())||StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        UserInfo register=userInfoService.add(userInfo);
        HttpSession session=request.getSession();
        session.setAttribute(Common.USER_INFO,register);
        session.setMaxInactiveInterval(60*60*24);
        return Result.success(register);
    }

    /**
     * 判断用户是否已经登录
     */
    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request){
        Object user = request.getSession().getAttribute(Common.USER_INFO);
        if(user==null){
            return Result.error("401","未登录");
        }
        return Result.success(user);
    }
}
