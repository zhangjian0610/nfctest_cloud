package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.bean.Resource;
import com.bupt.cardtest.model.bean.ResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     * @Description: 根据角色id查询用户权限
     * @Check parameters by the 【caller】
     * @param roleIds
     * @return
     * @throws Exception
     */
    public List<Resource> getResourcesByRoleId(String[] roleIds)
            throws Exception;





    /**
     * @Description: 根据用户id获取该用户的全部菜单资源
     * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Resource> getResourcesByAdminId(@Param("adminId") String adminId, @Param("resourceType") int resourceType)
            throws Exception;
}