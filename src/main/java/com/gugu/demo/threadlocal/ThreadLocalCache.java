package com.gugu.demo.threadlocal;

/**
 * @author Administrator
 * @Classname ThreadLocalCache
 * @Description TODO
 * @Date 2021/11/28 12:27
 */
public class ThreadLocalCache {
    public static ThreadLocal<BaseSignatureRequest> baseSignatureRequestThreadLocal = new ThreadLocal<>();
}
