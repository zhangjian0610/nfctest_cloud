/*
 * 文件名：LoginServiceImpl.java 版权：Copyright by http://www.bjleisen.com/ 描述： 修改人：zhangziqi
 * 修改时间：2016年11月11日 跟踪单号： 修改单号： 修改内容：
 */

package com.bupt.cardtest.service.impl;

import com.bupt.cardtest.dao.ResourceMapper;
import com.bupt.cardtest.dao.RoleMapper;
import com.bupt.cardtest.model.bean.*;
import com.bupt.cardtest.model.pageModel.Json;
import com.bupt.cardtest.model.pageModel.SessionInfo;
import com.bupt.cardtest.service.LoginServiceI;

import com.bupt.cardtest.util.Constant;
import com.bupt.cardtest.util.LaserUtils;
import com.bupt.cardtest.util.PBKDF2;



import com.bupt.cardtest.model.pagebean.AdminPage;
import com.bupt.cardtest.dao.AdminMapper;


import com.bupt.cardtest.dao.SystemConfigMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service()
public class LoginServiceImpl implements LoginServiceI {


	@Autowired
	private AdminMapper adminDao;

	@Autowired
	private RoleMapper roleDao;

	@Autowired
	private ResourceMapper resourceDao;

	@Autowired
	private SystemConfigMapper systemConfigDao;

