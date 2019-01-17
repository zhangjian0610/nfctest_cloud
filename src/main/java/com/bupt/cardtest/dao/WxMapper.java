package com.bupt.cardtest.dao;

import com.bupt.cardtest.model.pagebean.WxBean;

import java.util.List;

public interface WxMapper {


    /**
     * 通过设备ID查找所有拥有此设备而且绑定了微信公众号的用户及相应的openId
     * */
    public List<WxBean> selectOpenIds(String deviceId);
}
