package com.bupt.cardtest.service.impl;

import com.bupt.cardtest.dao.AdminMapper;
import com.bupt.cardtest.dao.AdminRoleMapper;
import com.bupt.cardtest.dao.RoleMapper;
import com.bupt.cardtest.dao.SystemConfigMapper;
import com.bupt.cardtest.model.bean.*;
import com.bupt.cardtest.model.pageModel.DataGrid;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.service.AdminServiceI;
import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.LaserUtils;
import com.bupt.cardtest.util.PBKDF2;
import com.bupt.cardtest.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import com.laser.util.LaserUtil;

@Service
public class AdminServiceImpl implements AdminServiceI {
	@Autowired
	private AdminMapper adminDao;
	//@Autowired
	//private ProvinceMapper provinceMapper;
	//@Autowired
	//private LocalCityMapper localCityMapper;

	@Autowired
	private SystemConfigMapper systemConfigDao;

	@Autowired
	private AdminRoleMapper adminRoleDao;
	//@Autowired
	//private ContestMapper contestMapper;

	@Autowired
	private RoleMapper roleDao;

	@Override
	public DataGrid dataGrid(AdminPage adminPage) throws Exception {
		adminPage.setName(LaserUtils.StringSymbol(adminPage.getName()));//多余 adminPage中的name=""
		PageHelper.startPage(adminPage.getPage(), adminPage.getRows());//设置分页
		DataGrid dg = new DataGrid();
		List<AdminPage> l = adminDao.dataGrid(adminPage);//分页取出所有管理员
		Long toatl = adminDao.dataGridCount(adminPage);//取出管理员总数
		List<AdminPage> list = new ArrayList<AdminPage>();
		if (l != null && l.size() > 0) {
			for (Admin admin : l) {
				AdminPage adminpage = new AdminPage();
				BeanUtils.copyProperties(admin, adminpage);

				list.add(adminpage);
				//Example example = new Example(AdminRole.class);
				AdminRoleExample example=new AdminRoleExample();
				//example.createCriteria().andEqualTo("tadminId", admin.getId());
				example.createCriteria().andTadminIdEqualTo(admin.getId());
				//List<AdminRole> AdminRoleList = adminRoleDao.selectByExample(example);
				//注意此行返回 AdminRoleKey 其实和AdminRole一样 逆向工程生成的
				List<AdminRoleKey> AdminRoleList =adminRoleDao.selectByExample(example);


				if (AdminRoleList != null && AdminRoleList.size() > 0) {
					String roleIds = "";
					String roleNames = "";
					boolean b = false;
					for (AdminRoleKey AdminRole : AdminRoleList) {
						Role role = roleDao.selectByPrimaryKey(AdminRole.getTroleId());
						if (role != null) {
							if (b) {
								roleIds += ",";
								roleNames += ",";
							} else {
								b = true;
							}
							roleIds += role.getId();
							roleNames += role.getName();
						}
					}
					adminpage.setRoleIds(roleIds);
					adminpage.setRoleNames(roleNames);
				}
			}
		}
		dg.setRows(list);
		dg.setTotal((long) toatl);
		return dg;
	}

