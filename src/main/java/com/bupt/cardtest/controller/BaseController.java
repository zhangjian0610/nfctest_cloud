package com.bupt.cardtest.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.StringDateToDateEditor;
import com.bupt.cardtest.util.StringEscapeEditor;


/**
 * 基础控制器 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 *
 * @author
 */
@Controller
@RequestMapping("/baseController")
public class BaseController
{

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder)
    {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new StringDateToDateEditor());
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession        session;

    /**
     * 获取当前的sessionInfo信息 登录用户名 、ip等信息
     *
     * @return
     */
    public SessionInfo getSessionInfo()
    {
        return (SessionInfo)session.getAttribute(Constant.SESSION_NAME);
    }

    /**
     * 获得当前项目的绝对路径
     *
     * @return
     */
    public String getTruePath()
    {
        String requestUri = request.getSession().getServletContext().getRealPath("/");
        return requestUri;
    }

    /**
     * 获取项目的请求地址(只到项目名) 例：http://127.0.0.1:8080/laser_base_frame/
     *
     * @return
     */
    public String getRequestPath()
    {
        String path = request.getContextPath();
        String requestpath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return requestpath;
    }
}

