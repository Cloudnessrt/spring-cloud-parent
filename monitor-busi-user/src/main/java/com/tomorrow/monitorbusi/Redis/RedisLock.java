package com.tomorrow.monitorbusi.Redis;

import redis.clients.jedis.JedisCluster;

import java.util.Random;

/**
 * redis分布式锁
 *
 * @AUTHOR TCH
 * @CREATE 2018-03-09
 **/
public class RedisLock {

    //纳秒和毫秒之间的转换率
    public static final long MILLI_NANO_TIME = 1000 * 1000L;
    public static final String LOCKED = "TRUE";
    private String key;
    private JedisCluster jedisCluster;

    public RedisLock(String purpose, String key,JedisCluster jedisCluster){
        this.key = purpose + "_" + key + "_lock";
        this.jedisCluster = jedisCluster;
    }

    /**
     * 加锁
     * 使用方式为：
     * lock();
     * try{
     *    executeMethod();
     * }finally{
     *   unlock();
     * }
     * @param timeout timeout的时间范围内轮询锁
     * @param expire 设置锁超时时间
     * @return 成功 or 失败
     */
    public boolean lock(long timeout,int expire){
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.nanoTime() - nanoTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (this.jedisCluster.setnx(this.key, LOCKED) == 1) {
                    this.jedisCluster.expire(key, expire);//设置锁过期时间是为了在没有释放
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    return true;
                }
                System.out.println("出现锁等待");
                //短暂休眠，避免可能的活锁
                Thread.sleep(3,new Random().nextInt(30));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error",e);
        }
        return false;
    }

    public  void unlock() {
        try {
            this.jedisCluster.del(key);//直接删除
        } catch (Throwable e) {

        }
    }
}
