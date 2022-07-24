package com.gugu.demo.config;

import com.gugu.demo.entity.Rectangle;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gugu
 * @Classname MyImportBeanDefinitionRegistrar
 * @Description TODO
 * @Date 2022/7/17 13:24
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rectangle.class);
        registry.registerBeanDefinition("rectangle", rootBeanDefinition);
    }
}
