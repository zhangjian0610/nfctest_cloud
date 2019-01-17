package com.bupt.cardtest.service;

import com.bupt.cardtest.model.bean.Resource;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.ResourcePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pageModel.Tree;


import java.util.List;

/**
 * 资源Service
 * 
 * @author
 */
public interface ResourceServiceI
{
    /**
     * 获得资源树(资源类型为菜单类型) 通过用户ID判断，他能看到的资源
     * 
     * @param sessionInfo
     * @return
     */
    public List<Tree> tree(String adminId) throws Exception;

    
    /**
     * 获得资源树(资源类型为菜单类型) 通过用户ID判断，他能看到的资源
     * 
     * @param sessionInfo
     * @return
     */
    public List<Resource> treetest(String adminId) throws Exception;
    
    /**
     * 获得资源树(包括所有资源类型) 通过用户ID判断，他能看到的资源
     * 
     * @param sessionInfo
     * @return
     */
    public List<Tree> allTree(SessionInfo sessionInfo) throws Exception;

    /**
     * 获得资源列表
     * 
     * @param sessionInfo
     * @return
     */
    public List<ResourcePage> treeGrid(SessionInfo sessionInfo) throws Exception;

    /**
     * 添加资源
     * 
     * @param resource
     * @param sessionInfo
     */
    public Json add(ResourcePage resourcepage, SessionInfo sessionInfo) throws Exception;

    /**
     * 删除资源
     * 
     * @param id
     */
    public Json delete(String id) throws Exception;

    /**
     * 修改资源
     * 
     * @param resource
     */
    public Json edit(ResourcePage resourcepage) throws Exception;

    /**
     * 获得一个资源
     * 
     * @param id
     * @return
     */
    public ResourcePage get(String id) throws Exception;
}
