package com.bupt.cardtest.service.impl;


import com.bupt.cardtest.dao.AdminDeviceMapper;
import com.bupt.cardtest.dao.DeviceMapper;
import com.bupt.cardtest.model.bean.AdminDeviceExample;
import com.bupt.cardtest.model.bean.AdminDeviceKey;
import com.bupt.cardtest.model.bean.Device;
import com.bupt.cardtest.model.bean.DeviceExample;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.service.DeviceService;
import com.bupt.cardtest.util.Constant;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    DeviceMapper deviceDao;

    @Autowired
    AdminDeviceMapper adminDeviceDao;

    @Override
    public DataGrid dataGrid(AdminDevicePage adminDevicePage) throws Exception {
        PageHelper.startPage(adminDevicePage.getPage(), adminDevicePage.getRows());//设置分页
        DataGrid dg = new DataGrid();


        if (adminDevicePage.getLevelType()==Constant.ADMIN_LEVEL_TYPE_0){
            adminDevicePage.setId("");

        }

        List<Device> l=deviceDao.dataGrid(adminDevicePage);
        Long total=deviceDao.countDataGrid(adminDevicePage);

        dg.setRows(l);
        dg.setTotal((long) total);
        return dg;
    }

    @Override
    public Json add(Device device,String adminId) throws Exception {
        Json j = new Json();


        Device s_device=deviceDao.selectByPrimaryKey(device.getDeviceId()); //查找出原来是否已经注册过该设备
        //AdminDeviceKey admindevice=new AdminDeviceKey();
        if (s_device==null){  //设备没注册过 先加设备
            device.setLockSymbol(0);
            deviceDao.insert(device);

        }
        //库表中加入用户和设备对应关系


        AdminDeviceExample adminDeviceExample=new AdminDeviceExample();
        adminDeviceExample.createCriteria().andAdminIdEqualTo(adminId).
                andDeviceIdEqualTo(device.getDeviceId());

        List<AdminDeviceKey> adminDeviceKeyList=adminDeviceDao.selectByExample(adminDeviceExample);

        if (adminDeviceKeyList!=null && adminDeviceKeyList.size()>0){
            j.setMsg("您已添加过该设备");
            j.setSuccess(false);
            return j;
        }else {

            AdminDeviceKey admindevice=new AdminDeviceKey();
            admindevice.setAdminId(adminId);
            admindevice.setDeviceId(device.getDeviceId());
            adminDeviceDao.insert(admindevice);

            j.setMsg(Constant.ADD_SUCCESS);
            j.setSuccess(true);
            return j;
        }

    }
}
