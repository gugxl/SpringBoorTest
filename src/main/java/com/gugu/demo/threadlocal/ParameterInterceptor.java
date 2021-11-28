package com.gugu.demo.threadlocal;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @Classname ParameterInterceptor
 * @Description TODO
 * @Date 2021/11/28 12:30
 */
public class ParameterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String device = request.getParameter("device");
        BaseSignatureRequest baseSignatureRequest = new BaseSignatureRequest();
        baseSignatureRequest.setDevice(device);
        ThreadLocalCache.baseSignatureRequestThreadLocal.set(baseSignatureRequest);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalCache.baseSignatureRequestThreadLocal.remove();
    }
}
