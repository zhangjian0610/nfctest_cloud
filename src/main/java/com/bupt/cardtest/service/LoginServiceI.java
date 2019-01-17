/*
 * 文件名：LoginServiceI.java
 * 版权：Copyright by http://www.bjleisen.com/
 * 描述：
 * 修改人：zhangziqi
 * 修改时间：2016年11月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bupt.cardtest.service;

import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.model.pageModel.Json;

import javax.servlet.http.HttpServletRequest;

public interface LoginServiceI
{

    /**
     * 用户登录
     * 
     * @param adminPage
     * @return 用户对象
     * @throws Exception
     */
	public Json login(AdminPage adminPage, HttpServletRequest request)
        throws Exception;


    
    
}
