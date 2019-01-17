package com.bupt.cardtest.model.pageModel;

/**
 * EasyUI tree模型
 * 
 * @author
 */
public class Tree implements java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 4397964618001378526L;

    private String id;

    private String text;

    private String state = "open";// open,closed

    private boolean checked = false;

    private Object attributes;


    private String iconCls;

    private String pid;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public Object getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Object attributes)
    {
        this.attributes = attributes;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

}
