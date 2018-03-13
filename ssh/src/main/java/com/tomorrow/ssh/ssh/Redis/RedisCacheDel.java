package com.tomorrow.ssh.ssh.Redis;

import java.lang.annotation.*;

//redis缓存注解  用于过期缓存
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCacheDel {
    String value() default "";   //key名称、前缀
}