	@Override
	public Json add(AdminPage userPage) throws Exception {
		Json j = new Json();
		//Example example = new Example(Admin.class);
		//example.createCriteria().andEqualTo("name", userPage.getName());
		AdminExample example =new AdminExample();
		example.createCriteria().andNameEqualTo(userPage.getName());
		List<Admin> userList = adminDao.selectByExample(example);
		if (userList != null && userList.size() > 0) {
			j.setMsg("用户名已存在！");
			j.setSuccess(false);
			return j;
		}
		if (userPage.getEmail() != null && !"".equals(userPage.getEmail())) {
			//example = new Example(Admin.class);
			//example.createCriteria().andEqualTo("email", userPage.getEmail());
			AdminExample example1=new AdminExample();
			example1.createCriteria().andEmailEqualTo(userPage.getEmail());
			userList = adminDao.selectByExample(example1);
			if (userList != null && userList.size() > 0) {
				j.setMsg("邮箱已存在！");
				j.setSuccess(false);
				return j;
			}
		}
		if (userPage.getEmail() != null && !"".equals(userPage.getPhone())) {

			//example = new Example(Admin.class);
			//example.createCriteria().andEqualTo("phone", userPage.getPhone());
			AdminExample example2=new AdminExample();
			example2.createCriteria().andPhoneEqualTo(userPage.getPhone());
			userList = adminDao.selectByExample(example2);
			if (userList != null && userList.size() > 0) {
				j.setMsg("手机号已存在！");
				j.setSuccess(false);
				return j;
			}
		}
		/*
		if (userPage.getProvinceId() == null) {
			userPage.setProvinceId(Constant.RAN_LEVEL_TYPE_0);
		}
		if (userPage.getCityId() == null) {
			userPage.setCityId(Constant.RAN_LEVEL_TYPE_0);
		}
		*/
		userPage.setId(UUID.randomUUID().toString());
		userPage.setCreatedatetime(new Date());
		userPage.setModifydatetime(new Date());
		byte[] salt = PBKDF2.getSalt().getBytes();
		String pwd1 = RandomUtil.generateWord();
		String pwd = PBKDF2.generateStorngPasswordHash256ByBCP(salt, LaserUtils.sha256(pwd1));
		userPage.setPwd(pwd);
		int REMAINING_LOGINS = Constant.REMAINING_LOGINS;
		// 查询用户每天的剩余登录次数
		SystemConfig systemconfig = systemConfigDao.selectByPrimaryKey(Constant.REMAINING_LOGINS_STR);
		if (systemconfig != null) {
			REMAINING_LOGINS = Short.parseShort(systemconfig.getConfContext());
		}
		userPage.setRemainingLogins(REMAINING_LOGINS);
		userPage.setLockSymbol(Constant.Int_Boolean_Result.No.getValue());
		/*
		 * SendMail s = new SendMail(); String flag = s.SendPwdmail(
		 * "<pre>您好：<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;" +
		 * "管理员为您创建了用户,用户名为&nbsp;" + userPage.getName() +
		 * "&nbsp;&nbsp;密码为&nbsp;" + pwd1 + "&nbsp;&nbsp;&nbsp;&nbsp;【乒乓在沃管理平台】"
		 * + "</pre>", userPage.getEmail()); Log.info("[密码通知]：" + flag);
		 */
		/*
		 * if (!flag.equals("success")) { if (flag.equals("邮箱地址不正确,邮件发送失败!")) {
		 * j.setSuccess(false); } else { j.setSuccess(false); } j.setMsg(flag);
		 * } else {
		 */
		adminDao.insert(userPage);
		j.setObj(pwd1);
		j.setMsg(Constant.ADD_SUCCESS);
		j.setSuccess(true);
		// }
		return j;
	}

	@Override
	public AdminPage getUser(String id) throws Exception {
		Admin admin = adminDao.selectByPrimaryKey(id);
		if (admin == null) {
			return null;
		}
		AdminPage adminPage = new AdminPage();
		BeanUtils.copyProperties(admin, adminPage);
		//Example example = new Example(AdminRole.class);
		//example.createCriteria().andEqualTo("tadminId", id);
		AdminRoleExample example =new AdminRoleExample();
		example.createCriteria().andTadminIdEqualTo(id);
		List<AdminRoleKey> AdminRoleList = adminRoleDao.selectByExample(example);
		if (AdminRoleList != null && AdminRoleList.size() > 0) {
			String roleIds = "";
			String roleNames = "";
			boolean b = false;
			for (AdminRoleKey AdminRole : AdminRoleList) {
				Role role = roleDao.selectByPrimaryKey(AdminRole.getTroleId());
				if (role != null) {
					if (b) {
						roleIds += ",";
						roleNames += ",";
					} else {
						b = true;
					}
					roleIds += role.getId();
					roleNames += role.getName();
				}
			}
			adminPage.setRoleIds(roleIds);
			adminPage.setRoleNames(roleNames);
		}

		return adminPage;
	}

