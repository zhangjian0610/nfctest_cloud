package com.bupt.cardtest.service;

import com.bupt.cardtest.model.bean.Role;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.RolePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;

import java.util.List;

/**
 * 角色业务逻辑
 * 
 * @author
 */
public interface RoleServiceI
{
    /**
     * 获得角色treeGrid
     * 
     * @return
     */
    public List<RolePage> treeGrid(SessionInfo sessionInfo) throws Exception;

    /**
     * 保存角色
     * 
     * @param RolePage
     */
    public Json add(RolePage RolePage, SessionInfo sessionInfo) throws Exception;

    /**
     * 获得角色
     * 
     * @param id
     * @return
     */
    public RolePage get(String id) throws Exception;

    /**
     * 修改角色
     * 
     * @param RolePage
     */
    public Json edit(RolePage RolePage) throws Exception;

    /**
     * 删除角色
     * 
     * @param id
     */
    public Json delete(String id) throws Exception;

    /**
     * 为角色授权
     * 
     * @param RolePage
     */
    public Json grant(RolePage RolePage) throws Exception;

    /**
     * 获得授权树((不包含超级管理员角色)
     * 
     * @return
     */
	public List<Role> grantRoleTree() throws Exception;
    /**
     * 获得角色树(只能看到自己拥有的角色)
     * 
     * @return
     */
    /*
     * public List<Tree> tree(SessionInfo sessionInfo) throws Exception;
     * 
     *//**
       * 获得角色树
       * 
       * @return
       */
    /*
     * public List<Tree> allTree() throws Exception;
     * 
     *//**
       * 获得授权树((不包含超级管理员角色)
       * 
       * @return
       */
    /*
     * public List<Tree> grantRolePageTree() throws Exception;
     * 
     */

	public List<Role> grantRoleTree(SessionInfo sessionInfo) throws Exception;
}
