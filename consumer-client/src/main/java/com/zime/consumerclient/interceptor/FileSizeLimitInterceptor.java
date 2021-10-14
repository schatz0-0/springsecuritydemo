package com.zime.consumerclient.interceptor;


import com.zime.consumerclient.mode.UploadFileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Component
public class FileSizeLimitInterceptor implements HandlerInterceptor {

    private UploadFileProperties uploadFileProperties;

    @Autowired
    public void setUploadFileProperties(UploadFileProperties uploadFileProperties){

        this.uploadFileProperties=uploadFileProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals(RequestMethod.OPTIONS.toString())) return true;
        if (request.getRequestURI().equals("/upload")){
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartRequest multipartRequest = multipartResolver.resolveMultipart(request);
            MultiValueMap<String, MultipartFile> uploadFiles = multipartRequest.getMultiFileMap();
            Set<String> keys = uploadFiles.keySet();
            for (String key:keys){
                List<MultipartFile> files = uploadFiles.get(key);
                if (files!=null)
                    for (MultipartFile file:files){
                        if (file.getSize()>uploadFileProperties.getMultipart().getMax_file_size().toBytes()){
                            throw new MaxUploadSizeExceededException(file.getSize());
                        }
                    }
            }
            return true;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);


    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器处理后");
        System.out.println(request);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器处理完成");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
