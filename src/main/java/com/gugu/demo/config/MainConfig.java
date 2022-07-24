package com.gugu.demo.config;

import com.gugu.demo.entity.Circle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author gugu
 * @Classname MainConfig
 * @Description TODO
 * @Date 2022/7/17 12:57
 */
@Import({Circle.class})
@Configuration
public class MainConfig {
}
