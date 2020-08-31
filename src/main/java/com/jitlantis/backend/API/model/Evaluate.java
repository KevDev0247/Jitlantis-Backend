package com.jitlantis.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The model for Evaluate that maps the structure from the database entities.
 * This model will carry the data in other sections
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/29
 */
@Entity
public class Evaluate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String comments;

    private Integer rate;

    private Integer userId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }

    public Integer getRate() {
        return rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
