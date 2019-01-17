package com.bupt.cardtest.model.pagebean;

import java.util.Date;
import com.bupt.cardtest.model.bean.Admin;


public class AdminPage extends Admin {
	/**
	 * 验证码
	 */

	private String verifycode;

	private String roleIds;

	private String roleNames;

	private Date modifydatetimeStart;

	private Date modifydatetimeEnd;

	//private String merchantName;

	//private String provinceName;
	//private String cityName;
	//private String contestName;
	private String levelName;

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public Date getModifydatetimeStart() {
		return modifydatetimeStart;
	}

	public void setModifydatetimeStart(Date modifydatetimeStart) {
		this.modifydatetimeStart = modifydatetimeStart;
	}

	public Date getModifydatetimeEnd() {
		return modifydatetimeEnd;
	}

	public void setModifydatetimeEnd(Date modifydatetimeEnd) {
		this.modifydatetimeEnd = modifydatetimeEnd;
	}


	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
