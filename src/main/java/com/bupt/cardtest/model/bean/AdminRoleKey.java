package com.bupt.cardtest.model.bean;

public class AdminRoleKey {
    private String troleId;

    private String tadminId;

    public String getTroleId() {
        return troleId;
    }

    public void setTroleId(String troleId) {
        this.troleId = troleId == null ? null : troleId.trim();
    }

    public String getTadminId() {
        return tadminId;
    }

    public void setTadminId(String tadminId) {
        this.tadminId = tadminId == null ? null : tadminId.trim();
    }
}