package com.bupt.cardtest.model.pageModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * session信息模型
 *
 * @author
 */
public class SessionInfo implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6561812689378348235L;

    private String id;// 用户ID

    private String name;// 用户登录名

    private String ip;// 用户IP

    //private String merchantId;// 商户ID

    private Map<String, String> resourceMap;

    private List<String> resourceList;// 用户可以访问的资源地址列表

    private Integer levelType;// 0全国管理员，1省级管理员，2赛区管理员
    //private Integer province;// 默认为0 ，当LEVEL_TYPE=0时 此字段为0 , 当type = 1（省级管理员）或 2
    // (赛区管理员) 此字段为省份ID

    //private List<String> divisionList;// 此字段为赛区ID

    //private Integer cityId;// 地区id
    //private String contestId;

    private Date operDate;
    private Date logDate;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(Map<String, String> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public List<String> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<String> resourceList) {
        this.resourceList = resourceList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }



    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }


    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Date getOperDate()
    {
        return operDate;
    }

    public void setOperDate(Date operDate)
    {
        this.operDate = operDate;
    }

}

