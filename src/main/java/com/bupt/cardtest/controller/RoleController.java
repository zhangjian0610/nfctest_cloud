package com.bupt.cardtest.controller;

import com.bupt.cardtest.model.bean.Role;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.RolePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//import com.laser.util.validator.ValidatorAnnotation;

/**
 * 角色控制器
 * 
 * @author
 * 
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
	@Autowired
	private RoleServiceI roleService;

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		this.getSessionInfo().getResourceMap().put("/roleController/treeGrid", "角色列表");
		return "/admin/role/role";
	}

	/**
	 * 获得角色列表
	 * 
	 * @return
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.select)
	@RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
	@ResponseBody
	public List<RolePage> treeGrid() throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		return roleService.treeGrid(sessionInfo);
	}

	/**
	 * 跳转到角色添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		getSessionInfo().getResourceMap().put("/roleController/add", "添加角色");
		return "/admin/role/roleAdd";
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "remark" }, cls = RolePage.class)
	//@LogReturnAnnotation(oper = OPERTYPE.Add)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Json add(RolePage rolePage) throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		Json j = new Json();
		j = roleService.add(rolePage, sessionInfo);
		return j;
	}

	/**
	 * 跳转到角色修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.POST)
	public String editPage(String id) throws Exception {
		this.getSessionInfo().getResourceMap().put("/resourceController/allTree", "资源列表");
		this.getSessionInfo().getResourceMap().put("/roleController/edit", "修改角色");
		RolePage rolePage = roleService.get(id);
		if (rolePage == null) {
			return "/error/noInfo";
		}
		request.setAttribute("role", rolePage);
		return "/admin/role/roleEdit";
	}

	/**
	 * 修改角色
	 * 
	 * @param rolePage
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "remark" }, cls = RolePage.class)
	//@LogReturnAnnotation(oper = OPERTYPE.update)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Json edit(RolePage rolePage) throws Exception {
		Json j = new Json();
		j = roleService.edit(rolePage);
		return j;
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.delete)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id) throws Exception {
		Json j = new Json();
		j = roleService.delete(id);
		return j;
	}

	/**
	 * 跳转到角色授权页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/grantPage", method = RequestMethod.POST)
	public String grantPage(String id) throws Exception {
		Role r = roleService.get(id);
		if (r == null) {
			return "/error/noInfo";
		} else {
			getSessionInfo().getResourceMap().put("/resourceController/allTree", "资源查询");
			getSessionInfo().getResourceMap().put("/roleController/grant", "角色授权");
			request.setAttribute("role", r);
			return "/admin/role/roleGrant";
		}
	}

	/**
	 * 授权
	 * 
	 * @param role
	 * @return
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.update)
	@RequestMapping(value = "/grant", method = RequestMethod.POST)
	@ResponseBody
	public Json grant(RolePage rolePage) throws Exception {
		Json j = new Json();
		j = roleService.grant(rolePage);
		return j;
	}

	/**
	 * 授权角色树(不包含超级管理员角色)
	 * 
	 * @return
	 */
	@RequestMapping("/grantRoleTree")
	@ResponseBody
	public List<Role> grantTree() throws Exception {
		return roleService.grantRoleTree();
	}
}
