/*
 * 文件名：adminController.java 版权：Copyright by http://www.bjleisen.com/ 描述： 修改人：zhangziqi
 * 修改时间：2016年11月2日 跟踪单号： 修改单号： 修改内容：
 */
package com.bupt.cardtest.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.service.AdminServiceI;
import com.bupt.cardtest.service.ResourceServiceI;
import com.bupt.cardtest.service.RoleServiceI;
import com.bupt.cardtest.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
//import com.laser.util.validator.ValidatorAnnotation;

/**
 * 用户控制器
 * 
 * @author
 */
@Controller
@RequestMapping("/adminController")
public class AdminController extends BaseController {
	@Autowired
	private AdminServiceI adminService;

	@Autowired
	private ResourceServiceI resourceService;

	//@Autowired
	//private ProvinceServicel provinceServicel;

	//@Autowired
	//private LocalCityServicel localCityServicel;

	//@Autowired
	//private ContestServiceI contestServiceI;

	@Autowired
	private RoleServiceI roleServiceI;

	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager")
	public String manager() throws Exception {
		//List<Contest> contests = contestServiceI.getList(0);
		//request.setAttribute("contests", contests);
		request.setAttribute("levelType", this.getSessionInfo().getLevelType());
		this.getSessionInfo().getResourceMap().put("/adminController/dataGrid", "用户列表");
		return "/admin/user/user";
	}

	/**
	 * 获取用户数据表格
	 * 
	 * @param //user
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "page", "rows", "order" }, cls = SystemConfigPage.class)
	//@LogReturnAnnotation(oper = OPERTYPE.select)
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid dataGrid(AdminPage userPage) throws Exception {
		SessionInfo sessionInfo = this.getSessionInfo();
		/*if (sessionInfo.getLevelType() != Constant.ADMIN_LEVEL_TYPE_0) {
			if (userPage.getContestId() == null || "".equals(userPage.getContestId())) {
				userPage.setContestId(sessionInfo.getContestId());

			}
			if (userPage.getProvinceId() == null || "".equals(userPage.getProvinceId())) {
				if (sessionInfo.getProvince() != Constant.RAN_LEVEL_TYPE_0) {
					userPage.setProvinceId(sessionInfo.getProvince());
				}
			}
			if (userPage.getCityId() == null || "".equals(userPage.getCityId())) {
				if (sessionInfo.getCityId() != Constant.RAN_LEVEL_TYPE_0) {
					userPage.setCityId(sessionInfo.getCityId());
				}
			}
		} else if (sessionInfo.getLevelType() == Constant.ADMIN_LEVEL_TYPE_2) {

		}
		*/
		if (sessionInfo.getLevelType() != Constant.ADMIN_LEVEL_TYPE_0){
			throw new Exception("不是超级管理员无权获取管理员信息");
		}
		DataGrid dg = new DataGrid();
		dg = adminService.dataGrid(userPage);
		return dg;
	}

	/**
	 * 跳转到添加用户页面
	 * 
	 * @param //request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPage")
	public String addPage() throws Exception {
		//this.getRequestLevl(request, null, null, null);
		request.setAttribute("levelType", this.getSessionInfo().getLevelType());
		this.getSessionInfo().getResourceMap().put("/adminController/add", "用户添加");
		//this.getSessionInfo().getResourceMap().put("/adminController/getProvince", "获取省级列表");
		//this.getSessionInfo().getResourceMap().put("/adminController/grantCity", "获得城市列表");
		return "/admin/user/userAdd";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "contestId", "levelType", "provinceId",
	//		"cityId" }, cls = AdminPage.class)
	//@LogReturnAnnotation(oper = OPERTYPE.Add)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Json add(AdminPage userPage) throws Exception {
		System.out.println("添加用户");
		Json j = new Json();
		j = adminService.add(userPage);
		return j;
	}

	/**
	 * 跳转到用户修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.POST)
	public String editPage(String id) throws Exception {

		request.setAttribute("roles", roleServiceI.grantRoleTree(this.getSessionInfo()));
		AdminPage userPage = adminService.getUser(id);
		if (userPage == null) {
			return "/error/noInfo";
		}
		//this.getRequestLevl(request, userPage.getContestId(), userPage.getProvinceId(), userPage.getCityId());

		request.setAttribute("levelType", this.getSessionInfo().getLevelType());
		getSessionInfo().getResourceMap().put("/roleController/grantRoleTree", "角色查询");
		this.getSessionInfo().getResourceMap().put("/adminController/edit", "用户修改");
		//this.getSessionInfo().getResourceMap().put("/adminController/getProvince", "获取省级列表");
		//this.getSessionInfo().getResourceMap().put("/adminController/grantCity", "获得城市列表");
		request.setAttribute("levelTypes", userPage.getLevelType());
		request.setAttribute("user", userPage);
		return "/admin/user/userEdit";
	}

	/**
	 * 修改用户
	 * 
	 * @param //user
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "contestId", "levelType", "provinceId",
	//		"cityId" }, cls = AdminPage.class)
	//@LogReturnAnnotation(oper = OPERTYPE.update)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Json edit(AdminPage userPage) throws Exception {
		Json j = new Json();
		SessionInfo sessionInfo = getSessionInfo();
		j = adminService.edit(userPage, sessionInfo);
		return j;
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.delete)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id) throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		Json j = new Json();
		j = adminService.delete(id, sessionInfo);
		return j;
	}

	/**
	 * @Description: 重置用户密码
	 * @Check
	 * @throws Exception
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.update)
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public Json resetPwd(String id) throws Exception {
		Json j = adminService.resetPwd(id);
		return j;
	}

	/**
	 * 跳转到修改自己的密码页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editCurrentUserPwdPage", method = RequestMethod.POST)
	public String editCurrentUserPwdPage() {
		return "/admin/user/editPwd";
	}

	/**
	 * 修改自己的密码
	 * 
	 * @param //session
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/editCurrentUserPwd", method = RequestMethod.POST)
	@ResponseBody
	public Json editCurrentUserPwd(String oldPwd, String pwd) throws Exception {
		Json j = new Json();
		SessionInfo sessionInfo = getSessionInfo();
		j = adminService.editCurrentUserPwd(sessionInfo, oldPwd, pwd);
		return j;
	}

	/**
	 * 跳转到显示用户权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/currentUserResourcePage", method = RequestMethod.POST)
	public String currentUserResourcePage() throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		request.setAttribute("userResources", JSON.toJSONString(resourceService.allTree(sessionInfo)));
		return "/user/userResource";
	}


}
