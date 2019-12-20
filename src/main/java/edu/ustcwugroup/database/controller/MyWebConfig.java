package edu.ustcwugroup.database.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Haozk on 2019/12/19
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/imagem/**").addResourceLocations("file:D:/code/barcode/");
        registry.addResourceHandler("/job/**").addResourceLocations("file:F:/job/");
    }
}

