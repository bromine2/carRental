package com.shengx1ao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局拦截配置，随着spring启动而启动，注意不能拦截登录页面和静态页面
 */
@Configuration
public class BackInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 防止静态资源和登录页面被拦截
         */
        registry.addInterceptor(new BackInterceptor()).addPathPatterns("/end/page/**")
                .excludePathPatterns("/end/page/login.html");
    }
}
