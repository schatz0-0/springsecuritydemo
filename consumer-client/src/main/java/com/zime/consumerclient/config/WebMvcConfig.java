package com.zime.consumerclient.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zime.consumerclient.interceptor.FileSizeLimitInterceptor;
import com.zime.consumerclient.mode.UploadFileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private UploadFileProperties uploadFileProperties;
    private FileSizeLimitInterceptor fileSizeLimitInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowCredentials(true);
    }

    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }



    @Autowired
    public void setUploadFileProperties(UploadFileProperties uploadFileProperties){
        this.uploadFileProperties=uploadFileProperties;
        System.out.println("WebMvcConfig加载上传文件配置对象");
    }

    @Autowired
    public void setFileSizeLimitInterceptor(FileSizeLimitInterceptor fileSizeLimitInterceptor){
        this.fileSizeLimitInterceptor = fileSizeLimitInterceptor;
        System.out.println("WebMvcConfig加载文件大小检查拦截器");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fileSizeLimitInterceptor)
                .addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
