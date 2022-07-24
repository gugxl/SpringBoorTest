package com.gugu.demo.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gugu
 * @Classname Person
 * @Description TODO
 * @Date 2022/7/17 12:52
 */

@Component
@Data
public class Person {
    @Value("gugu")
// 等价于
// <bean class="Person">
//   <property name ="name" value="gugu"></property>
// </bean>
    private String name;


}
