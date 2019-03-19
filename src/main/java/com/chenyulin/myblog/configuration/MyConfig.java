package com.chenyulin.myblog.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
}
