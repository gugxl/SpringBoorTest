package com.gugu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
//        keepRunning();
    }

    /**
     * @Description 阻塞main方法
     * @params
     * @return void
     * @auther gugu
     */
    private static void keepRunning() throws InterruptedException {
        Thread thread = Thread.currentThread();
        synchronized (thread){
            thread.wait();
        }
    }
}
