package com.bupt.cardtest.model.bean;

public class RoleResource {
    private String troleId;

    private String tresourceId;

    public String getTroleId() {
        return troleId;
    }

    public void setTroleId(String troleId) {
        this.troleId = troleId == null ? null : troleId.trim();
    }

    public String getTresourceId() {
        return tresourceId;
    }

    public void setTresourceId(String tresourceId) {
        this.tresourceId = tresourceId == null ? null : tresourceId.trim();
    }
}