package com.bupt.cardtest.service.impl;

import com.bupt.cardtest.dao.AdminRoleMapper;
import com.bupt.cardtest.dao.ResourceMapper;
import com.bupt.cardtest.dao.RoleMapper;
import com.bupt.cardtest.dao.RoleResourceMapper;
import com.bupt.cardtest.model.bean.*;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.RolePage;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.service.RoleServiceI;
import com.bupt.cardtest.util.Constant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleServiceI {
	@Autowired
	private RoleMapper roleDao;

	@Autowired
	private ResourceMapper resourceDao;

	@Autowired
	private RoleResourceMapper roleResourceDao;

	@Autowired
	private AdminRoleMapper adminRoleDao;

	@Override
	public List<RolePage> treeGrid(SessionInfo sessionInfo) throws Exception {
		List<RolePage> listpage = new ArrayList<RolePage>();
		List<Role> list = null;
		if (Constant.USER_ID_0.equals(sessionInfo.getId())) {

			//Example example = new Example(Role.class);
			//example.setOrderByClause(" seq ASC ");
			RoleExample roleExample=new RoleExample();
			roleExample.setOrderByClause(" seq ASC ");
			list = roleDao.selectByExample(roleExample);
		} else {
			list = roleDao.getListRoleByUserId(sessionInfo == null ? null : sessionInfo.getId());
		}
		if (list != null && list.size() > 0) {
			for (Role role : list) {
				RolePage rolePage = new RolePage();
				BeanUtils.copyProperties(role, rolePage);

				//Example example = new Example(RoleResource.class);
				//example.createCriteria().andEqualTo("troleId", rolePage.getId());
				RoleResourceExample roleResourceExample=new RoleResourceExample();
				roleResourceExample.createCriteria().andTroleIdEqualTo(rolePage.getId());

				List<RoleResourceKey> roleResourceList = roleResourceDao.selectByExample(roleResourceExample);
				if (roleResourceList != null && roleResourceList.size() > 0) {
					boolean b = false;
					String ids = "";
					String names = "";
					for (RoleResourceKey roleResource : roleResourceList) {
						Resource resource = resourceDao.selectByPrimaryKey(roleResource.getTresourceId());
						if (resource != null) {
							if (b) {
								ids += ",";
								names += ",";
							} else {
								b = true;
							}
							ids += resource.getId();
							names += resource.getName();
						}
					}
					rolePage.setResourceIds(ids);
					rolePage.setResourceNames(names);
				}
				listpage.add(rolePage);
			}
		}
		return listpage;
	}

	@Override
	public Json add(RolePage rolePage, SessionInfo sessionInfo) throws Exception {
		Json j = new Json();
		//Example example = new Example(Role.class);
		//example.createCriteria().andEqualTo("name", rolePage.getName());
		RoleExample example=new RoleExample();
		example.createCriteria().andNameEqualTo(rolePage.getName());
		List<Role> role = roleDao.selectByExample(example);
		if (role != null && role.size() > 0) {
			j.setSuccess(false);
			j.setMsg("添加失败，该系统角色名称已存在！");
			return j;
		}
		String id = UUID.randomUUID().toString();
		rolePage.setId(id);
		Long long1 = new Date().getTime();
		rolePage.setSeq(Integer.parseInt(long1.toString().substring(0, 10)));
		roleDao.insert(rolePage);
		AdminRoleKey userRole = new AdminRoleKey();
		userRole.setTadminId(sessionInfo.getId());
		userRole.setTroleId(id);
		adminRoleDao.insert(userRole);
		j.setSuccess(true);
		j.setMsg("添加成功");
		return j;
	}

	public static void main(String[] args) {
		Long long1 = new Date().getTime();
		System.out.println(Integer.parseInt(long1.toString().substring(0, 10)));
	}

	@Override
	public RolePage get(String id) throws Exception {
		RolePage rolePage = new RolePage();
		Role role = roleDao.selectByPrimaryKey(id);
		if (role != null) {
			BeanUtils.copyProperties(role, rolePage);

			//Example example = new Example(RoleResource.class);
			//example.createCriteria().andEqualTo("troleId", rolePage.getId());
			RoleResourceExample roleResourceExample=new RoleResourceExample();
			roleResourceExample.createCriteria().andTroleIdEqualTo(rolePage.getId());

			List<RoleResourceKey> roleResourceList = roleResourceDao.selectByExample(roleResourceExample);
			if (roleResourceList != null && roleResourceList.size() > 0) {
				boolean b = false;
				String ids = "";
				String names = "";
				for (RoleResourceKey roleResource : roleResourceList) {
					Resource resource = resourceDao.selectByPrimaryKey(roleResource.getTresourceId());
					if (b) {
						ids += ",";
						names += ",";
					} else {
						b = true;
					}
					ids += resource.getId();
					names += resource.getName();
				}
				rolePage.setResourceIds(ids);
				rolePage.setResourceNames(names);
			}
		}
		return rolePage;
	}

	@Override
	public Json edit(RolePage rolePage) throws Exception {
		Json j = new Json();
		Role role = roleDao.selectByPrimaryKey(rolePage.getId());
		if (role == null) {
			j.setMsg("该角色信息不存在或已经删除,修改失败");
			j.setSuccess(false);
			return j;
		}
		if (role.getId() != null && role.getId().equals(Constant.ROLE_ID_0)) {
			j.setSuccess(false);
			j.setMsg("该角色为超管角色，不能修改！");
			return j;
		}
		if (!role.getName().equals(rolePage.getName())) {
			//Example example = new Example(Role.class);
			//example.createCriteria().andEqualTo("name", rolePage.getName());
			RoleExample roleExample=new RoleExample();
			roleExample.createCriteria().andNameEqualTo(rolePage.getName());

			List<Role> roles = roleDao.selectByExample(roleExample);
			if (roles.size() > 0) {
				j.setSuccess(false);
				j.setMsg("该角色名称已经存在！");
				return j;
			}
		}

		//Example example = new Example(RoleResource.class);
		//example.createCriteria().andEqualTo("troleId", rolePage.getId());
		RoleResourceExample roleResourceExample=new RoleResourceExample();
		roleResourceExample.createCriteria().andTroleIdEqualTo(rolePage.getId());
		roleResourceDao.deleteByExample(roleResourceExample);
		if (rolePage.getResourceIds() != null && !rolePage.getResourceIds().equalsIgnoreCase("")) {
			for (String resourceId : rolePage.getResourceIds().split(",")) {
				RoleResourceKey roleresource = new RoleResourceKey();
				roleresource.setTroleId(rolePage.getId());
				roleresource.setTresourceId(resourceId);
				roleResourceDao.insert(roleresource);
			}
		}
		rolePage.setSeq(role.getSeq());
		roleDao.updateByPrimaryKey(rolePage);
		j.setSuccess(true);
		j.setMsg("修改成功");
		return j;
	}

	@Override
	public Json delete(String id) throws Exception {
		Json j = new Json();
		Role role = roleDao.selectByPrimaryKey(id);
		if (role == null) {
			j.setMsg("该角色信息不存在或已经删除,修改失败");
			j.setSuccess(false);
		}
		if (id != null && id.equals(Constant.ROLE_ID_0)) {
			j.setSuccess(false);
			j.setMsg("授权失败，该角色为超管角色，不能删除！");
			return j;
		}
		//Example exampleUserRole = new Example(AdminRole.class);
		//exampleUserRole.createCriteria().andEqualTo("troleId", id);
		AdminRoleExample adminRoleExample =new AdminRoleExample();
		adminRoleExample.createCriteria().andTroleIdEqualTo(id);
		adminRoleDao.deleteByExample(adminRoleExample);

		//Example exampleRoleResource = new Example(RoleResource.class);
		//exampleRoleResource.createCriteria().andEqualTo("troleId", id);
		RoleResourceExample roleResourceExample=new RoleResourceExample();
		roleResourceExample.createCriteria().andTroleIdEqualTo(id);
		roleResourceDao.deleteByExample(roleResourceExample);
		//roleDao.delete(role);
		roleDao.deleteByPrimaryKey(id);
		j.setSuccess(true);
		j.setMsg("删除成功");
		j.setObj(role);
		return j;
	}

	@Override
	public Json grant(RolePage rolePage) throws Exception {
		Json j = new Json();
		Role role = roleDao.selectByPrimaryKey(rolePage.getId());
		if (role == null) {
			j.setMsg("该角色信息不存在或已经删除,授权失败");
			j.setSuccess(false);
			return j;
		}
		if (rolePage.getId() != null && rolePage.getId().equals(Constant.ROLE_ID_0)) {
			j.setSuccess(false);
			j.setMsg("授权失败，该角色为超管角色，不能授权！");
			return j;
		}

		//Example example = new Example(RoleResource.class);
		//example.createCriteria().andEqualTo("troleId", rolePage.getId());
		RoleResourceExample roleResourceExample =new RoleResourceExample();
		roleResourceExample.createCriteria().andTroleIdEqualTo(rolePage.getId());
		roleResourceDao.deleteByExample(roleResourceExample);
		if (rolePage.getResourceIds() != null && !rolePage.getResourceIds().equalsIgnoreCase("")) {
			for (String resourceId : rolePage.getResourceIds().split(",")) {
				RoleResourceKey roleresource = new RoleResourceKey();
				roleresource.setTroleId(rolePage.getId());
				roleresource.setTresourceId(resourceId);
				roleResourceDao.insert(roleresource);
			}
		}
		j.setSuccess(true);
		j.setMsg("授权成功");
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
	 */
	/*
	 * private boolean isChildren(Trole t, Trole pt) throws Exception { if (pt
	 * != null && pt.getTrole() != null) { if
	 * (pt.getTrole().getId().equalsIgnoreCase(t.getId())) { pt.setTrole(null);
	 * return true; } else { return isChildren(t, pt.getTrole()); } } return
	 * false; }
	 *//**
		 * 去掉List内重复的信息
		 * 
		 * @param tl
		 * @return
		 */

	/*
	 * public List<Trole> removal(List<Trole> tl) { List<Trole> result = new
	 * ArrayList<Trole>(); for (Trole t : tl) { if (!result.contains(t)) {
	 * result.add(t); } } return result; }
	 *//**
		 * 查询出所有的子角色
		 * 
		 * @param
		 * @return
		 *//*
		 * public List<Trole> findChildRole(List<Trole> tl) { List<Trole> result
		 * = new ArrayList<Trole>(); if (tl != null && tl.size() != 0) { for
		 * (Trole t : tl) { if (!result.contains(t)) { result.add(t); }
		 * Set<Trole> troles = t.getTroles(); List<Trole> l = new
		 * ArrayList<Trole>(); if (troles != null && troles.size() != 0) {
		 * Iterator<Trole> iterator = troles.iterator(); while
		 * (iterator.hasNext()) { Trole temp = iterator.next(); l.add(temp); } }
		 * result.addAll(findChildRole(l)); } } return result; }
		 * 
		 * @Override public void delete(String id) throws Exception { Trole t =
		 * roleDao.get(Trole.class, id); if (t != null) { del(t); } {
		 * logger.info("该角色信息不存在或已经删除"); } } private void del(Trole t) throws
		 * Exception { if (t.getTroles() != null && t.getTroles().size() > 0) {
		 * for (Trole r : t.getTroles()) { del(r); } } roleDao.delete(t); }
		 * 
		 * @Override public List<Tree> tree(SessionInfo sessionInfo) throws
		 * Exception { List<Trole> l = null; List<Tree> lt = new
		 * ArrayList<Tree>(); Map<String, Object> params = new HashMap<String,
		 * Object>(); params.put("roleType", Constant.MANAGER); if (sessionInfo
		 * != null) { params.put("userId", sessionInfo.getId());// 查自己有权限的角色 l =
		 * roleDao.find(
		 * "select distinct t from Trole t join fetch t.tusers user where t.roleType = :roleType and user.id = :userId  order by t.seq"
		 * , params); } else { l = roleDao.find(
		 * "from Trole t where t.roleType = :roleType order by t.seq", params);
		 * } l = findChildRole(l); l = removal(l); if (l != null && l.size() >
		 * 0) { for (Trole t : l) { Tree tree = new Tree();
		 * BeanUtils.copyProperties(t, tree); tree.setText(t.getName());
		 * tree.setIconCls("status_online"); if (t.getTrole() != null) {
		 * tree.setPid(t.getTrole().getId()); } lt.add(tree); } } return lt; }
		 * 
		 * @Override public List<Tree> grantRoleTree() throws Exception {
		 * List<Trole> l = null; List<Tree> lt = new ArrayList<Tree>();
		 * Map<String, Object> params = new HashMap<String, Object>(); l =
		 * roleDao.find( "from Trole t where t.id<> '0' order by t.seq",
		 * params); l = findChildRole(l); l = removal(l); if (l != null &&
		 * l.size() > 0) { for (Trole t : l) { Tree tree = new Tree();
		 * BeanUtils.copyProperties(t, tree); tree.setText(t.getName());
		 * tree.setIconCls("status_online"); if (t.getTrole() != null) {
		 * tree.setPid(t.getTrole().getId()); } lt.add(tree); } } return lt; }
		 * 
		 * @Override public List<Tree> allTree() throws Exception { return
		 * this.tree(null); }
		 */
	@Override
	public List<Role> grantRoleTree() throws Exception {
		//return roleDao.selectAll();
		RoleExample roleExample=new RoleExample();
		return roleDao.selectByExample(roleExample);

	}

	@Override
	public List<Role> grantRoleTree(SessionInfo sessionInfo) throws Exception {
		List<Role> list = new ArrayList<>();
		if (Constant.USER_ID_0.equals(sessionInfo.getId())) {
			//Example example = new Example(Role.class);
			//example.setOrderByClause(" seq ASC ");
			RoleExample example=new RoleExample();
			example.setOrderByClause(" seq ASC ");//不确定
			list = roleDao.selectByExample(example);
		} else {
			list = roleDao.getListRoleByUserId(sessionInfo == null ? null : sessionInfo.getId());
		}
		return list;
	}
}
