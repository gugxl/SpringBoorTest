package com.gugu.demo.exception;

import org.apache.hbase.thirdparty.com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gugu
 * @Classname CustomExceptionResolver
 * @Description TODO
 * @Date 2022/7/2 10:49
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 解析出异常类型
        CustomException customException = null;
        String message = null;
        Integer errorCode = null;

        if (ex instanceof CustomException){
            customException = (CustomException) ex;
            message = customException.message;
            errorCode = customException.code;
        }else {
            // 如果不是系统自定义异常，构造一个系统自定义的异常类型，信息为“未知错误”
            errorCode = -1;
            message = "未知异常";
            customException = new CustomException(errorCode, message, ex);

        }
        //错误信息
        ModelAndView modelAndView = new ModelAndView();
        //判断请求的类型， 是否是json
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
        //是json请求， 则放回json数据
        if (null != responseBody){
            //设置状态码和信息
            Map<String, Object> responseMap =  new HashMap<>();
            responseMap.put("code", customException.code);
            responseMap.put("message", message);
            String json = new Gson().toJson(responseMap);
            System.out.println(json);
            response.setContentType("UTF-8");
            response.setContentType("application/json;charset=utf-8");

            try {
                response.getWriter().write(json);
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return modelAndView;
        }
        //将错误信息传到页面
        modelAndView.addObject("errorCode", errorCode);
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("showError");
        return modelAndView;
    }
}
