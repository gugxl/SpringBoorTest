package com.gugu.demo;

import com.gugu.demo.config.ConditionConfig;
import com.gugu.demo.config.MainConfig;
import com.gugu.demo.config.MainConfigThree;
import com.gugu.demo.config.MyImportSelector;
import com.gugu.demo.entity.Circle;
import com.gugu.demo.entity.ConditionBean;
import com.gugu.demo.entity.Person;
import com.gugu.demo.entity.Person2;
import com.gugu.demo.entity.Rectangle;
import com.gugu.demo.entity.Triangle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author gugu
 * @Classname Tests
 * @Description TODO
 * @Date 2022/7/17 12:58
 */

@SpringBootTest
public class Tests {
    @Autowired
    private Person person;
    @Autowired
    private Person2 person2;

    @Test
    public void testValue(){
        System.out.println(person.getName());
    }

    @Test
    public void testConfig(){
        System.out.println(person2.toString());
    }

    @Test
    public void MainConfig(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Circle circle = applicationContext.getBean(Circle.class);
        circle.sayHi();
    }

    @Test
    public void MainConfig2(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyImportSelector.class);
        Circle circle = applicationContext.getBean(Circle.class);
        circle.sayHi();

        Triangle triangle = applicationContext.getBean(Triangle.class);
        triangle.sayHi();
    }


    @Test
    public void MainConfig3(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigThree.class);
        Circle circle = applicationContext.getBean(Circle.class);
        circle.sayHi();

        Triangle triangle = applicationContext.getBean(Triangle.class);
        triangle.sayHi();

        Rectangle rectangle = applicationContext.getBean(Rectangle.class);
        rectangle.sayHi();
    }


    @Test
    public void MainConfig4(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ConditionBean conditionBean = applicationContext.getBean(ConditionBean.class);
        conditionBean.sayHi();
    }

}
