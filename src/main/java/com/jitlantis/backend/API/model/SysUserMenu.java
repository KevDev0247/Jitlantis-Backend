package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class for SysUserMenu Entity, which is used to connect Users and Menus
 *
 * @author Yonggang Su
 * created on 2020/10/31
 */
@Entity
public class SysUserMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer menuId;

    private Integer isShow;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
