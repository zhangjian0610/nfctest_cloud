package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Role;
import com.bupt.cardtest.model.bean.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * @Description: 根据用户id查询用户的角色信息
     * @Check parameters by the 【caller】
     * @param userid
     * @return
     * @throws Exception
     */
    public List<Role> getListRoleByUserId(String userid) throws Exception;
}