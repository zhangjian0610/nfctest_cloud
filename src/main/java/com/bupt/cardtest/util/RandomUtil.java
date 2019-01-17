package com.bupt.cardtest.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生成随机密码工具类
 * 
 * @author
 * 
 */
public class RandomUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	public static String generateWord() {
		// 密码
		StringBuffer pword = new StringBuffer();
		SecureRandom random = new SecureRandom();

		// 随机生成三位小写字母
		String lowerStr = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < 3; i++) {
			pword.append(lowerStr.charAt(random.nextInt(lowerStr.length())));
		}

		// 随机生成三位大写字母
		String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 3; i++) {
			pword.append(upperStr.charAt(random.nextInt(upperStr.length())));
		}

		// 随机生成两位数字
		String number = "0123456789";
		for (int i = 0; i < 2; i++) {
			pword.append(number.charAt(random.nextInt(number.length())));
		}

		/*
		 * // 随机生成两位特殊字符 String specialStr = "@#$%^*+|/=~`!?,.:;-_(){}[]/"; for
		 * (int i = 0; i < 2; i++) {
		 * pword.append(specialStr.charAt(random.nextInt(specialStr.length())));
		 * }
		 */
		return pword.toString();
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		while (true) {
			String paw = generateWord();
			if (map.containsKey(paw)) {
				System.out.println(paw);
				System.out.println(map.get(paw).toString());
			}
			map.put(paw, i);
			i++;
		}
	}

	// 生成随机数字和字母,
	public static String getStringRandom(int length) {

		StringBuffer val = new StringBuffer();
		SecureRandom random = new SecureRandom();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val.append((char) (random.nextInt(26) + temp));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val.append(String.valueOf(random.nextInt(10)));
			}
		}
		return val.toString();
	}

}
