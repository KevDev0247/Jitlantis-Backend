package com.jitlantis.backend.API.base;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.format.FastDateFormat;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    public <T> T convertObjectToBean(Object bean, Class<T> clazz) {
        return WolfBeanUtil.mapToBeanIgnoreCase(convertBeanToMap(bean), clazz, true);
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

    public List<Long> getLongListFromEntityList(List<?> entityList, String field) {
        if (entityList != null && entityList.size() > 0) {
            List<Long> list = new ArrayList<>();
            String tmpField = "id";
            if (field != null && field.trim().length() > 0) {
                tmpField = field.trim();
            }
            for (Object object : entityList) {
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField) && !StringUtils.isEmpty(map.get(tmpField))) {
                    Long fieldValue = Long.parseLong(String.valueOf(map.get(tmpField)));
                    if (!list.contains(fieldValue)) {
                        list.add(fieldValue);
                    }
                }
            }
            return list;
        }
        return null;
    }

    public List<String> getStringListFromEntityList(List<?> entityList, String field) {
        if (entityList != null && entityList.size() > 0) {
            List<String> list = new ArrayList<>();
            String tmpField = "id";
            if (field != null && field.trim().length() > 0) {
                tmpField = field.trim();
            }
            for (Object object : entityList) {
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField)) {
                    String fieldValue = String.valueOf(map.get(tmpField));
                    if (!list.contains(fieldValue) && fieldValue.length() > 0) {
                        list.add(fieldValue);
                    }
                }
            }
            return list;
        }
        return null;
    }

    public List<Long> getCommaStringListFromEntityList(List<?> entityList, String field) {
        if (entityList != null && entityList.size() > 0) {
            List<Long> list = new ArrayList<>();
            String tmpField = "mixspecids";
            if (field != null && field.trim().length() > 0) {
                tmpField = field.trim();
            }
            for (Object object : entityList) {
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField)) {
                    String fieldValue = String.valueOf(map.get(tmpField));
                    String[] result = fieldValue.split(",");
                    for (String string : result) {
                        Long element = Long.valueOf(string);
                        if (!list.contains(element)) {
                            list.add(element);
                        }
                    }
                }
            }
            return list;
        }
        return null;
    }

    public <T> List<T> getDateListFromEntityList(Class<T> clazz, List<?> entityList, String dateField, String formatPattern) {
        if (entityList != null && entityList.size() > 0) {
            List<T> list = new ArrayList<>();
            String tmpField = "createtime";
            if (dateField != null && dateField.trim().length() > 0) {
                tmpField = dateField.trim();
            }
            String pattern = DatePattern.NORM_DATE_PATTERN;
            if (formatPattern != null && formatPattern.trim().length() > 0) {
                pattern = formatPattern.trim();
            }
            for (Object object : entityList) {
                T t = convertObjectToBean(object, clazz);
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField)) {
                    String fieldValue = FastDateFormat.getInstance(pattern).format(map.get(tmpField));
                    WolfBeanUtil.setFieldValue(t, tmpField, fieldValue);
                }
                list.add(t);
            }
            return list;
        }
        return null;
    }

    public List<Long> getLongListFromSingleEntity(String field) {
        if (field != null && field.length() > 0) {
            String[] result = field.split(",");
            List<Long> list = new ArrayList<>();
            for (String string : result) {
                Long element = Long.valueOf(string);
                list.add(element);
            }
            return list;
        }
        return null;
    }

    public List<String> getStringListFromString(String field) {
        if (field != null && field.length() > 0) {
            return Arrays.asList(field.split(","));
        }
        return null;
    }

    public List<String> getStringListFromLongList(List<Long> field) {
        if (field != null && field.size() > 0) {
            List<String> list = new ArrayList<>();
            for (long id : field) {
                String string = String.valueOf(id);
                list.add(string);
            }
            return list;
        }
        return null;
    }

    public List<Long> getIntersection(List<Long> list1, List<Long> list2) {
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }
        List<Long> resultList = new ArrayList<>();
        for (long item : list1) {
            if (list2.contains(item)) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public List<Long> getUnion(List<Long> list1, List<Long> list2) {
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }
        list2.removeAll(list1);
        list1.removeAll(list2);
        
        return list1;
    }
}
