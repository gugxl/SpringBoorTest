package com.gugu.demo.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author gugu
 * @Classname EnvConfig
 * @Description TODO
 * @Date 2022/11/12 23:29
 */
public class EnvConfig implements EnvironmentAware {

    private Environment environment;

    public String getStringValue(String key){
        return environment.getProperty(key);
    }

    public Long getLongValue(String key){
        String property = environment.getProperty(key);
        return Long.parseLong(property);
    }

    public Integer getIntegerValue(String key){
        String property = environment.getProperty(key);
        return getLongValue(property).intValue();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
