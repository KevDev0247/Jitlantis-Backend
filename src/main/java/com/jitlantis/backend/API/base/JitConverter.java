package com.jitlantis.backend.API.base;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.format.FastDateFormat;
import com.alibaba.fastjson.JSON;
import com.jitlantis.backend.API.model.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * A converter used to convert entities between beans, maps, and objects
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/27
 */
@Component
public class JitConverter {

    @Autowired
    private JitPageableUtil jitPageableUtil;

    private final List<String> pageKeyList = new ArrayList<>(Arrays.asList("page", "limit", "sort", "order"));

    public <T> T convertMapToBean(Map<?, ?> map, Class<T> clazz) {
        if (map != null && map.size() > 0 && clazz != null) {
            return JitBeanUtil.mapToBeanIgnoreCase(map, clazz, true);
        }
        return null;
    }

    public <T> T convertObjectToBean(Object bean, Class<T> clazz) {
        return JitBeanUtil.mapToBeanIgnoreCase(convertBeanToMap(bean), clazz, true);
    }

    public Map<String, Object> convertBeanToMap(Object bean) {
        if (bean != null) {
            return JitBeanUtil.beanToMap(bean, false, false);
        }
        return null;
    }

    public Map<String, Object> mergeBeanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue) {
        if (bean != null && targetMap != null) {
            return JitBeanUtil.beanToMap(bean, targetMap, false, ignoreNullValue);
        }
        return null;
    }

    public <T> T mergeMapToBean(Class<T> clazz, Map<String, Object> sourceMap, Object bean) {
        T t = JitBeanUtil.mapToBeanIgnoreCase(sourceMap, clazz, true);
        if (t != null && bean != null) {
            JitBeanUtil.copyProperties(bean, t);
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
                    JitBeanUtil.setFieldValue(t, tmpField, fieldValue);
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

    public List<String> getStringListFromSingleEntity(String field) {
        if (field != null && field.length() > 0) {
            String[] result = field.split(",");
            return new ArrayList<>(Arrays.asList(result));
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

    public <T> List<T> mergeListByAny(Class<T> clazz, List<?> refList, Map<String, String> rules,
                                      Map<String, JitEntityGroup<?>> assistMap) {
        if (clazz != null && refList != null && refList.size() > 0) {
            List<T> resultList = new ArrayList<>();
            for (Object object : refList) {
                T t = convertObjectToBean(object, clazz);
                if (rules != null && rules.size() > 0) {
                    for (Map.Entry<String, String> entry : rules.entrySet()) {
                        String keyField = entry.getKey();
                        String valueField = entry.getValue();
                        Object valueFieldValue = JitBeanUtil.getFieldValue(object, valueField);
                        JitEntityGroup<?> jitEntityGroup = null;
                        if (assistMap != null && assistMap.containsKey(keyField)) {
                            jitEntityGroup = assistMap.get(keyField);
                        }
                        Object valueObject = null;
                        if (jitEntityGroup != null && valueFieldValue != null) {
                            valueObject = jitEntityGroup.getTDataByValue(String.valueOf(valueFieldValue));
                        }
                        JitBeanUtil.setFieldValue(t, keyField, valueObject);
                    }
                }
                resultList.add(t);
            }
            return resultList;
        }
        return null;
    }

    public <T> Map<String, T> groupIdByField(Class<T> clazz, List<?> entityList, String dateField, String formatPattern) {
        if (entityList != null && entityList.size() > 0) {
            Map<String, T> resultMap = new HashMap<>();
            String tmpField = "id";
            String pattern;
            if (dateField != null && dateField.trim().length() > 0) {
                tmpField = dateField.trim();
            }
            for (Object object : entityList) {
                T t = convertObjectToBean(object, clazz);
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField)) {
                    String keyValue = String.valueOf(map.get(tmpField));
                    if (formatPattern != null && formatPattern.trim().length() > 0) {
                        pattern = formatPattern.trim();
                        keyValue = FastDateFormat.getInstance(pattern).format(map.get(tmpField));
                    }
                    resultMap.put(keyValue.trim(), t);
                }
            }
            return resultMap;
        }
        return null;
    }

    public <T> Map<String, List<T>> groupListsByField(Class<T> clazz, List<?> entityList, String dateField, String formatPattern) {
        if (entityList != null && entityList.size() > 0) {
            Map<String, List<T>> resultMap = new HashMap<>();
            String tmpField = "id";
            String pattern;
            if (dateField != null && dateField.trim().length() > 0) {
                tmpField = dateField.trim();
            }
            for (Object object : entityList) {
                T t = convertObjectToBean(object, clazz);
                Map<String, Object> map = convertBeanToMap(object);
                if (map.containsKey(tmpField)) {
                    List<T> list = new ArrayList<>();
                    String keyValue = String.valueOf(map.get(tmpField));
                    if (formatPattern != null && formatPattern.trim().length() > 0) {
                        pattern = formatPattern.trim();
                        keyValue = FastDateFormat.getInstance(pattern).format(map.get(tmpField));
                    }
                    if (resultMap.containsKey(keyValue.trim())) {
                        list = resultMap.get(keyValue.trim());
                    }
                    list.add(t);
                    resultMap.put(keyValue.trim(), list);
                }
            }
            return resultMap;
        }
        return null;
    }

    public <T> List<T> convertJsonStringToList(String jsonString, Class<T> clazz) {
        if (jsonString != null && jsonString.length() > 0) {
            return JSON.parseArray(jsonString, clazz);
        }
        return null;
    }

    public List<String> convertJsonStringToList(String jsonString) {
        if (jsonString != null && jsonString.length() > 0) {
            String[] pathArray = (jsonString.substring(1, jsonString.length() - 1)).split(",");
            if (pathArray.length > 1) {
                pathArray[0] = pathArray[0].replace("[", "");
                pathArray[pathArray.length - 1] = pathArray[pathArray.length - 1].replace("]", "");
                for (int i = 0; i < pathArray.length; i++) {
                    pathArray[i] = pathArray[i].replace("\"", "");
                }
            }
            pathArray[0] = pathArray[0].replace("[", "").replace("]", "");
            if (pathArray[0].equals("")) {
                return null;
            }
            return Arrays.asList(pathArray);
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(new JitConverter().convertJsonStringToList("[]"));
    }
}
