package com.jitlantis.backend.API.base;

import cn.hutool.core.bean.BeanUtil;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class JitBeanUtil extends BeanUtil {

    public static <E> E mergeDefaultValueToBean(E bean) {
        if (bean != null) {
            Map<String, Object> defaultMap = new HashMap<>();
            PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(bean.getClass());
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String propertyName = propertyDescriptor.getName();
                    if (BeanUtil.getFieldValue(bean, propertyName) == null) {
                        Class<?> clazz = propertyDescriptor.getPropertyType();
                        if (clazz == Integer.class) {
                            defaultMap.put(propertyName, Integer.valueOf(0));
                        } else if (clazz == Long.class) {
                            defaultMap.put(propertyName, Long.valueOf(0));
                        } else if (clazz == Double.class) {
                            defaultMap.put(propertyName, Double.valueOf(0));
                        } else if (clazz == BigDecimal.class) {
                            defaultMap.put(propertyName, new BigDecimal(0));
                        } else if (clazz == String.class) {
                            defaultMap.put(propertyName, "");
                        }
                    }
                }
            }
            if (defaultMap.size() > 0) {
                return BeanUtil.fillBeanWithMap(defaultMap, bean, true);
            }
        }

        return bean;
    }

    public static <E> E mergeUpdatedBeanToNewBean(E newBean, E oldBean) {
        if (newBean != null && oldBean != null) {
            Map<String, Object> newMap = new HashMap<>();
            PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(newBean.getClass());
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String propertyName = propertyDescriptor.getName();
                    if (BeanUtil.getFieldValue(newBean, propertyName) != null) {
                        newMap.put(propertyName, BeanUtil.getFieldValue(newBean, propertyName));
                    }
                }
            }
            if (newMap.size() > 0) {
                return BeanUtil.fillBeanWithMap(newMap, oldBean, true);
            }
        }

        return oldBean;
    }
}
