package com.phiotonia.kniotcloud.backend.model;

public class SysRoleMenu {

    private Integer id;
    private Integer roleId;
    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
