package com.bupt.cardtest.service;

import java.util.Date;

public interface FileService {
    public void addFile(String filepath, String deviceId, String filename, Date createtime);
}
