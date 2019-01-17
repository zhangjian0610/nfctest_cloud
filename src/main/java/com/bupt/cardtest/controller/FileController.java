package com.bupt.cardtest.controller;


import com.bupt.cardtest.service.FileService;
import com.bupt.cardtest.service.RecordService;
import com.bupt.cardtest.service.WxService;
import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.SendHttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;


/**
 *
 * WEB端上传文件的控制器（之后要改成设备上传文件）
 *
 */
@Controller
public class FileController{

    private static final Logger logger=LoggerFactory.getLogger(FileController.class);

    @Autowired
    RecordService recordService;

    @Autowired
    FileService fileService;

    @Autowired
    WxService wxService;


    /**
     * 跳转上传文件页面
     *
     *
     */

    @RequestMapping("/upload")
    public String uploadPage(){
        return "/upload";
    }


    /**
     * web端上传文件
     *
     *
     */

    @RequestMapping(path = {"/uploading"})
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam("deviceId") String deviceId ){
        if(file.isEmpty()){
            return "文件为空";
        }
        if (deviceId==null||deviceId.length()==0) return "设备ID为空";
        //String fileName = file.getOriginalFilename();
        //int size = (int) file.getSize();
        //System.out.println(fileName + "-->" + size);
        //System.setProperty("user.timezone","GMT+8");
        String path = Constant.FILE_PATH +deviceId ;
        //Date uploadtime=null;
        String filename=UUID.randomUUID().toString().substring(0,5)+new Date().getTime();
        File dest = new File(path + "/" + filename);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件

            //uploadtime=new Date();
            //logger.info(uploadtime.toString());

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            logger.info(deviceId+"上传失败");
            e.printStackTrace();
            return "上传失败";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.info(deviceId+"上传失败");
            e.printStackTrace();
            return "上传失败";
        }


        //解析上传的文件 生成记录list并添加进数据库
        try {
            recordService.addRecords(path + "/" + filename,deviceId,filename);
        }catch (Exception e){
            logger.info(String.valueOf(deviceId)+"入库失败");
            return "上传失败，请重新上传";
        }
        Date uploadtime=new Date();
        try {

            logger.info(uploadtime.toString());
            fileService.addFile(path + "/" + filename,deviceId,filename,uploadtime);

        }catch (Exception e){
            logger.info(filename+"  文件信息入库失败");
        }



        logger.info(String.valueOf("设备"+deviceId)+"：文件入库成功");

        //SendHttpMessage.sendPostRequest("http://zhangjian.nat300.top/template/send",)
        List<String> openIds=wxService.findOpenIds(deviceId);

        if (openIds!=null&&openIds.size()!=0){
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            for (String openId:openIds
                 ) {
                wxService.sendTemplateToWx(deviceId,filename,openId,dateFormat.format(uploadtime));
            }
            logger.info("微信消息发送成功");
        }

        return "上传成功";
    }



}
