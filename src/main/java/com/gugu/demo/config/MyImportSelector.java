package com.gugu.demo.config;

import com.gugu.demo.entity.Circle;
import com.gugu.demo.entity.Triangle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gugu
 * @Classname MyImportSelector
 * @Description TODO
 * @Date 2022/7/17 13:15
 */
@Import({Circle.class, Triangle.class})
@Configuration
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.gugu.demo.entity.Triangle"};
    }
}
