package com.bupt.cardtest.service.impl;

//import com.bupt.cardtest.dao.RecordDAO;
import com.bupt.cardtest.dao.RecordMapper;
import com.bupt.cardtest.model.bean.Record;
import com.bupt.cardtest.service.RecordService;
import com.bupt.cardtest.util.Txt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private static final Logger logger=LoggerFactory.getLogger(RecordServiceImpl.class);

    //@Autowired
    //RecordDAO recordDAO;

    @Autowired
    RecordMapper recordDAO;

    @Autowired
    Txt txt;

    @Override
    //@Async
    public void addRecords(String filePath, String deviceId, String file_name)throws Exception{

        //List<Record> records=txt.readTxt(filePath,deviceId,file_name);

        List<Record> records=null;
        try {
            records=txt.readTxt(filePath,deviceId,file_name);
        }catch (Exception e){
            logger.info("文件读取失败");
            throw new Exception("文件读取失败");

        }

        if (records==null||records.size()==0) {
            logger.info("文件记录为空");
            throw new Exception("文件记录为空");
        }

        try {
            for (Record record:records
                    ) {
                //recordDAO.addRecord(record);
                recordDAO.insert(record);
            }
        }catch (Exception e){
            logger.info("文件入库失败");
            throw new Exception("文件入库失败");
        }


    }



    @Override
    public List<Record> selectByDeviceAndFile(String deviceId, String fileName) {
        return null;
    }
}
