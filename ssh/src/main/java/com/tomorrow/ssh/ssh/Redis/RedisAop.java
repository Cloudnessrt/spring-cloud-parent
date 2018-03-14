package com.tomorrow.ssh.ssh.Redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tomorrow.ssh.ssh.VO.test;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.lang.reflect.Method;

/**
 * redis的aop
 * @AUTHOR TCH
 * @CREATE 2018-03-06
 **/
@Component // 注册到Spring容器，必须加入这个注解
@Aspect // 该注解标示该类为切面类，切面是由通知和切点组成的。
public class RedisAop {
    //日志
    private static Logger logger= LoggerFactory.getLogger(RedisAop.class);
    public static int error=0;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * redis集群 缓存读取aop切面
     */
    @Pointcut("@annotation( com.tomorrow.ssh.ssh.Redis.RedisCacheAble)")// 定义注解类型的切点，只要方法上有该注解，都会匹配
    public void annotationAble() {

    }

    /**
     * redis集群 缓存读取aop切面
     * @param joinPoint 连接点
     * @param rd 注解类
     * @return
     * @throws Throwable
     */
    @Around("annotationAble()&& @annotation(rd)")
    public Object redisCacheAble(ProceedingJoinPoint joinPoint, RedisCacheAble rd) throws Throwable {
        String preKey = rd.value();
        String arg0 = joinPoint.getArgs()[0].toString();
        String key = preKey + ":" + arg0;
        //如果redis中已經有值，直接返回
        String rtObject = jedisCluster.get(key);
        if (rtObject != null) {
            Signature sig = joinPoint.getSignature();
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            //方法签名
            MethodSignature msig = (MethodSignature) sig;
            //返回类型
            Class clazz=msig.getReturnType();
            return JSON.parseObject(rtObject,clazz);
        }
        // 执行函数,如果返回值為空,返回
        Object sourceObject = joinPoint.proceed();
        if (sourceObject == null) {
            return null;
        }
        // 如果values没有值，那么redis对应的value为输入对象；否则根据输入参数重新生成对象
        if (rd.names().length==0) {
            // 存入目标对象
            jedisCluster.set(key, JSON.toJSONString(sourceObject, SerializerFeature.WRITE_MAP_NULL_FEATURES) );
            jedisCluster.expire(key, rd.timeout());
        } else {
            /*todo*/
        }
        return sourceObject;
    }


    /**
     * redis集群 分布式锁 切面
     */
    @Pointcut("@annotation( com.tomorrow.ssh.ssh.Redis.RedisLockAnnotation)")// 定义注解类型的切点，只要方法上有该注解，都会匹配
    public void annotationLock() {

    }

    /**
     * redis集群 分布式锁实现
     * @param joinPoint 连接点
     * @param rl 注解参数
     * @return
     * @throws Exception
     */
    @Around("annotationLock()&& @annotation(rl)") //定义注解的具体实现，以及能够接受注解对象，定义 @annotation(rd)就可以直接取到annotation的实例了
    public Object annotationLock(ProceedingJoinPoint joinPoint,RedisLockAnnotation rl) throws Exception {
        Signature sig = joinPoint.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        //方法签名
        MethodSignature msig = (MethodSignature) sig;
        Method method=msig.getMethod();
        //根据获取到的参数注解和参数列表获得加锁的参数
        Object[] args=joinPoint.getArgs();
        //返回值
        Object exceResult;
        String objectValue = getCacheKeyField(args);
        String prefix=rl.lockedPrefix();
        //新建一个锁
        RedisLock lock = new RedisLock(prefix, objectValue,jedisCluster);
        //加锁
        boolean result = lock.lock(rl.timeOut(), rl.expireTime());
        if(!result){//取锁失败
            test.error++;
            throw new Exception("error");
        }
        try{
            //加锁成功，执行方法
            exceResult= joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(msig.getMethod().getDeclaringClass()+"--"+ throwable.getMessage());
            exceResult= null;
        } finally {
            lock.unlock();//释放锁
        }
        return exceResult;
    }

   private String getCacheKeyField(Object[] args){
        StringBuffer sb=new StringBuffer();
        for (Object item:args){
            if(sb.length()==0){

            }else{
                sb.append("|");
            }
            sb.append(item.toString());
        }
        return sb.toString();
   }
}