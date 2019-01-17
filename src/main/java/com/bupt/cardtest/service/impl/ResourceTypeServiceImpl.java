package com.bupt.cardtest.service.impl;


import com.bupt.cardtest.dao.ResourceTypeMapper;
import com.bupt.cardtest.model.bean.ResourceType;
import com.bupt.cardtest.model.bean.ResourceTypeExample;
import com.bupt.cardtest.service.ResourceTypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResourceTypeServiceImpl implements ResourceTypeServiceI
{

    @Autowired
    private ResourceTypeMapper resourceType;

    @Override
    public List<ResourceType> getResourceTypeList()
        throws Exception
    {
        ResourceTypeExample resourceTypeExample=new ResourceTypeExample();
        List<ResourceType> l = resourceType.selectByExample(resourceTypeExample);
        return l;
    }

}
