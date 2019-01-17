package com.bupt.cardtest.service;


import java.util.List;

public interface WxService {


    void sendTemplateToWx(String deviceId,String fileName,String openId,String uploadtime);


    List<String> findOpenIds(String deviceId);
}
