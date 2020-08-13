package com.jitlantis.backend.API.dto;

import java.util.List;

public class OrgDto {

    private Integer id;

    private String key;

    private String title;

    private boolean isLeaf;

    private boolean expanded;

    private List<OrgDto> children;

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public List<OrgDto> getChildren() {
        return children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void setChildren(List<OrgDto> children) {
        this.children = children;
    }
}
