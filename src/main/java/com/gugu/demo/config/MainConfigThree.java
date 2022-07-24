package com.gugu.demo.config;

import com.gugu.demo.entity.Circle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author gugu
 * @Classname MainConfigThree
 * @Description TODO
 * @Date 2022/7/17 13:27
 */
@Import({Circle.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfigThree {
}
