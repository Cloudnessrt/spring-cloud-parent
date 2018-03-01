package com.tomorrow.monitorbusi.Common.AOP;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;
import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * dao层的aop
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-17
 **/
@Aspect
@Component
@Order(1000) //order越小越是最先执行，但更重要的是最先执行的最后结束
@Configuration
public class DaoAop {

    /**
     * 拦截新增操作  维护基本信息 例如 创建人、创建时间
     */
    @Pointcut("execution(public * com.tomorrow.monitorbusi.Dao.Mapper..*.insert*(..))")
    public void insertBaseData(){}

    /**
     * 拦截跟新操作  维护基本信息 例如 创建人、创建时间
     */
    @Pointcut("execution(public * com.tomorrow.monitorbusi.Dao.Mapper..*.update*(..))")
    public void updateBaseData(){}

    /**
     * 获取用户信息
     * @return 用户信息
     */
    private UserEntity getUserData(){
        //? 缺少用户信息获取，先测试用临时new了个以后集成redis获取用户 ?/
        UserEntity userEntity=new UserEntity();
        userEntity.setName("假");
        userEntity.setId("1");
        return userEntity;
    }

    @Before("updateBaseData()")
    public void doBeforeUpate(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            //获取当前人员信息
            UserEntity userEntity=getUserData();
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                Object obj= joinPoint.getArgs()[i];
                if(obj.getClass().getSuperclass().equals(BaseEntity.class)){
                    //注入参数
                    Class[] types = {UserEntity.class};
                    //根据方法签名在cls查找方法的信息
                    Method m=obj.getClass().getMethod("updateBase", types);
                    //执行 跟新时基本信息的注入
                    m.invoke(obj,userEntity);
                    break;
                }
            }
        }
    }

    @Before("insertBaseData()")
    public void doBeforeInsert(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            //获取当前人员信息
            UserEntity userEntity=getUserData();
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                Object obj= joinPoint.getArgs()[i];
                if(obj.getClass().getSuperclass().equals(BaseEntity.class)){
                    //注入参数
                    Class[] types = {UserEntity.class};
                    //根据方法签名在cls查找方法的信息
                    Method m=obj.getClass().getMethod("insertBase", types);
                    //执行 新增时基本信息的注入
                    m.invoke(obj,userEntity);
                    break;
                }
            }
        }
    }

}
