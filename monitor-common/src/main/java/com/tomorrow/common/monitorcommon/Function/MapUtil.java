package com.tomorrow.common.monitorcommon.Function;

import com.google.common.collect.Maps;
import com.tomorrow.common.monitorcommon.Constant.CurrentConstant;
import com.tomorrow.common.monitorcommon.Constant.DateConstant;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * map工具类
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-16
 **/
public class MapUtil {
    /**
     * 将javabean对象装换为map
     * @param bean 实例
     * @param <T> map
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean,String dateFormat,String currentFormat) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            //默认格式化初始化
            if(StringUtils.isEmpty(dateFormat)){
                dateFormat= DateConstant.shortDate_ZN;
            }
            if(StringUtils.isEmpty(currentFormat)){
                currentFormat= CurrentConstant.standardFormat;
            }
            BeanMap beanMap = BeanMap.create(bean);
            for (Object item : beanMap.keySet()) {
                String key=item+"";
                String value="";
                if(item.getClass().isInstance(Date.class)){//时间类型
                    SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                    value=format.format(beanMap.get(item));
                }
                else if(item.getClass().isInstance(BigDecimal.class)){//货币格式化
                    DecimalFormat format = new DecimalFormat(currentFormat);
                    value=format.format(beanMap.get(item));
                }
                map.put(key+"", value);
            }
        }
        return map;
    }

    /**
     * 默认格式化下的 将javabean对象装换为map
     * @param bean 类
     * @param <T> map
     * @return
     */
    public   static <T> Map<String, Object> beanToMap(T bean) {
        return beanToMap(bean,null,null);
    }


    /**
     * 将map装换为javabean对象
     * @param map map
     * @param bean 实例
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
