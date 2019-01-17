package com.bupt.cardtest.model.bean;

import java.util.Date;

public class SystemConfig {
    private String confId;

    private String confName;

    private String confContext;

    private String confDesc;

    private Date updateTime;

    private String adminName;

    private Short dataVerFlag;

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId == null ? null : confId.trim();
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
    }

    public String getConfContext() {
        return confContext;
    }

    public void setConfContext(String confContext) {
        this.confContext = confContext == null ? null : confContext.trim();
    }

    public String getConfDesc() {
        return confDesc;
    }

    public void setConfDesc(String confDesc) {
        this.confDesc = confDesc == null ? null : confDesc.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Short getDataVerFlag() {
        return dataVerFlag;
    }

    public void setDataVerFlag(Short dataVerFlag) {
        this.dataVerFlag = dataVerFlag;
    }
}