package com.bupt.cardtest.model.pageModel;

import com.bupt.cardtest.model.bean.Role;


public class RolePage extends Role
{
    private String resourceIds;
    private String resourceNames;

    public String getResourceIds()
    {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds)
    {
        this.resourceIds = resourceIds;
    }

    public String getResourceNames()
    {
        return resourceNames;
    }

    public void setResourceNames(String resourceNames)
    {
        this.resourceNames = resourceNames;
    }
}
