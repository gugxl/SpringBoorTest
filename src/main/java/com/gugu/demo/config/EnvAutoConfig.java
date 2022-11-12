package com.gugu.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;

/**
 * @author gugu
 * @Classname EnvAutoConfig
 * @Description TODO
 * @Date 2022/11/12 23:30
 */
@Configuration
@ConditionalOnClass(PropertyResolver.class)
public class EnvAutoConfig {
    @Bean
    public EnvConfig envConfig(){
        return new EnvConfig();
    }
}
