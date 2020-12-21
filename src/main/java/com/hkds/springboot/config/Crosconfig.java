package com.hkds.springboot.config;

import com.hkds.springboot.interceptors.JWTinterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* 配置：跨域问题
* */
@Configuration
public class Crosconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("Access-Control-Allow-Origin","*")
                .allowedMethods("Access-Control-Allow-Methods","GET","HEAD","POST","DELETE","OPTIONS","PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
    /*
    * 添加拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTinterceptor())
                .addPathPatterns("/**")    //保护的接口
                .excludePathPatterns("/Users/**");  //放行的接口

    }
}
