package com.bupt.cardtest.model.bean;

import com.bupt.cardtest.model.pagebean.PageHelper;

import java.util.Date;

public class Admin extends PageHelper {
    private String id;

    private String name;

    private String pwd;

    private Date createdatetime;

    private Integer lockSymbol;

    private Integer remainingLogins;

    private String email;

    private Date modifydatetime;

    private Date validdatetime;

    private Integer levelType;

    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Integer getLockSymbol() {
        return lockSymbol;
    }

    public void setLockSymbol(Integer lockSymbol) {
        this.lockSymbol = lockSymbol;
    }

    public Integer getRemainingLogins() {
        return remainingLogins;
    }

    public void setRemainingLogins(Integer remainingLogins) {
        this.remainingLogins = remainingLogins;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public Date getValiddatetime() {
        return validdatetime;
    }

    public void setValiddatetime(Date validdatetime) {
        this.validdatetime = validdatetime;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}