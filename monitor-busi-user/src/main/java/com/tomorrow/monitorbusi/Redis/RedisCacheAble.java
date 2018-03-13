package com.tomorrow.monitorbusi.Redis;

import java.lang.annotation.*;


//redis缓存注解  用于新增缓存
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCacheAble {
    String value() default "";   //key名称、前缀
    String[] names() default {};  //所需要包含的键值
    int timeout() default 86400; //过期时间1天
}
