package com.jitlantis.backend.API.base;

import cn.hutool.db.sql.Direction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JitPageableUtil {

    private final int defaultPage = 1;

    private final int defaultSize = 5;

    private final int maxSize = 6000;

    public Direction convertStringToDirection(String order) {
        if (order != null && "DESC".equals(order.trim().toUpperCase())) {
            return Direction.DESC;
        }
        return Direction.ASC;
    }

    public List<Direction> convertOrder(String order) {
        List<Direction> directionList = new ArrayList<>();
        if (order != null && order.trim().length() > 0) {
            if (order.contains(",")) {
                String[] list = order.split(",");
                for (String string : list) {
                    directionList.add(convertStringToDirection(string));
                }
            } else {
                directionList.add(convertStringToDirection(order));
            }
        } else {
            directionList.add(Direction.ASC);
        }
        return directionList;
    }

    public List<String> convertSort(String sort) {
        if (sort != null && sort.trim().length() > 0) {
            List<String> sortList = new ArrayList<>();
            if (sort.contains(",")) {
                String[] list = sort.split(",");
                for (String string : list) {
                    sortList.add(string.trim());
                }
            } else {
                sortList.add(sort.trim());
            }
            return sortList;
        }
        return null;
    }
}
