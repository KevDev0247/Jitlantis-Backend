package com.jitlantis.backend.API.base;

import cn.hutool.core.bean.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class used to store string groups in Jitlantis
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/27
 */
public class JitEntityStringGroup<T> implements JitEntityGroup<T> {

    private List<?> dataList;

    private String dataField;
    
    private String stringField;

    private Map<String, String> globalMap;

    public JitEntityStringGroup(List<?> dataList, String dataField, String stringField) {
        this.dataList = dataList;
        this.dataField = dataField;
        this.stringField = stringField;
        this.handleData();
    }

    public void handleData() {
        if (dataList != null && dataList.size() > 0) {
            globalMap = new HashMap<>();

            String id = "id";
            if (this.dataField != null && this.dataField.trim().length() > 0) {
                id = this.dataField.trim();
            }

            String name = "name";
            if (this.stringField != null && this.stringField.trim().length() > 0) {
                stringField = this.stringField.trim();
            }

            for (Object data : dataList) {
                Map<String, Object> map = BeanUtil.beanToMap(data);
                if (map.containsKey(id) && map.containsKey(name)) {
                    String key = String.valueOf(map.get(id));
                    String value = String.valueOf(map.get(name));
                    globalMap.put(key, value);
                }
            }
        }
    }

    @Override
    public Object getTDataByValue(String value) {
        if (globalMap != null && globalMap.size() > 0 && value != null && value.trim().length() > 0) {
            if (globalMap.containsKey(value.trim())) {
                return globalMap.get(value.trim());
            }
        }
        return "";
    }
}
