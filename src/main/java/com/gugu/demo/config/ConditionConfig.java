package com.gugu.demo.config;

import com.gugu.demo.entity.ConditionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author gugu
 * @Classname ConditionConfig
 * @Description TODO
 * @Date 2022/7/24 8:54
 */
@Configuration
@Conditional(MyCondition.class)
public class ConditionConfig {
    @Bean
    public ConditionBean conditionBean(){
        return new ConditionBean();
    }
}
