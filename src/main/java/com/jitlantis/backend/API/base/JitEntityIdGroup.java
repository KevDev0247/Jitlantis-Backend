package com.jitlantis.backend.API.base;

import cn.hutool.core.bean.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JitEntityIdGroup<T> implements JitEntityGroup<T> {

    private Class<T> clazz;

    private List<?> dataList;

    private String dataField;

    private String valueField;

    private Map<String, T> globalMap;

    public JitEntityIdGroup(Class<T> clazz, List<?> dataList, String dataField) {
        this.clazz = clazz;
        this.dataList = dataList;
        this.dataField = dataField;
        this.valueField = "";
        this.handleData();
    }
    public JitEntityIdGroup(Class<T> clazz, List<?> dataList, String dataField, String valueField) {
        this.clazz = clazz;
        this.dataList = dataList;
        this.dataField = dataField;
        this.valueField = valueField;
        this.handleData();
    }

    public void handleData() {
        if (dataList != null && dataList.size() > 0) {
            globalMap = new HashMap<>();
            String id = "id";
            if (this.dataField != null && this.dataField.trim().length() > 0) {
                id = this.dataField.trim();
            }
            for (Object data : dataList) {
                Map<String, Object> map = BeanUtil.beanToMap(data);
                if (map.containsKey(id)) {
                    String key = String.valueOf(map.get(id));
                    T value = BeanUtil.mapToBean(map, this.clazz, true);
                    globalMap.put(key, value);
                }
            }
        }
    }

    @Override
    public Object getTDataByValue(String value) {
        if (globalMap != null && globalMap.size() > 0 && value.trim().length() > 0) {
            if (globalMap.containsKey(value.trim())) {
                Object resultObject = globalMap.get(value.trim());
                if (this.valueField.trim().length() > 0) {
                    return BeanUtil.getFieldValue(resultObject, this.valueField.trim());
                }
                return resultObject;
            }
        }
        return null;
    }
}
