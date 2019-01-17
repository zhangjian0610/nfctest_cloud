package com.bupt.cardtest.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Constant {
	public static final String LOG_OPERTYPE = "aop_opertype";
	public static final String LOG_START_TIME = "aop_startOperTime";
	public static final String LOG_ID = "aop_logid";
	/**
	 * 全局变量缓存登陆信息
	 */
	public static ConcurrentMap<String, Object> HASH_MAP = new ConcurrentHashMap<String, Object>();
	public static final String SESSION_NAME = "sessionInfo";

	/**
	 * 
	 */
	public enum Int_Boolean_Result {
		Yes(1), No(0);
		// 构造方法
		private int Value;

		private Int_Boolean_Result(int opertype) {
			this.Value = opertype;
		}

		public int getValue() {
			return Value;
		}

		public void setValue(int value) {
			Value = value;
		}
	};

	public static final String FILE_PATH = "/Users/zjhangjian/uploadFIle/";//上传文件存在服务器下的路径

	public static final String ROLE_ID_0 = "0";// 超管角色id

	public static final String USER_ID_0 = "0";// 超管用户id

	public static final String ADD_SUCCESS = "添加成功!";

	public static final String ADD_FAIL = "添加失败!";

	public static final String EDIT_SUCCESS = "修改成功!";

	public static final String EDIT_FAIL = "修改失败!";

	public static final String DELETE_SUCCESS = "删除成功!";

	public static final String DELETE_FAIL = "删除失败!";

	public static final String NO_MESSAGE = "该信息不存在，请刷新页面!";

	public static final String IS_MODIFIED = "该信息已被他人修改，请刷新页面重新修改!";

	public static final String MESSAGE_EXIST = "该信息已存在!";

	public static final String CONTEST_PROVICEN_NO_EXIST = "省市信息不存在!";

	public static final String CONTEST_CITY_NO_EXIST = "地方市信息不存在!";

	public static final String GET_ONLINE_LIST_LEAVEL = "权限不足!";

	public static final String MESSAGE_TO_EMALI = "邮件发送成功!";

	//public static final String MESSAGE_TO_REGISTARATION_EMALI = "平台工作人员及时沟通确认，进行线下保险购买!";

	//public static final String MESSAGE_SCHEDULE_ISNULL = "所选赛事下，该省市已经存在赛程!";
	//public static final String MESSAGE_DIVISION_ISNULL = "所选赛事下，该省地方市已经存在赛区!";
	public static final String NO_USERINFO_MESSAGE = "用户信息不存在!";
	//public static final String NO_USERINFO_PHONE = "短信发送失败，用户无接收方式!";
	//public static final String USER_INFO_REGIS = "该用户已经报名，不能直接删除用户信息!";

	/**
	 * 范围权限
	 */
	public static Integer RAN_LEVEL_TYPE_0 = 0; // 全国管理员

	/**
	 * 默认登录剩余次数
	 */

	public static final String REMAINING_LOGINS_STR = "REMAINING_LOGINS";
	public static final Integer REMAINING_LOGINS = 5;

	// 用户锁定状态
	public static final Integer LOCKUSER = 1; // 锁定

	public static final Integer UNLOCKUSER = 0;// 未锁定

	/**
	 * email通知超管:1,开启；0：关闭
	 */
	public static final int MAIL_NOTIFICATION_ON = 1;

	public static final int MAIL_NOTIFICATION_OFF = 0;

	/**
	 * 是否需要验证码
	 */
	public static Integer IS_YES_CODE = 1;
	public static Integer IS__NO_CODE = 0;
	/**
	 * 需要验证码条件
	 */
	public static Integer IS_YES_CODE_COUNT = 2;
	public static Integer IS__NO_CODE_COUNT = 0;

	public static Byte PRIVILEGES_1 = 1;

	public static Byte PRIVILEGES_2 = 2;

	public static Byte PRIVILEGES_3 = 3;

	public static final String KEYVALUE = "LeiSen@2015LLLLL";

	/**
	 * 管理员权限
	 */
	public static Integer ADMIN_LEVEL_TYPE_0 = 0; // 超级管理员
	public static Integer ADMIN_LEVEL_TYPE_1 = 1; // 普通管理员
	//public static Integer ADMIN_LEVEL_TYPE_2 = 2; // 赛区管理员
	/**
	 * 用户信息
	 */
	/**
	 * 身份证
	 */
	//public static Integer USER_CARD_TYPE_1 = 1;

	//public static String USER_CARD_TYPE_DESC_1 = "身份证";
	/**
	 * 其他
	 */
	public static Integer USER_CARD_TYPE_2 = 2;

	public static String USER_CARD_TYPE_DESC_2 = "其他";

	/**
	 * 性别
	 */
	/**
	 * 女
	 */
	//public static Integer USER_SEX_0 = 0;

	//public static String USER_SEX_DESC_0 = "女";
	/**
	 * 男
	 */
	//public static Integer USER_SEX_1 = 1;

	//public static String USER_SEX_DESC_1 = "男";

	/**
	 * 小组男
	 */
	//public static Integer GROUP_USER_SEX_2 = 2;

	//public static String GROUP_USER_SEX_DESC_2 = "男";

	/**
	 * 小组女
	 */
	//public static Integer GROUP_USER_SEX_3 = 3;

	//public static String GROUP_USER_SEX_DESC_3 = "女";

	/**
	 * 小组类型
	 */
	//public static Integer GROUP_TYPE_1 = 1;

	//public static Integer GROUP_TYPE_2 = 2;

	/**
	 * 报名类型
	 */
	/**
	 * 本人报名
	 */
	//public static Integer REG_TYPE_1 = 1;
	/**
	 * 孩子报名
	 */
	//public static Integer REG_TYPE_2 = 2;

	/**
	 * 与孩子关系类型
	 */
	/**
	 * 父母
	 */
	//public static Integer CHILDREN_TYPE_1 = 1;

	//public static String CHILDREN_TYPE_DESC_1 = "父母";
	/**
	 * 亲戚
	 */
	//public static Integer CHILDREN_TYPE_2 = 2;

	//public static String CHILDREN_TYPE_DESC_2 = "亲戚";
	/**
	 * 其他
	 */
	//public static Integer CHILDREN_TYPE_3 = 3;

	//public static String CHILDREN_TYPE_DESC_3 = "其他";

	/**
	 * 用户业务类型
	 */
	/**
	 * 手机号
	 */
	//public static Integer USER_TYPE_1 = 1;
	/**
	 * 固话
	 */
	//public static Integer USER_TYPE_2 = 2;
	/**
	 * 宽带账号
	 */
	//public static Integer USER_TYPE_3 = 3;
	/**
	 * LAN账号
	 */
	//public static Integer USER_TYPE_4 = 4;
	/**
	 * 代报名
	 */
	//public static Integer USER_TYPE_5 = 5;
	/**
	 * 用户状态
	 */

	/**
	 * 待审核
	 */
	//public static Integer USER_STATUS_1 = 1;

	//public static String USER_STATUS_DESC_1 = "待审核";
	/**
	 * 审核通过
	 */
	//public static Integer USER_STATUS_2 = 2;

	//public static String USER_STATUS_DESC_2 = "审核通过";
	/**
	 * 审核未通过
	 */
	//public static Integer USER_STATUS_3 = 3;

	//public static String USER_STATUS_DESC_3 = "审核未通过";
	public static String TO_EAMIL = "EAMIL_ADDRESS";

}