	@Override
	public Json login(AdminPage adminPage, HttpServletRequest request) throws Exception {
		Json json = new Json();
		HttpSession session = request.getSession();
		if (session == null) {
			json.setMsg("登录时间过期，请重新登录。");
			json.setSuccess(false);
			return json;
		}
		// 查询用户
		//Example example = new Example(Admin.class);
		//example.createCriteria().andEqualTo("name", adminPage.getName());
		//List<Admin> userList = adminDao.selectByExample(example);
		AdminExample adminExample=new AdminExample();
		adminExample.createCriteria().andNameEqualTo(adminPage.getName());
		List<Admin> userList=adminDao.selectByExample(adminExample);

		Admin admin = userList == null || userList.size() < 1 ? null : userList.get(0);
		if (admin != null) {
			// 判断密码是否一致
			String random = admin.getPwd().split(":")[0];
			String pwd = PBKDF2.generateStorngPasswordHash256ByBCP(LaserUtils.hexStringToBinarys(random),
					adminPage.getPwd());
			if (admin.getPwd().equals(pwd)) {
				// 判断用户有效期
				if (null != admin.getValiddatetime() && admin.getValiddatetime().before(new Date())) {
					json.setMsg("您的密码超过有效期，请联系管理员。");
					json.setSuccess(false);
					return json;
				}
				// 除了系统管理员外，检查普通管理员账户是否锁定
				if (!admin.getId().equals(Constant.USER_ID_0)) {
					// 用户状态
					int lockSymbol = admin.getLockSymbol();
					// 用户锁定
					if (lockSymbol == Constant.Int_Boolean_Result.Yes.getValue()) {
						json.setMsg("您的账户已锁定，请联系管理员或明天再尝试登录。");
						json.setSuccess(false);
						return json;
					} else {
						int short_remaining_logins = Constant.REMAINING_LOGINS;
						SystemConfig systemconfig = systemConfigDao.selectByPrimaryKey(Constant.REMAINING_LOGINS_STR);
						if (systemconfig != null) {
							try {
								short_remaining_logins = Integer.parseInt(systemconfig.getConfContext());
							} catch (Exception e) {
								//Log.error("从系统配置表中，获取用户登录剩余次数[" + systemconfig.getConfContext() + "]转换异常", e);
							}
						}
						if (short_remaining_logins != admin.getRemainingLogins()) {
							admin.setRemainingLogins(short_remaining_logins);
							int int_result = adminDao.updateByPrimaryKeySelective(admin);
							if (int_result == 0) {
								//Log.error("修改用户表失败", new Exception("恢复用户剩余登录次数"));
							}
						}
					}
				}
				Map<String, String> map_url_name = new HashMap<String, String>();
				// 查询用户对应的角色
				List<Role> roles = roleDao.getListRoleByUserId(admin.getId());
				if (!roles.isEmpty()) {
					StringBuffer sb = new StringBuffer();
					for (Role role : roles) {
						sb.append(role.getId()).append(",");
					}
					String[] strs_role = sb.toString().split(",");
					// 根据用户的角色id，去查询对应的权限
					List<Resource> Resources = resourceDao.getResourcesByRoleId(strs_role);
					if (!Resources.isEmpty()) {
						for (Resource resource : Resources) {
							map_url_name.put(resource.getUrl(), resource.getName());
						}
					}
				}

				SessionInfo sessionInfo = new SessionInfo();

				/**
				 * 三级管理员 start
				 */
				//sessionInfo.setProvince(admin.getProvinceId());
				//sessionInfo.setCityId(admin.getCityId());
				sessionInfo.setLevelType(admin.getLevelType());
				//sessionInfo.setContestId(admin.getContestId());
				sessionInfo.setId(admin.getId());
				sessionInfo.setName(admin.getName());
				sessionInfo.setResourceMap(map_url_name);
				sessionInfo.setLogDate(new Date());
				json.setObj(sessionInfo);
				json.setSuccess(true);
				json.setMsg("登录成功。");
				/**
				 * 验证码 start
				 */
				session.setAttribute("isCode", Constant.IS__NO_CODE);
				session.setAttribute("codeStrCount", Constant.IS__NO_CODE_COUNT);
				/**
				 * 验证码 end
				 */
			} else {
				if (admin.getId().equals(Constant.USER_ID_0)) {
					/**
					 * 验证码 start
					 */
					String codeStrCount = session.getAttribute("codeStrCount") == null ? "0"
							: session.getAttribute("codeStrCount").toString();
					Integer count = Integer.parseInt(codeStrCount);
					if (count >= Constant.IS_YES_CODE_COUNT) {
						session.setAttribute("isCode", Constant.IS_YES_CODE);
					}
					count++;
					session.setAttribute("codeStrCount", count);
					/**
					 * 验证码 end
					 */
					json.setMsg("您输入的用户名或密码不正确，请重新输入。");
					json.setSuccess(false);
				} else {
					int short_remaininglogins = admin.getRemainingLogins() - 1;
					admin.setRemainingLogins(short_remaininglogins);
					// 用户状态
					int lockSymbol = admin.getLockSymbol();
					// 用户锁定
					if (lockSymbol == Constant.Int_Boolean_Result.Yes.getValue()) {
						json.setMsg("您的账户已锁定，请联系管理员或明天再尝试登录。");
						json.setSuccess(false);
						return json;
					}
					if (short_remaininglogins == 0)// 需要锁定帐户
					{
						admin.setLockSymbol(Constant.Int_Boolean_Result.Yes.getValue());
						int int_result = adminDao.updateByPrimaryKeySelective(admin);
						if (int_result == 0) {
							//Log.error("修改用户表失败", new Exception("锁定用户,剩余次数为0"));
						}
						json.setMsg("您的账户已锁定，请联系管理员或明天再登录");
						json.setSuccess(false);
					} else {
						int int_result = adminDao.updateByPrimaryKeySelective(admin);
						if (int_result == 0) {
							//Log.error("修改用户表失败", new Exception("锁定用户,剩余次数为0"));
						}
						/**
						 * 验证码 start
						 */
						String codeStrCount = session.getAttribute("codeStrCount") == null ? "0"
								: session.getAttribute("codeStrCount").toString();
						Integer count = Integer.parseInt(codeStrCount);
						if (count >= Constant.IS_YES_CODE_COUNT) {
							session.setAttribute("isCode", Constant.IS_YES_CODE);
						}
						count++;
						session.setAttribute("codeStrCount", count);
						/**
						 * 验证码 end
						 */
						json.setMsg("您输入的用户名或密码不正确,您还有" + short_remaininglogins + "次尝试机会。");
						json.setSuccess(false);
					}
				}
			}
		} else {
			String codeStrCount = session.getAttribute("codeStrCount") == null ? "0"
					: session.getAttribute("codeStrCount").toString();
			Integer count = Integer.parseInt(codeStrCount);
			if (count >= Constant.IS_YES_CODE_COUNT) {
				session.setAttribute("isCode", Constant.IS_YES_CODE);
			}
			count++;
			session.setAttribute("codeStrCount", count);
			json.setMsg("您输入的用户名或密码不正确，请重新输入。");
			json.setSuccess(false);
		}
		return json;
	}

}
