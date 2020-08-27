package com.jitlantis.backend.API.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class WolfConverter {

    @Autowired
    private WolfPageableUtil wolfPageableUtil;

    private final List<String> pageKeyList = new ArrayList<>(Arrays.asList("page", "limit", "sort", "order"));

    public <T> T convertMapToBean(Map<?, ?> map, Class<T> clazz) {
        if (map != null && map.size() > 0 && clazz != null) {
            return WolfBeanUtil.mapToBeanIgnoreCase(map, clazz, true);
        }
        return null;
    }

    public Map<String, Object> convertBeanToMap(Object bean) {
        if (bean != null) {
            return WolfBeanUtil.beanToMap(bean, false, false);
        }
        return null;
    }

    public Map<String, Object> mergeBeanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue) {
        if (bean != null && targetMap != null) {
            return WolfBeanUtil.beanToMap(bean, targetMap, false, ignoreNullValue);
        }
        return null;
    }

    public <T> T mergeMapToBean(Class<T> clazz, Map<String, Object> sourceMap, Object bean) {
        T t = WolfBeanUtil.mapToBeanIgnoreCase(sourceMap, clazz, true);
        if (t != null && bean != null) {
            WolfBeanUtil.copyProperties(bean, t);
            return t;
        }
        return null;
    }
}
