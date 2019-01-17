package com.bupt.cardtest.service;

import com.bupt.cardtest.model.bean.Device;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import com.bupt.cardtest.model.pagebean.AdminPage;



public interface DeviceService {
    public DataGrid dataGrid(AdminDevicePage userPage) throws Exception;


    public Json add(Device device,String adminId) throws Exception;
}
