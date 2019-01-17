package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.AdminDeviceExample;
import com.bupt.cardtest.model.bean.AdminDeviceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminDeviceMapper {
    long countByExample(AdminDeviceExample example);

    int deleteByExample(AdminDeviceExample example);

    int deleteByPrimaryKey(AdminDeviceKey key);

    int insert(AdminDeviceKey record);

    int insertSelective(AdminDeviceKey record);

    List<AdminDeviceKey> selectByExample(AdminDeviceExample example);

    int updateByExampleSelective(@Param("record") AdminDeviceKey record, @Param("example") AdminDeviceExample example);

    int updateByExample(@Param("record") AdminDeviceKey record, @Param("example") AdminDeviceExample example);
}