	@Override
	public Json edit(AdminPage adminPage, SessionInfo sessionInfo) throws Exception {
		Json j = new Json();
		if (adminPage.getId() != null && adminPage.getId().equals(Constant.USER_ID_0)) {
			j.setSuccess(false);
			j.setMsg("修改失败，该角色为超管用户，不能修改！");
			return j;
		}
		Admin admin = adminDao.selectByPrimaryKey(adminPage.getId());
		if (admin == null) {
			j.setSuccess(false);
			j.setMsg(Constant.NO_MESSAGE);
		} else {

			if (!admin.getName().equals(adminPage.getName())) {
				//Example example = new Example(Admin.class);
				//example.createCriteria().andEqualTo("name", adminPage.getName());
				AdminExample example = new AdminExample();
				example.createCriteria().andNameEqualTo(adminPage.getName());
				List<Admin> userList = adminDao.selectByExample(example);
				if (userList != null && userList.size() > 0) {
					j.setMsg("用户名已存在！");
					j.setSuccess(false);
					return j;
				}
			}
			if (admin.getEmail() != null && !"".equals(admin.getEmail())) {
				if (!admin.getEmail().equals(adminPage.getEmail())) {
					//Example example = new Example(Admin.class);
					//example.createCriteria().andEqualTo("email", adminPage.getEmail());
					AdminExample example1 = new AdminExample();
					example1.createCriteria().andEmailEqualTo(adminPage.getEmail());
					List<Admin> userList = adminDao.selectByExample(example1);
					if (userList != null && userList.size() > 0) {
						j.setMsg("用户邮箱已存在！");
						j.setSuccess(false);
						return j;
					}
				}
			}
			if (admin.getPhone() != null && !"".equals(admin.getPhone())) {
				if (!admin.getPhone().equals(adminPage.getPhone())) {
					//Example example = new Example(Admin.class);
					//example.createCriteria().andEqualTo("phone", adminPage.getPhone());
					AdminExample example2 =new AdminExample();
					example2.createCriteria().andPhoneEqualTo(adminPage.getPhone());
					List<Admin> userList = adminDao.selectByExample(example2);
					if (userList != null && userList.size() > 0) {
						j.setMsg("用户手机号已存在！");
						j.setSuccess(false);
						return j;
					}
				}
			}
			/*
			if (adminPage.getProvinceId() == null) {
				adminPage.setProvinceId(Constant.RAN_LEVEL_TYPE_0);
			}
			if (adminPage.getCityId() == null) {
				adminPage.setCityId(Constant.RAN_LEVEL_TYPE_0);
			}
			*/
			// adminPage.setName(admin.getName());
			adminPage.setPwd(admin.getPwd());
			adminPage.setCreatedatetime(admin.getCreatedatetime());
			adminPage.setRemainingLogins(admin.getRemainingLogins());
			adminPage.setModifydatetime(new Date());
			adminDao.updateByPrimaryKey(adminPage);
			//Example exampleAdminRole = new Example(AdminRole.class);
			//exampleAdminRole.createCriteria().andEqualTo("tadminId", adminPage.getId());
			AdminRoleExample exampleAdminRole= new AdminRoleExample();
			exampleAdminRole.createCriteria().andTadminIdEqualTo(adminPage.getId());
			adminRoleDao.deleteByExample(exampleAdminRole);
			if (adminPage.getRoleIds() != null && !adminPage.getRoleIds().equals("")) {
				for (String roleId : adminPage.getRoleIds().split(",")) {
					AdminRoleKey adminRoleKey = new AdminRoleKey();
					//AdminRole.setTadminId(adminPage.getId());
					//AdminRole.setTroleId(roleId);
					adminRoleKey.setTadminId(adminPage.getId());
					adminRoleKey.setTroleId(roleId);
					adminRoleDao.insert(adminRoleKey);
				}
			}

			j.setSuccess(true);
			j.setMsg(Constant.EDIT_SUCCESS);
		}
		return j;
	}

	@Override
	public Json delete(String id, SessionInfo sessionInfo) throws Exception {
		Json j = new Json();
		Admin admin = adminDao.selectByPrimaryKey(id);
		if (admin != null) {
			if (id != null && id.equals(Constant.USER_ID_0)) {
				j.setSuccess(false);
				j.setMsg("该用户为超管用户，不能删除！");
				return j;
			}
			if (id != null && id.equals(sessionInfo.getId())) {
				j.setMsg("不可以删除自己！");
				j.setSuccess(false);
				return j;
			}
			//Example exampleAdminRole = new Example(AdminRole.class);
			//exampleAdminRole.createCriteria().andEqualTo("tadminId", id);
			AdminRoleExample exampleAdminRole=new AdminRoleExample();
			exampleAdminRole.createCriteria().andTadminIdEqualTo(id);
			adminRoleDao.deleteByExample(exampleAdminRole);
			adminDao.deleteByPrimaryKey(id);
			//adminDao.delete(admin);
			j.setSuccess(true);
			j.setMsg(Constant.DELETE_SUCCESS);
			j.setObj(admin);
		} else {
			j.setSuccess(false);
			j.setMsg(Constant.NO_MESSAGE);
		}
		return j;
	}

	@Override
	public Json resetPwd(String id) throws Exception {
		Json j = new Json();
		if (id != null && id.equals(Constant.USER_ID_0)) {
			j.setSuccess(false);
			j.setMsg("该用户为超管用户，不能重置密码！");
			return j;
		}
		Admin admin = adminDao.selectByPrimaryKey(id);
		if (admin != null) {
			String pwd = RandomUtil.generateWord();
			String pwdStr = PBKDF2.generateStorngPasswordHash256ByBCP(PBKDF2.getSalt().getBytes(),
					LaserUtils.sha256(pwd));
			/*
			 * if (admin.getEmail() == null || admin.getEmail().equals("")) {
			 * j.setSuccess(false); j.setMsg("用户没有邮箱信息!"); return j; }
			 */
			// 把密码和账户email给用户
			// SendMail s = new SendMail();
			// String flag =
			// s.SendPwdmail("<pre>您好：<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;" +
			// "管理员为您重置了密码," + "密码为&nbsp;" + pwd +
			// "&nbsp;&nbsp;&nbsp;&nbsp;【乒乓在沃管理平台】" + "</pre>",
			// admin.getEmail());
			// Log.info("[密码重置通知]：" + flag);
			/*
			 * if (!flag.equals("success")) { j.setSuccess(false);
			 * j.setMsg(flag); } else {
			 */
			admin.setPwd(pwdStr);
			admin.setModifydatetime(new Date());
			adminDao.updateByPrimaryKey(admin);
			j.setMsg("重置密码成功,密码:" + pwd);
			j.setObj(null);
			j.setSuccess(true);
			// }
		} else {
			j.setSuccess(false);
			j.setMsg(Constant.NO_MESSAGE);
		}
		return j;
	}

