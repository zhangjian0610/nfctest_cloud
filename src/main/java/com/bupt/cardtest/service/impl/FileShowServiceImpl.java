package com.bupt.cardtest.service.impl;


import com.bupt.cardtest.dao.RecordMapper;
import com.bupt.cardtest.dao.UpfileMapper;
import com.bupt.cardtest.model.bean.Record;
import com.bupt.cardtest.model.bean.RecordExample;
import com.bupt.cardtest.model.bean.Upfile;
import com.bupt.cardtest.model.bean.UpfileExample;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.model.pagebean.RecordPage;
import com.bupt.cardtest.service.FileShowService;
import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.RecordShow;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class FileShowServiceImpl implements FileShowService {


    @Autowired
    UpfileMapper upfileDao;

    @Autowired
    RecordMapper recordDao;


    /**
     *
     * 显示文件列表
     *
     * */
    @Override
    public DataGrid dataGrid(AdminDevicePage adminDevicePage) {
        PageHelper.startPage(adminDevicePage.getPage(), adminDevicePage.getRows());//设置分页
        DataGrid dg = new DataGrid();

        if (adminDevicePage.getLevelType()==Constant.ADMIN_LEVEL_TYPE_0) {
            adminDevicePage.setId("");
        }


        List<Upfile> l=upfileDao.dataGrid(adminDevicePage);
        Long total=upfileDao.countDataGrid(adminDevicePage);

        dg.setRows(l);
        dg.setTotal((long) total);
        return dg;

    }

    /**
     *
     *
     * 获取记录列表
     * */
    @Override
    public DataGrid recordDataGrid(Record record) {
        PageHelper.startPage(record.getPage(), record.getRows());//设置分页
        DataGrid dg = new DataGrid();
        /*RecordExample recordExample=new RecordExample();
        recordExample.createCriteria().andFileNameEqualTo(upfile.getFileName());
        recordExample.setOrderByClause("sid ASC");
        List<Record> l=recordDao.selectByExample(recordExample);*/
        List<RecordPage> l=recordDao.dataGrid(record);
        RecordShow.recordshow(l);
        Long total=recordDao.countDataGrid(record);


        dg.setRows(l);
        dg.setTotal((long) total);
        return dg;

    }


    @Override
    public List<RecordPage> download(Record record) {
        /*RecordExample recordExample=new RecordExample();
        recordExample.createCriteria().andFileNameEqualTo(filename);
        recordExample.setOrderByClause("sid ASC");
        return recordDao.selectByExample(recordExample);*/
        List<RecordPage> l=recordDao.dataGrid(record);
        RecordShow.recordshow(l);
        return l;
    }
}
