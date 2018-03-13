package com.tomorrow.ssh.ssh.Redis;

import java.lang.annotation.*;

/**
 * 锁参数
 *
 * @AUTHOR TCH
 * @CREATE 2018-03-09
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
}
