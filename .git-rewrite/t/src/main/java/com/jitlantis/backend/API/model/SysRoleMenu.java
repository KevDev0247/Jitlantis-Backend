package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The model for SysRoleMenu that maps the structure from the database entities.
 * This model will carry the data in other sections
 * SysRoleMenu stores the relationship between roles and the menu access of each roles in System section
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/21
 */
@Entity
public class SysRoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
