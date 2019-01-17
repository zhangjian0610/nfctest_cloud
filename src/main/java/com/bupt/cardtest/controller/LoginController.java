package com.bupt.cardtest.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.service.LoginServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.laser.log.Log;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.IpUtil;
//import com.laser.util.aop.LogReturnAnnotation;
//import com.laser.util.aop.LogReturnAnnotation.OPERTYPE;
//import com.laser.util.validator.ValidatorAnnotation;

/**
 * 用户登录类 用户登录 用户退出
 *
 * @author zhangziqi
 * @version 2016年11月2日
 * @see LoginController
 * @since
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {

    private static final Logger logger=LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceI loginService;

    /**
     * 用户登录
     *
     * @param adminPage
     * @return
     * @throws Exception
     */
    //@ValidatorAnnotation(validatorParam = { "name","pwd","verifycode" }, cls = AdminPage.class)
    //@LogReturnAnnotation(oper = OPERTYPE.login)
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Json login(AdminPage adminPage) throws Exception {
        Json j = new Json();
        HttpSession session1 = request.getSession();
        if(session1.getAttribute("authCode")!=null){
            String authCode = (String)session1.getAttribute("authCode");
            if(adminPage.getVerifycode().equalsIgnoreCase(authCode)){
                j = loginService.login(adminPage, request);
                if (j.isSuccess()) {
                    // ibm appscan 会话标识未更新---------
                    session1 = request.getSession(false);
                    if (session1 != null) { // 让cookie过期
                        session1.invalidate();
                        Cookie cookie = request.getCookies()[0];// 获取cookie
                        cookie.setMaxAge(0);// 让cookie过期
                        cookie.setSecure(true);
                    }
                    session1 = request.getSession(true);// 生成新会话
                    // --------------------------------
                    SessionInfo sessioninfo = (SessionInfo) j.getObj();
                    sessioninfo.setIp(IpUtil.getIpAddr(request));
                    session1.setAttribute(Constant.SESSION_NAME, sessioninfo);
                    Constant.HASH_MAP.put(session1.getId(), session1);
                    //Log.info("[信息]：登陆|当前在线人数|" + Constant.HASH_MAP.size());
                    logger.info("[信息]：登陆|当前在线人数|" + Constant.HASH_MAP.size());
                    this.getSessionInfo().getResourceMap().put("/loginController/getPeople", "获取在线人数");
                }
            }else{
                j.setMsg("您输入的验证码不正确，请重新输入。");
                j.setSuccess(false);
            }
        }else{
            j.setMsg("验证码超时，请重新输入");
            j.setSuccess(false);
        }

        return j;
    }

    /**
     * 用户退出登录
     *
     * @return
     * @throws Exception
     */
    //@LogReturnAnnotation(oper = OPERTYPE.logout)
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Json logout() throws Exception {
        Json j = new Json();
        j.setSuccess(true);
        j.setMsg("用户没有登录或登录已超时。");
        if (session != null) {
            if (session.getAttribute(Constant.SESSION_NAME) != null) {
                // 清空该用户的最后一次操作时间和最后一次页面访问时间
                session.invalidate();
                j.setMsg("用户成功退出。");
            }
        }
        return j;
    }
}

