package com.bupt.cardtest.service;

import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pagebean.AdminPage;

import java.util.List;

/**
 * 用户Service
 * 
 * @author
 */
public interface AdminServiceI
{
    /**
     * 获取用户数据表格
     * 
     * @param user
     * @return
     */
    public DataGrid dataGrid(AdminPage userPage) throws Exception;

    /**
     * 添加用户
     * 
     * @param user
     */
    public Json add(AdminPage userPage) throws Exception;

    /**
     * 获得用户对象
     * 
     * @param id
     * @return
     */
    public AdminPage getUser(String id) throws Exception;

    /**
     * 
     * @param user
     */
    public Json edit(AdminPage userPage, SessionInfo sessionInfo) throws Exception;

    /**
     * 删除用户
     * 
     * @param id
     */
    public Json delete(String id, SessionInfo sessionInfo) throws Exception;

    /**
     * @Description: 重置密码
     * @Check
     * @param id
     * @return
     * @throws Exception
     */
    public Json resetPwd(String id) throws Exception;

    /**
     * 修改用户自己的密码
     * 
     * @param sessionInfo
     * @param oldPwd
     * @param pwd
     * @param editpwdcode
     * @param session
     * @return
     */
    public Json editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) throws Exception;
    
    
    /**
     * 修改所有用户为解锁状态
     * 
     * @throws Exception
     */
    public void userUnlock()throws Exception;

	/**
	 * 修改所有用户为解锁状态
	 * 
	 * @throws Exception
	 */
	public List<AdminPage> getList() throws Exception;
}
