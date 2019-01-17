package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Upfile;
import com.bupt.cardtest.model.bean.UpfileExample;
import java.util.List;

import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import org.apache.ibatis.annotations.Param;

public interface UpfileMapper {
    long countByExample(UpfileExample example);

    int deleteByExample(UpfileExample example);

    int deleteByPrimaryKey(String fileName);

    int insert(Upfile record);

    int insertSelective(Upfile record);

    List<Upfile> selectByExample(UpfileExample example);

    Upfile selectByPrimaryKey(String fileName);

    int updateByExampleSelective(@Param("record") Upfile record, @Param("example") UpfileExample example);

    int updateByExample(@Param("record") Upfile record, @Param("example") UpfileExample example);

    int updateByPrimaryKeySelective(Upfile record);

    int updateByPrimaryKey(Upfile record);




    List<Upfile> dataGrid(AdminDevicePage adminDevicePage);

    Long countDataGrid(AdminDevicePage adminDevicePage);
}