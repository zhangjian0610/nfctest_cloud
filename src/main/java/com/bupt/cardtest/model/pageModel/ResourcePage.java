package com.bupt.cardtest.model.pageModel;

import com.bupt.cardtest.model.bean.Resource;


public class ResourcePage extends Resource {

    private String iconCls;

    private String typeName;
    
	private String pidName;

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

}
