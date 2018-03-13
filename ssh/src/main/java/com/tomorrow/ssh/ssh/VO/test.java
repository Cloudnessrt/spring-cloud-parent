package com.tomorrow.ssh.ssh.VO;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR TCH
 * @CREATE 2018-03-12
 **/
public class test {
    public  static int error=0;
    public static Map<Long, Long> inventory ;
    static{
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000l);
        inventory.put(10000002L, 10000l);
    }
}
