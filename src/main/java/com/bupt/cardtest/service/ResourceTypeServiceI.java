package com.bupt.cardtest.service;


import com.bupt.cardtest.model.bean.ResourceType;

import java.util.List;


/**
 * 资源类型服务
 * 
 * @author
 */
public interface ResourceTypeServiceI
{

    /**
     * 获取资源类型
     * 
     * @return
     */
    public List<ResourceType> getResourceTypeList()
        throws Exception;

}
