package com.gugu.demo;

import com.gugu.demo.threadlocal.ParameterInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Administrator
 * @Classname WebMvcConfig
 * @Description TODO
 * @Date 2021/11/28 12:37
 */
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ParameterInterceptor()).addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }
}
