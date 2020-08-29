package com.jitlantis.backend.API.base;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WolfEntityListGroup<T> implements WolfEntityGroup<T> {

    private Class<T> clazz;

    private List<?> dataList;

    private String field = "id";

    private Map<String, List<T>> globalMap;

    public WolfEntityListGroup(Class<T> clazz, List<?> dataList, String field) {
        this.clazz = clazz;
        this.dataList = dataList;
        this.field = field;
        this.handleData();
    }

    public void handleData() {
        if (dataList != null && dataList.size() > 0) {
            globalMap = new HashMap<>();
            String id = "id";
            if (this.field != null && this.field.trim().length() > 0) {
                id = this.field.trim();
                for (Object data: dataList) {
                    Map<String, Object> map = BeanUtil.beanToMap(data);
                    if (map.containsKey(id)) {
                        List<T> list = new ArrayList<>();
                        String key = String.valueOf(map.get(id)).trim();
                        if (globalMap.containsKey(key)) {
                            list = globalMap.get(key);
                        }
                        T value = BeanUtil.mapToBean(map, this.clazz, true);
                        list.add(value);
                        globalMap.put(key, list);
                    }
                }
            }
        }
    }

    @Override
    public List<T> getTDataByValue(String value) {
        if (globalMap != null && globalMap.size() > 0 && value != null && value.trim().length() > 0) {
            if (globalMap.containsKey(value.trim())) {
                return globalMap.get(value.trim());
            }
        }
        return new ArrayList<>();
    }
}
