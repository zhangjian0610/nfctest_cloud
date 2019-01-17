package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Device;
import com.bupt.cardtest.model.bean.DeviceExample;
import java.util.List;

import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(String deviceId);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);






    /**
     * 通过admin_id device_id查找拥有的所有设备
     */

    List<Device> dataGrid(AdminDevicePage adminDevicePage);


    /**
     * 通过用户ID统计该用户拥有的设备数目
     */
    Long countDataGrid(AdminDevicePage adminDevicePage);
}