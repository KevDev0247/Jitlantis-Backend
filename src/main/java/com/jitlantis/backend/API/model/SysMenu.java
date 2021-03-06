package com.jitlantis.backend.API.model;

import com.baomidou.mybatisplus.annotations.TableField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SysMenu that maps the structure from the database entities.
 * This model will carry the data in other sections
 * SysMenu refers to the menu access assigned to each role in System section
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/21
 */
@Entity
public class SysMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer parentId;

    private Integer level;

    private Integer orderNum;

    private String text;

    private String i18n;

    private String link;

    private String icon;

    private Integer isMain;

    private String creator;

    private String updater;

    private Date createTime;

    @TableField(update = "now()")
    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }

    public String getI18n() {
        return i18n;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public String getCreator() {
        return creator;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
