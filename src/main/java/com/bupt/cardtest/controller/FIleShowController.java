package com.bupt.cardtest.controller;

import com.bupt.cardtest.changevalue.ChangeValueInteger;
import com.bupt.cardtest.excelPOI.ExcelBulid;
import com.bupt.cardtest.excelPOI.ExcelHeader;
import com.bupt.cardtest.excelPOI.ExcelMessage;
import com.bupt.cardtest.excelPOI.ExcelZip;
import com.bupt.cardtest.model.bean.Record;
import com.bupt.cardtest.model.bean.Upfile;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pagebean.AdminDevicePage;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.model.pagebean.RecordPage;
import com.bupt.cardtest.service.FileShowService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * 显示文件和文件详情的控制器
 * */
@Controller
@RequestMapping("/fileController")
public class FIleShowController extends BaseController{


    @Autowired
    FileShowService fileShowService;


    /***
     *
     * 跳转到文件管理页面
     */

    @RequestMapping(value = "/manager")
    public String manager()throws Exception{
        this.getSessionInfo().getResourceMap().put("/fileController/dataGrid", "文件列表");
        return "/admin/file/file";

    }

    /***
     *
     * 获取文件数据表格
     */

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid dataGrid(AdminDevicePage adminDevicePage) throws Exception{
        SessionInfo sessionInfo = this.getSessionInfo();
        adminDevicePage.setId(sessionInfo.getId());
        adminDevicePage.setLevelType(sessionInfo.getLevelType());

        DataGrid dg = new DataGrid();
        dg=fileShowService.dataGrid(adminDevicePage);
        return dg;

    }


    /**
     *
     * 获取文件详情
     *
     * */

    @RequestMapping(value = "/fileInfo")
    public String fileinfo(String filename)throws Exception{
        //System.out.println("12345");

        request.setAttribute("fileName",filename);

        this.getSessionInfo().getResourceMap().put("/fileController/recordDataGrid", "记录列表");
        return "/admin/file/fileInfo";

    }


    /**
     *
     * 获取记录数据列表
     *
     * */
    @RequestMapping(value ="/recordDataGrid")
    @ResponseBody
    public DataGrid recordDataGrid(Record record){
        DataGrid dg=new DataGrid();
        dg=fileShowService.recordDataGrid(record);

        return dg;

    }

    /**
     *
     * 导出EXCEL
     *
     * */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(Record record, HttpServletResponse response) throws Exception{

        List<RecordPage> list=fileShowService.download(record);
        List<ExcelMessage> message = new ArrayList<ExcelMessage>();
        List<ExcelHeader> head = new ArrayList<ExcelHeader>();
        ExcelHeader excelheadA1 = new ExcelHeader();
        excelheadA1.setShowValue("设备ID");
        excelheadA1.setHeadField("deviceId");
        ExcelHeader excelheadA2 = new ExcelHeader();
        excelheadA2.setShowValue("文件名");
        excelheadA2.setHeadField("fileName");
        ExcelHeader excelheadA3 = new ExcelHeader();
        excelheadA3.setShowValue("起始");
        excelheadA3.setHeadField("start");
        excelheadA3.setBasechangevalue(new ChangeValueInteger());
        ExcelHeader excelheadA4 = new ExcelHeader();
        excelheadA4.setShowValue("结束");
        excelheadA4.setHeadField("end");
        excelheadA4.setBasechangevalue(new ChangeValueInteger());
        ExcelHeader excelheadA5 = new ExcelHeader();
        excelheadA5.setShowValue("源");
        excelheadA5.setHeadField("src");
        ExcelHeader excelheadA6 = new ExcelHeader();
        excelheadA6.setShowValue("数据");
        excelheadA6.setHeadField("data");
        ExcelHeader excelheadA7 = new ExcelHeader();
        excelheadA7.setShowValue("校验");
        excelheadA7.setHeadField("crc");
        ExcelHeader excelheadA8 = new ExcelHeader();
        excelheadA8.setShowValue("注释");
        excelheadA8.setHeadField("annotation");

        ExcelHeader excelheadA9=new ExcelHeader();
        excelheadA9.setShowValue("指令时长");
        excelheadA9.setHeadField("last");
        excelheadA9.setBasechangevalue(new ChangeValueInteger());

        ExcelHeader excelheadA10=new ExcelHeader();
        excelheadA10.setShowValue("间隔时长");
        excelheadA10.setHeadField("interval");
        excelheadA10.setBasechangevalue(new ChangeValueInteger());

        head.add(excelheadA1);
        head.add(excelheadA2);
        head.add(excelheadA3);
        head.add(excelheadA4);

        head.add(excelheadA9);
        head.add(excelheadA10);

        head.add(excelheadA5);
        head.add(excelheadA6);
        head.add(excelheadA7);
        head.add(excelheadA8);

        ExcelBulid tt = new ExcelBulid(1, 1000, true);
        List<SXSSFWorkbook> wb = tt.excelbuild("汇总", message, head, list);

        if (!wb.isEmpty() && wb.size() > 1) {
            File zip = new File("测试日志文件汇总汇总.zip");
            List<String> fileNames = new ArrayList<String>();
            FileOutputStream fileOut = null;
            for (int i = 0; i < wb.size(); i++) {
                try {
                    String sFileName = "测试日志文件详情信息(" + (i + 1) + ").xlsx";
                    fileNames.add(sFileName);
                    fileOut = new FileOutputStream(sFileName);
                    wb.get(i).write(fileOut);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fileOut != null) {
                        try {
                            fileOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            File srcfile[] = new File[fileNames.size()];
            for (int j = 0, n = fileNames.size(); j < n; j++) {
                srcfile[j] = new File(fileNames.get(j));
            }
            ExcelZip excelZip = new ExcelZip();
            excelZip.ZipFiles(srcfile, zip);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String("测试日志文件信息汇总".getBytes("UTF-8"), "ISO_8859_1") + ".zip");
            response.addHeader("Pargam", "no-cache");
            response.setHeader("Connection", "close");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream outputStream = response.getOutputStream();
            FileInputStream inStream = new FileInputStream(zip);
            byte[] buf = new byte[4096];
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
                outputStream.write(buf, 0, readLength);
            }
            inStream.close();
        } else if (!wb.isEmpty() && wb.size() == 1) {
            String userAgent = request.getHeader("user-agent");
            String sFileName = "测试日志文件信息汇总.xlsx";
            if (userAgent != null && userAgent.indexOf("Firefox") >= 0 || userAgent.indexOf("Chrome") >= 0
                    || userAgent.indexOf("Safari") >= 0) {
                sFileName = new String((sFileName).getBytes("UTF-8"), "ISO8859-1");
            } else {
                sFileName = URLEncoder.encode(sFileName, "UTF8"); // 其他浏览器
            }

            response.setContentType("text/javascript; charset=utf-8");

            response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
            response.addHeader("Pargam", "no-cache");
            response.setHeader("Connection", "close");
            response.addHeader("Cache-Control", "no-cache");

            wb.get(0).write(response.getOutputStream());
        }
    }




}
