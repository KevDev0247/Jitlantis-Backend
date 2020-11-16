package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for SendAttachment that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/09/01
 */
@Entity
public class SendAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String warehouse;

    private String goodscode;

    private String goodsname;

    private String specType;

    private Integer num;

    private Integer hsprice;

    private Integer wsprice;

    private Integer wssum;

    private Integer ssum;

    private Integer tax;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public String getGoodscode() {
        return goodscode;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getSpecType() {
        return specType;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getHsprice() {
        return hsprice;
    }

    public Integer getWsprice() {
        return wsprice;
    }

    public Integer getWssum() {
        return wssum;
    }

    public Integer getSsum() {
        return ssum;
    }

    public Integer getTax() {
        return tax;
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

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public void setGoodscode(String goodscode) {
        this.goodscode = goodscode;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setHsprice(Integer hsprice) {
        this.hsprice = hsprice;
    }

    public void setWsprice(Integer wsprice) {
        this.wsprice = wsprice;
    }

    public void setWssum(Integer wssum) {
        this.wssum = wssum;
    }

    public void setSsum(Integer ssum) {
        this.ssum = ssum;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
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
