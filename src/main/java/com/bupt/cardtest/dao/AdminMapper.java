package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Admin;
import com.bupt.cardtest.model.bean.AdminExample;
import java.util.List;

import com.bupt.cardtest.model.pagebean.AdminPage;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);


    public List<AdminPage> dataGrid(AdminPage page) throws Exception;

    public Long dataGridCount(AdminPage page) throws Exception;
}