package com.bupt.cardtest.service.impl;


import com.bupt.cardtest.dao.UpfileMapper;
import com.bupt.cardtest.model.bean.Upfile;
import com.bupt.cardtest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    //@Autowired
    //FileDAO fileDAO;

    @Autowired
    UpfileMapper upfileDAO;

    public FileServiceImpl() {
        super();
    }

    @Override
    public void addFile(String filepath, String deviceId, String filename, Date uploadtime) {
        Upfile file=new Upfile();
        file.setPath(filepath);
        file.setDeviceId(deviceId);
        file.setFileName(filename);
        file.setUploadTime(uploadtime);
        //fileDAO.addFile(file);
        upfileDAO.insert(file);

    }
}
