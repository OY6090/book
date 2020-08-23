package com.oy.online.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    /**
     *  BeanUtils.populate()遍历map<key, value>中的key，如果bean中有这个属性，就把这个key对应的value值赋给bean的属性。
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前："+bean);
            /**
             * 把所有的请求的参数注入到user中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后："+ bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换为 int 类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
