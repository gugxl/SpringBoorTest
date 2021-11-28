package com.gugu.demo.controller;

import com.gugu.demo.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @Classname TestController
 * @Description TODO
 * @Date 2021/4/28 23:39
 */
@RestController
public class TestController {

    @GetMapping("/set-session")
    public Object writeSession(String sessionVal, HttpSession httpSession) {
        System.out.println("Param 'sessionVal' = " + sessionVal);
        httpSession.setAttribute("sessionVal", sessionVal);
        return sessionVal;
    }

    @GetMapping("/get-session")
    public Object readSession(HttpSession httpSession) {
        StringUtils.isEmpty("aaa");
        Object obj = httpSession.getAttribute("sessionVal");
        System.out.println("'sessionVal' in Session = " + obj);
        return obj;
    }
}

