package com.chenyulin.myblog.configuration;


import com.chenyulin.myblog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    @Value("${file.imageAccessPath}")
    private String imageAccessPath;
    @Value("${file.imageUploadPath}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(imageAccessPath).addResourceLocations("file:"+uploadPath);
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        String[] addPathPatterns = {"/blog/*/edit","/blog/*/manage/**"};
        String[] excludePathPatterns = {};
        registration.addPathPatterns(addPathPatterns);   //添加拦截路径
//        registration.excludePathPatterns(); //不拦截的路径
    }
}
