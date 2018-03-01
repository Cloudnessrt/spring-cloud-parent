package com.tomorrow.common.monitorcommon.Function;


import com.tomorrow.common.monitorcommon.Enum.IEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 公共方法
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-14
 **/
public class EnumUtil {

    /**
     * 枚举转map
     * @param en 枚举
     * @return
     */
    public static Map<Integer, String> enumToMap(Enum en){
        Class clazz = en.getDeclaringClass();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        //实现了枚举接口和是枚举类型
        if(typeCheck( clazz)){
            try {
                Method getKey=getKey(clazz);
                Method getText = getText(clazz);
                //得到enum的所有实例
                Object[] objs = clazz.getEnumConstants();
                for (Object obj : objs) {
                    map.put((Integer)getKey.invoke(obj),(String)getText.invoke(obj));
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 根据枚举key返回key对应的枚举
     * @param key key
     * @param clazz 枚举类型
     * @return 枚举
     */
    public  static Enum getEnumByKey(Integer key,Class clazz){
        //实现了枚举接口和是枚举类型
        if(typeCheck(clazz)){
            try {
                Method getKey=getKey(clazz);
                //得到enum的所有实例
                Object[] objs = clazz.getEnumConstants();
                for (Object obj : objs) {
                    if(getKey.invoke(obj).equals(key)){
                        return (Enum) obj;
                    }
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
     * 实现了枚举接口和是枚举类型
     * @param clazz 类
     * @return 是否
     */
    private static boolean typeCheck(Class clazz){
        if(IEnum.class.isAssignableFrom(clazz) && clazz.isEnum()){
            return true;
        }
        return false;
    }

    /**
     * 获取枚举的text的方法
     * @param clazz 枚举类型
     * @return 方法
     */
    private static Method getText(Class clazz){
        return getMethod(clazz,"getText");
    }

    /**
     * 获取枚举的key的方法
     * @param clazz 枚举类型
     * @return 方法
     */
    private static Method getKey(Class clazz){
        return getMethod(clazz,"getKey");
    }

    /**
     * 根据方法名反射方法
     * @param clazz 类
     * @param method 方法名
     * @return 方法
     */
    private static Method getMethod(Class clazz,String method){
        try{
            return clazz.getMethod(method);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
