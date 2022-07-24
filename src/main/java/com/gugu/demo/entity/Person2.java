package com.gugu.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gugu
 * @Classname Person2
 * @Description TODO
 * @Date 2022/7/17 12:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@ToString
public class Person2 {
    private String name;
    private Integer age;
    private String sex;
}
