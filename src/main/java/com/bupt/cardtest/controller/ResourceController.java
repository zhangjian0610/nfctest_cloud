package com.bupt.cardtest.controller;

import com.bupt.cardtest.model.bean.ResourceType;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.ResourcePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pageModel.Tree;
import com.bupt.cardtest.service.ResourceServiceI;
import com.bupt.cardtest.service.ResourceTypeServiceI;
import com.bupt.cardtest.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//import com.laser.util.validator.ValidatorAnnotation;

/**
 * 资源控制器
 * 
 * @author
 */
@Controller
@RequestMapping("/resourceController")
public class ResourceController extends BaseController {
	@Autowired
	private ResourceServiceI resourceService;

	@Autowired
	private ResourceTypeServiceI resourceTypeService;

	/**
	 * 获得资源树(资源类型为菜单类型) 通过用户ID判断，他能看到的资源
	 * 
	 * @param //session
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree() throws Exception {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constant.SESSION_NAME);
		return resourceService.tree(sessionInfo.getId());
	}

	/**
	 * 获得资源树(包括所有资源类型) 通过用户ID判断，他能看到的资源
	 * 
	 * @param //session
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		return resourceService.allTree(sessionInfo);
	}

	/**
	 * 跳转到资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		this.getSessionInfo().getResourceMap().put("/resourceController/treeGrid", "资源列表");
		return "/admin/resource/resource";
	}

	/**
	 * 获得资源列表 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 */
	//@LogReturnAnnotation(oper = OPERTYPE.select)
	@RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourcePage> treeGrid() throws Exception {
		SessionInfo sessionInfo = getSessionInfo();
		List<ResourcePage> treeGrid = resourceService.treeGrid(sessionInfo);
		return treeGrid;
	}

	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage() throws Exception {
		this.getSessionInfo().getResourceMap().put("/resourceController/allTree", "资源列表");
		getSessionInfo().getResourceMap().put("/resourceController/add", "添加资源");
		List<ResourceType> resourceTypeList = resourceTypeService.getResourceTypeList();
		request.setAttribute("resourceTypeList", resourceTypeList);
		return "/admin/resource/resourceAdd";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "tresourcetypeId", "url", "seq", "text", "iconCls",
	//		"remark" }, cls = ResourcePage.class)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@LogReturnAnnotation(oper = OPERTYPE.Add)
	@ResponseBody
	public Json add(ResourcePage resourcepage) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constant.SESSION_NAME);
		Json j = resourceService.add(resourcepage, sessionInfo);
		return j;
	}

	/**
	 * 跳转到资源修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.POST)
	public String editPage(String id) throws Exception {
		this.getSessionInfo().getResourceMap().put("/resourceController/allTree", "资源列表");
		request.setAttribute("resourceTypeList", resourceTypeService.getResourceTypeList());
		getSessionInfo().getResourceMap().put("/resourceController/edit", "修改资源");
		ResourcePage r = resourceService.get(id);
		if (r == null) {
			return "/error/noInfo";
		}
		request.setAttribute("resource", r);
		return "/admin/resource/resourceEdit";
	}

	/**
	 * 修改资源
	 * 
	 * @param //resource
	 * @return
	 */
	//@ValidatorAnnotation(validatorParam = { "name", "tresourcetypeId", "url", "seq", "text", "iconCls",
	//		"remark" }, cls = ResourcePage.class)
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	//@LogReturnAnnotation(oper = OPERTYPE.update)
	@ResponseBody
	public Json edit(ResourcePage resourcepage) throws Exception {
		Json j = resourceService.edit(resourcepage);
		return j;
	}

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	//@LogReturnAnnotation(oper = OPERTYPE.delete)
	@ResponseBody
	public Json delete(String id) throws Exception {
		Json j = resourceService.delete(id);
		return j;
	}
}
