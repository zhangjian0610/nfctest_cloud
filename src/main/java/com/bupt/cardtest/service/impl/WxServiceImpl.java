package com.bupt.cardtest.service.impl;

import com.bupt.cardtest.dao.WxMapper;
import com.bupt.cardtest.model.pagebean.WxBean;
import com.bupt.cardtest.service.WxService;
import com.bupt.cardtest.util.SendHttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxServiceImpl implements WxService {

    @Autowired
    WxMapper wxDao;

    @Override
    public List<String> findOpenIds(String deviceId) {

        List<WxBean>l= wxDao.selectOpenIds(deviceId);
        List<String> res=new ArrayList<>();
        for (WxBean user:l
             ) {
            res.add(user.getOpenId());
        }
        return res;
    }

    @Override
    public void sendTemplateToWx(String deviceId, String fileName, String openId, String uploadTime) {
        String url="http://zhangjian.nat300.top/template/send";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("deviceId",deviceId);
        params.add("fileName",fileName);
        params.add("openId",openId);
        params.add("uploadTime",uploadTime);
        SendHttpMessage.sendPostRequest(url,params);
    }
}
