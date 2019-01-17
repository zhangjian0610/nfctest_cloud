package com.bupt.cardtest.controller;


import com.bupt.cardtest.model.bean.Device;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/deviceController")
public class DeviceController extends BaseController {

    @Autowired
    DeviceService deviceService;


    /***
     *
     * 跳转到设备管理页面
     */

    @RequestMapping(value = "/manager")
    public String manager()throws Exception{
        this.getSessionInfo().getResourceMap().put("/deviceController/dataGrid", "设备列表");
        return "/admin/device/device";

    }

    /***
     *
     * 获取设备数据表格
     */

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid dataGrid(AdminDevicePage adminDevicePage) throws Exception{
        SessionInfo sessionInfo = this.getSessionInfo();
        adminDevicePage.setId(sessionInfo.getId());
        adminDevicePage.setLevelType(sessionInfo.getLevelType());

        DataGrid dg = new DataGrid();
        dg=deviceService.dataGrid(adminDevicePage);
        return dg;
    }


    /***
     *
     * 跳转到设备添加页面
     */

    @RequestMapping(value = "/addPage")
    public String addPage() throws Exception{
        //this.getSessionInfo().getResourceMap().put("/deviceController/add", "设备添加");
        return "/admin/device/deviceAdd";
    }


    /**
     *    添加设备
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Json add(Device device) throws Exception{
        System.out.println("添加设备");
        SessionInfo sessionInfo = this.getSessionInfo();
        String addminId=sessionInfo.getId();

        Json j = new Json();
        j = deviceService.add(device,addminId);
        return j;
    }



}
