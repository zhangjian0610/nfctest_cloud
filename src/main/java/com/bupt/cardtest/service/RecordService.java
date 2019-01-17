package com.bupt.cardtest.service;

import com.bupt.cardtest.model.bean.Record;

import java.util.List;


public interface RecordService {

    void addRecords(String filePath, String deviceId, String file_name) throws Exception;

    //public List<Record> resolveTxt(String filePath,int deviceId,String file_name);

    public List<Record> selectByDeviceAndFile(String deviceId,String fileName);
}
