package com.bupt.cardtest.service.impl;

import com.bupt.cardtest.dao.ResourceMapper;
import com.bupt.cardtest.dao.ResourceTypeMapper;
import com.bupt.cardtest.dao.RoleMapper;
import com.bupt.cardtest.dao.RoleResourceMapper;
import com.bupt.cardtest.model.bean.*;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.ResourcePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pageModel.Tree;
import com.bupt.cardtest.service.ResourceServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceServiceI {
	@Autowired
	private ResourceMapper resourceDao;

	@Autowired
	private ResourceTypeMapper resourceTypeDao;

	@Autowired
	private RoleMapper roleDao;

	@Autowired
	private RoleResourceMapper roleResourceDao;

	@Override
	public List<Resource> treetest(String adminId) throws Exception {
		List<Resource> list_resources = resourceDao.getResourcesByAdminId(adminId, 1);
		return list_resources;
	}

	@Override
	public List<Tree> tree(String adminId) throws Exception {
		List<Tree> lt = new ArrayList<Tree>();
		List<Resource> list_resources = resourceDao.getResourcesByAdminId(adminId, -1);
		if (list_resources != null && list_resources.size() > 0) {
			for (Resource r : list_resources) {
				Tree tree = new Tree();
				tree.setId(r.getId());
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				if (r.getPid() != null && !"".equals(r.getPid())) {
					tree.setPid(r.getPid());
				}
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Tree> allTree(SessionInfo sessionInfo) throws Exception {
		List<Resource> list = resourceDao.getResourcesByAdminId(sessionInfo == null ? null : sessionInfo.getId(), -1);
		List<Tree> lt = new ArrayList<Tree>();
		if (list != null && list.size() > 0) {
			for (Resource resource : list) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(resource, tree);
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", resource.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<ResourcePage> treeGrid(SessionInfo sessionInfo) throws Exception {
		List<Resource> list = null;
		List<ResourcePage> listpage = new ArrayList<ResourcePage>();
		list = resourceDao.getResourcesByAdminId(sessionInfo == null ? null : sessionInfo.getId(), -1);
		if (list != null && list.size() > 0) {
			for (Resource r : list) {
				ResourcePage page = new ResourcePage();
				BeanUtils.copyProperties(r, page);
				page.setIconCls(r.getIcon() == null || r.getIcon().isEmpty() ? null : r.getIcon());
				ResourceType resourcetype = resourceTypeDao.selectByPrimaryKey(r.getTresourcetypeId());
				page.setTypeName(resourcetype.getName());
				listpage.add(page);
			}
		}
		return listpage;
	}

	@Override
	public Json add(ResourcePage resourcepage, SessionInfo sessionInfo) throws Exception {
		Json j = new Json();
		List<Role> listrole = roleDao.getListRoleByUserId(sessionInfo.getId());
		if (listrole != null && listrole.size() > 0) {
			String id = UUID.randomUUID().toString();
			resourcepage.setId(id);
			resourceDao.insert(resourcepage);
			for (Role r : listrole) {
				RoleResourceKey roleresource = new RoleResourceKey();
				roleresource.setTroleId(r.getId());
				roleresource.setTresourceId(id);
				roleResourceDao.insert(roleresource);
			}
			j.setSuccess(true);
			j.setMsg("添加成功");
		} else {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		return j;
	}

	@Override
	public Json delete(String id) throws Exception {
		Resource t = resourceDao.selectByPrimaryKey(id);
		Json j = new Json();
		if (t != null) {
			del(t);

			j.setSuccess(true);
			j.setMsg("成功删除");
			j.setObj(t);
		} else {
			j.setMsg("该资源信息不存在或已经删除");
			j.setSuccess(false);
		}
		return j;
	}

	private void del(Resource t) throws Exception {
		//Example examplePid = new Example(Resource.class);
		//examplePid.createCriteria().andEqualTo("pid", t.getId());

		ResourceExample resourceExample =new ResourceExample();
		resourceExample.createCriteria().andPidEqualTo(t.getId());
		List<Resource> list = resourceDao.selectByExample(resourceExample);
		if (list != null && list.size() > 0) {
			for (Resource resource : list) {
				del(resource);
			}
		}
		//Example e = new Example(RoleResource.class);
		//e.createCriteria().andEqualTo("tresourceId", t.getId());
		RoleResourceExample roleResourceExample =new RoleResourceExample();
		roleResourceExample.createCriteria().andTresourceIdEqualTo(t.getId());
		// 删除关联表数据
		roleResourceDao.deleteByExample(roleResourceExample);
		resourceDao.deleteByPrimaryKey(t.getId());
	}

	@Override
	public Json edit(ResourcePage resourcepage) throws Exception {
		Json j = new Json();
		Resource t = resourceDao.selectByPrimaryKey(resourcepage.getId());
		if (t != null) {
			resourceDao.updateByPrimaryKey(resourcepage);
			j.setSuccess(true);
			j.setMsg("修改成功");
		} else {
			j.setMsg("该资源信息不存在或已经删除,修改失败");
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * @return
	 *//*
		 * private boolean isChildren(Tresource t, Tresource pt) throws
		 * Exception { if (pt != null && pt.getTresource() != null) { if
		 * (pt.getTresource().getId().equalsIgnoreCase(t.getId())) {
		 * pt.setTresource(null); return true; } else { return isChildren(t,
		 * pt.getTresource()); } } return false; }
		 */
	@Override
	public ResourcePage get(String id) throws Exception {
		Resource resource = resourceDao.selectByPrimaryKey(id);
		ResourcePage r = new ResourcePage();
		if (resource != null) {
			BeanUtils.copyProperties(resource, r);

			if (resource.getPid() != null) {
				Resource resource2 = resourceDao.selectByPrimaryKey(resource.getPid());
				if (resource2 != null) {
					r.setPidName(resource2.getName());
				}
			}
			r.setIconCls(resource.getIcon() == null || resource.getIcon().isEmpty() ? null : resource.getIcon());
		}
		return r;
	}
}
