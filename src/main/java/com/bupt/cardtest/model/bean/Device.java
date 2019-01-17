package com.bupt.cardtest.model.bean;

import com.bupt.cardtest.model.pagebean.PageHelper;

public class Device extends PageHelper {
    private String deviceId;

    private String deviceInfo;

    private Integer deviceType;

    private Integer lockSymbol;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getLockSymbol() {
        return lockSymbol;
    }

    public void setLockSymbol(Integer lockSymbol) {
        this.lockSymbol = lockSymbol;
    }
}