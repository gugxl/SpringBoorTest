package com.gugu.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * @author Administrator
 * @Classname RedisTool
 * @Description TODO
 * @Date 2021/5/12 22:10
 */
public class RedisTool {
    private static final Long RELEASE_SUCCESS = 1L;


    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * @param jedis      Redis客户端
     * @param lockKey    lockKey 锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     * @Description 尝试获取分布式锁
     * @params
     * @auther Administrator
     */

    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
    
    /**
     * @Description 释放分布式锁
     * @params 
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     * @auther Administrator
     */
    
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    /**
     * @param jedis
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return void
     * @Description 加锁错误示例
     * @params
     * @auther Administrator
     */

    public static void wrongGetLock1(Jedis jedis, String lockKey, String requestId, int expireTime) {
        Long result = jedis.setnx(lockKey, requestId);
        if (1 == result) {
            // 若在这里程序突然崩溃，则无法设置过期时间，将发生死锁
            jedis.expire(lockKey, expireTime);
        }
    }

    public static boolean wrongGetLock2(Jedis jedis, String lockKey, String requestId, int expireTime) {
        long expires = System.currentTimeMillis() + expireTime;
        String expiresStr = String.valueOf(expires);
        // 如果当前锁不存在，返回加锁成功
        if (1 == jedis.setnx(lockKey, expiresStr)){
            return true;
        }
        // 如果锁存在，获取锁的过期时间
        String currentValueStr = jedis.get(lockKey);
        if ( null != currentValueStr && Long.parseLong(currentValueStr) < System.currentTimeMillis()){
            // 锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
            String oldValueStr = jedis.getSet(lockKey, expiresStr);
            if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                // 考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
                return true;
            }
        }
        // 其他情况，一律返回加锁失败
        return false;
    }
/**
 * @Description 错误释放锁
 * @params 
 * @param jedis
 * @param lockKey
 * @return void
 * @auther Administrator
 */

    public static void wrongReleaseLock1(Jedis jedis, String lockKey) {
        jedis.del(lockKey);
    }

    /**
     * @Description 错误释放锁
     * @params 
     * @param jedis
     * @param lockKey
     * @param requestId
     * @return void
     * @auther Administrator
     */
    
    public static void wrongReleaseLock2(Jedis jedis, String lockKey, String requestId) {
        // 判断加锁与解锁是不是同一个客户端
        if (requestId.equals(jedis.get(lockKey))) {
            // 若在此时，这把锁突然不是这个客户端的，则会误解锁
            jedis.del(lockKey);
        }
    }

    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = getJedisClient();
            String lockKey = "小谷";
            String requestId = "name=gugu";
            int expireTime = 30 * 1000;
            if (tryGetDistributedLock(jedis, lockKey, requestId, expireTime)) {
                System.out.println("获取锁成功");
                Thread.sleep(10 * 1000);
                if (releaseDistributedLock(jedis, lockKey, requestId)){
                    System.out.println("释放锁成功");
                }else {
                    System.out.println("释放锁失败");
                }
            } else {
                System.out.println("获取锁失败");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close(jedis);
        }



    }

    public static Jedis getJedisClient() {
        JedisPool jedisPool = new JedisPool("192.168.2.200", 6379);
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis) {
        if (null != jedis){
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
            jedis.close();
        }
    }

}