	@Override
	public Json editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) throws Exception {
		Json j = new Json();
		if (sessionInfo != null) {
			Admin admin = adminDao.selectByPrimaryKey(sessionInfo.getId());
			if (admin != null) {
				String random = admin.getPwd().split(":")[0];
				//LaserUtil.hexString2Ba(random)
				String oldPwdStr = PBKDF2.generateStorngPasswordHash256ByBCP(new byte[]{1,2,3,4}, oldPwd);
				if (admin.getPwd().equalsIgnoreCase(oldPwdStr)) {// 说明原密码输入正确
					String pwdStr = PBKDF2.generateStorngPasswordHash256ByBCP(PBKDF2.getSalt().getBytes(), pwd);
					admin.setPwd(pwdStr);
					admin.setModifydatetime(new Date());
					adminDao.updateByPrimaryKey(admin);
					j.setMsg("修改密码成功，下次登录生效！");
					j.setSuccess(true);
				} else {
					j.setSuccess(false);
					j.setMsg("原密码错误！");
				}
			} else {
				j.setSuccess(false);
				j.setMsg("修改失败，该管理员信息不存在，请刷新页面！");
			}
		} else {
			j.setMsg("登录超时，请重新登录！");
			j.setSuccess(false);
		}
		return j;
	}

	@Override
	public void userUnlock() throws Exception {
		//List<Admin> list = adminDao.selectAll();
		AdminExample adminExample=new AdminExample();
		List<Admin> list=adminDao.selectByExample(adminExample);
		int REMAINING_LOGINS = Constant.REMAINING_LOGINS;
		SystemConfig systemconfig = systemConfigDao.selectByPrimaryKey(Constant.REMAINING_LOGINS_STR);
		if (systemconfig != null) {
			REMAINING_LOGINS = Short.parseShort(systemconfig.getConfContext());
		}
		for (Admin admin : list) {
			admin.setLockSymbol(Constant.UNLOCKUSER);
			admin.setRemainingLogins(REMAINING_LOGINS);
			adminDao.updateByPrimaryKey(admin);
		}
	}

	@Override
	public List<AdminPage> getList() throws Exception {
		AdminExample adminExample =new AdminExample();
		adminExample.setOrderByClause("PROVINCE_ID desc,CITY_ID desc");
		//Example example = new Example(Admin.class);
		//example.setOrderByClause("PROVINCE_ID desc,CITY_ID desc");
		List<Admin> adminPages = adminDao.selectByExample(adminExample);
		List<AdminPage> list = new ArrayList<>();
		for (int i = 0, b = adminPages.size(); i < b; i++) {
			Admin admin = adminPages.get(i);
			AdminPage adminPage = new AdminPage();
			BeanUtils.copyProperties(admin, adminPage);
			if (adminPage.getLevelType() == Constant.ADMIN_LEVEL_TYPE_0) {
				adminPage.setLevelName("超级管理员");
			} else if (adminPage.getLevelType() == Constant.ADMIN_LEVEL_TYPE_1) {
				adminPage.setLevelName("普通用户");
			} else {
				adminPage.setLevelName("未知");
			}
			/*
			if (adminPage.getProvinceId() != null && adminPage.getProvinceId() != 0) {
				Province province = provinceMapper.selectByPrimaryKey(adminPage.getProvinceId());
				if (province != null) {
					adminPage.setProvinceName(province.getProvinceName());
				} else {
					adminPage.setProvinceName("未知");

				}
			}
			if (adminPage.getCityId() != null && adminPage.getCityId() != 0) {
				LocalCity cLocalCity = localCityMapper.selectByPrimaryKey(adminPage.getCityId());
				if (cLocalCity != null) {
					adminPage.setCityName(cLocalCity.getCityName());
				} else {
					adminPage.setCityName("未知");

				}
			}
			if (adminPage.getContestId() != null && !"0".equals(adminPage.getContestId())) {
				Contest contest = contestMapper.selectByPrimaryKey(adminPage.getContestId());
				if (contest != null) {
					adminPage.setContestName(contest.getContestName());
				} else {
					adminPage.setContestName("未知");

				}
			}
			*/
			list.add(adminPage);

		}

		return list;
	}
}
