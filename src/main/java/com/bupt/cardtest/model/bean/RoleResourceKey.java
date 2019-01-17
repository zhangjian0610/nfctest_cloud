package com.bupt.cardtest.model.bean;

public class RoleResourceKey {
    private String tresourceId;

    private String troleId;

    public String getTresourceId() {
        return tresourceId;
    }

    public void setTresourceId(String tresourceId) {
        this.tresourceId = tresourceId == null ? null : tresourceId.trim();
    }

    public String getTroleId() {
        return troleId;
    }

    public void setTroleId(String troleId) {
        this.troleId = troleId == null ? null : troleId.trim();
    }
}