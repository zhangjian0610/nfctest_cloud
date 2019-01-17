/**
 * 文件名：LaserUtils.java 版权：Copyright by www.bjleisen.com 描述： 修改人：zhangziqi 修改时间：2016年2月16日 修改内容：
 */

package com.bupt.cardtest.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.*;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

public class LaserUtils {

	public static void main(String[] args) {
		// System.out.println(stringToAscii("123456abcdefABCDEF"));
		// System.out.println(asciiToString("313233343536616263646566414243444546"));
		// System.out.println(Arrays.toString(hexStringToBinarys("f")));
		// System.out.println(binarysToHexString(hexStringToBinarys("f")));
		// System.out.println(Arrays.toString(hexStringToBinarys("50")));

		try {
			Map<String, Object> priAndPulKeys = getPriAndPulKeys();
			System.out.println("公钥:" + Base64encode(((RSAPublicKey) priAndPulKeys.get("publickey")).getEncoded()));
			System.out.println("私钥:" + Base64encode(((RSAPrivateKey) priAndPulKeys.get("privatekey")).getEncoded()));
			// KeyPairGenerator keypairgen =
			// KeyPairGenerator.getInstance("RSA");
			// keypairgen.initialize(2048);
			// KeyPair keypair = keypairgen.generateKeyPair();
			// RSAPublicKey public1 = (RSAPublicKey)keypair.getPublic();
			// RSAPrivateKey private1 = (RSAPrivateKey)keypair.getPrivate();
			// System.out.println(binarysToHexString(public1.getEncoded()));
			// System.out.println(binarysToHexString(public1.getEncoded()).length());

			String base64 = Base64encode("123456");
			System.out.println(base64);
			System.out.println(Base64decode(base64));
			System.out.println(md5("123456"));
			System.out.println(sha1("123456"));
			System.out.println(sha256("123456"));

			byte[] des_key = "12345678".getBytes();
			System.out.println(binarysToHexString(des_key));
			byte[] tripleDes_key = "1234567890abcdef12345678".getBytes();
			byte[] des_data_no = "01234567".getBytes();
			System.out.println(binarysToHexString(des_data_no));
			byte[] des_data = "012345".getBytes();
			byte[] cbc_iv = "00000000".getBytes();
			byte[] aes_key = "1234567890abcdef".getBytes();
			byte[] aes_data = "1234567887".getBytes();
			byte[] aes_data_no = "1234567887654321".getBytes();
			byte[] aes_cbc_iv = "0000000000000000".getBytes();
			byte[] encrypt_DES_ECB_NoPadding = encrypt_DES_ECB_NoPadding(des_key, des_data_no);
			byte[] encrypt_DES_CBC_NoPadding = encrypt_DES_CBC_NoPadding(des_key, des_data_no, cbc_iv);
			byte[] encrypt_DES_ECB_PKCS5Padding = encrypt_DES_ECB_PKCS5Padding(des_key, des_data);
			byte[] encrypt_DES_CBC_PKCS5Padding = encrypt_DES_CBC_PKCS5Padding(des_key, des_data, cbc_iv);
			System.out.println("DES_ECB_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_DES_ECB_NoPadding));
			System.out.println("DES_ECB_NoPadding加密结果:" + binarysToHexString(encrypt_DES_ECB_NoPadding));
			System.out.println("DES_CBC_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_DES_CBC_NoPadding));
			System.out.println("DES_ECB_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_DES_ECB_PKCS5Padding));
			System.out.println("DES_CBC_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_DES_CBC_PKCS5Padding));
			byte[] decrypt_DES_ECB_NoPadding = decrypt_DES_ECB_NoPadding(des_key, encrypt_DES_ECB_NoPadding);
			byte[] decrypt_DES_CBC_NoPadding = decrypt_DES_CBC_NoPadding(des_key, encrypt_DES_CBC_NoPadding, cbc_iv);
			byte[] decrypt_DES_ECB_PKCS5Padding = decrypt_DES_ECB_PKCS5Padding(des_key, encrypt_DES_ECB_PKCS5Padding);
			byte[] decrypt_DES_CBC_PKCS5Padding = decrypt_DES_CBC_PKCS5Padding(des_key, encrypt_DES_CBC_PKCS5Padding,
					cbc_iv);
			System.out.println("DES_ECB_NoPadding解密结果:" + new String(decrypt_DES_ECB_NoPadding));
			System.out.println("DES_CBC_NoPadding解密结果:" + new String(decrypt_DES_CBC_NoPadding));
			System.out.println("DES_ECB_PKCS5Padding解密结果:" + new String(decrypt_DES_ECB_PKCS5Padding));
			System.out.println("DES_CBC_PKCS5Padding解密结果:" + new String(decrypt_DES_CBC_PKCS5Padding));
			byte[] encrypt_TripleDES_ECB_NoPadding = encrypt_TripleDES_ECB_NoPadding(tripleDes_key, des_data_no);
			byte[] encrypt_TripleDES_CBC_NoPadding = encrypt_TripleDES_CBC_NoPadding(tripleDes_key, des_data_no,
					cbc_iv);
			byte[] encrypt_TripleDES_ECB_PKCS5Padding = encrypt_TripleDES_ECB_PKCS5Padding(tripleDes_key, des_data);
			byte[] encrypt_TripleDES_CBC_PKCS5Padding = encrypt_TripleDES_CBC_PKCS5Padding(tripleDes_key, des_data,
					cbc_iv);
			System.out.println(
					"TripleDES_ECB_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_TripleDES_ECB_NoPadding));
			System.out.println(
					"TripleDES_CBC_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_TripleDES_CBC_NoPadding));
			System.out.println(
					"TripleDES_ECB_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_TripleDES_ECB_PKCS5Padding));
			System.out.println(
					"TripleDES_CBC_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_TripleDES_CBC_PKCS5Padding));
			byte[] decrypt_TripleDES_ECB_NoPadding = decrypt_TripleDES_ECB_NoPadding(tripleDes_key,
					encrypt_TripleDES_ECB_NoPadding);
			byte[] decrypt_TripleDES_CBC_NoPadding = decrypt_TripleDES_CBC_NoPadding(tripleDes_key,
					encrypt_TripleDES_CBC_NoPadding, cbc_iv);
			byte[] decrypt_TripleDES_ECB_PKCS5Padding = decrypt_TripleDES_ECB_PKCS5Padding(tripleDes_key,
					encrypt_TripleDES_ECB_PKCS5Padding);
			byte[] decrypt_TripleDES_CBC_PKCS5Padding = decrypt_TripleDES_CBC_PKCS5Padding(tripleDes_key,
					encrypt_TripleDES_CBC_PKCS5Padding, cbc_iv);
			System.out.println("TripleDES_ECB_NoPadding解密结果:" + new String(decrypt_TripleDES_ECB_NoPadding));
			System.out.println("TripleDES_CBC_NoPadding解密结果:" + new String(decrypt_TripleDES_CBC_NoPadding));
			System.out.println("TripleDES_ECB_PKCS5Padding解密结果:" + new String(decrypt_TripleDES_ECB_PKCS5Padding));
			System.out.println("TripleDES_CBC_PKCS5Padding解密结果:" + new String(decrypt_TripleDES_CBC_PKCS5Padding));

			byte[] encrypt_AES_ECB_NoPadding = encrypt_AES_ECB_NoPadding(aes_key, aes_data_no);
			byte[] encrypt_AES_CBC_NoPadding = encrypt_AES_CBC_NoPadding(aes_key, aes_data_no, aes_cbc_iv);
			byte[] encrypt_AES_ECB_PKCS5Padding = encrypt_AES_ECB_PKCS5Padding(aes_key, aes_data);
			byte[] encrypt_AES_CBC_PKCS5Padding = encrypt_AES_CBC_PKCS5Padding(aes_key, aes_data, aes_cbc_iv);
			System.out.println("AES_ECB_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_AES_ECB_NoPadding));
			System.out.println("AES_CBC_NoPadding加密结果:" + Base64.encodeBase64String(encrypt_AES_CBC_NoPadding));
			System.out.println("AES_ECB_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_AES_ECB_PKCS5Padding));
			System.out.println("AES_CBC_PKCS5Padding加密结果:" + Base64.encodeBase64String(encrypt_AES_CBC_PKCS5Padding));

			byte[] decrypt_AES_ECB_NoPadding = decrypt_AES_ECB_NoPadding(aes_key, encrypt_AES_ECB_NoPadding);
			byte[] decrypt_AES_CBC_NoPadding = decrypt_AES_CBC_NoPadding(aes_key, encrypt_AES_CBC_NoPadding,
					aes_cbc_iv);
			byte[] decrypt_AES_ECB_PKCS5Padding = decrypt_AES_ECB_PKCS5Padding(aes_key, encrypt_AES_ECB_PKCS5Padding);
			byte[] decrypt_AES_CBC_PKCS5Padding = decrypt_AES_CBC_PKCS5Padding(aes_key, encrypt_AES_CBC_PKCS5Padding,
					aes_cbc_iv);
			System.out.println("AES_ECB_NoPadding解密结果:" + new String(decrypt_AES_ECB_NoPadding));
			System.out.println("AES_CBC_NoPadding解密结果:" + new String(decrypt_AES_CBC_NoPadding));
			System.out.println("AES_ECB_PKCS5Padding解密结果:" + new String(decrypt_AES_ECB_PKCS5Padding));
			System.out.println("AES_CBC_PKCS5Padding解密结果:" + new String(decrypt_AES_CBC_PKCS5Padding));

			// Map<String, Object> priAndPulKeys = getPriAndPulKeys();

			// RSAPublicKey publickey =
			// (RSAPublicKey)priAndPulKeys.get("publickey");
			// RSAPrivateKey privatekey =
			// (RSAPrivateKey)priAndPulKeys.get("privatekey");

			// String m = publickey.getModulus().toString();
			// String e = publickey.getPublicExponent().toString();
			// RSAPublicKey publicKey2 = getPublicKey(m, e);
			// String m1= privatekey.getModulus().toString();
			// String e1= privatekey.getPrivateExponent().toString();
			// RSAPrivateKey privatekey2 = getPrivateKey(m1, e1);

			// RSAPublicKey publicKeyByBinary =
			// getPublicKeyByBinary(binarysToHexString(publickey.getEncoded()),true);
			// RSAPrivateKey privateKeyByBinary =
			// getPrivateKeyByBinary(binarysToHexString(privatekey.getEncoded()),
			// true);
			// RSAPublicKey publicKeyByBinary1 =
			// getPublicKeyByBinary(Base64.encodeBase64String(publickey.getEncoded()),false);
			// RSAPrivateKey privateKeyByBinary1 =
			// getPrivateKeyByBinary(Base64.encodeBase64String(privatekey.getEncoded()),
			// false);

			// String rsa_data =
			// "12345678901234567890123456789012345678901234567890"
			// + "12345678901234567890123456789012345678901234567890"
			// + "12345678901234567890123456789012345678901234567890"
			// + "12345678901234567890123456789012345678901234567890"
			// + "12345678901234567890123456789012345678901234567890"
			// + "abcedfghijabcedfghijabcedfghijabcedfghijabcedfghij";
			// byte[] encrypt_RSA_PublicKey =
			// encrypt_RSA_PublicKey(rsa_data.getBytes(), publickey);
			// byte[] encrypt_RSA_PublicKey1 =
			// encrypt_RSA_PublicKey(rsa_data.getBytes(), publicKey2);
			// byte[] encrypt_RSA_PublicKey2 =
			// encrypt_RSA_PublicKey(rsa_data.getBytes(), publicKeyByBinary);
			// byte[] encrypt_RSA_PublicKey3 =
			// encrypt_RSA_PublicKey(rsa_data.getBytes(), publicKeyByBinary1);
			// byte[] encrypt_RSA_PrivateKey =
			// encrypt_RSA_PrivateKey(rsa_data.getBytes(), privatekey);
			// byte[] encrypt_RSA_PrivateKey2 =
			// encrypt_RSA_PrivateKey(rsa_data.getBytes(), privatekey2);
			// byte[] encrypt_RSA_PrivateKey3 =
			// encrypt_RSA_PrivateKey(rsa_data.getBytes(), privateKeyByBinary);
			// byte[] encrypt_RSA_PrivateKey4 =
			// encrypt_RSA_PrivateKey(rsa_data.getBytes(), privateKeyByBinary1);

			// System.out.println("公钥加密"+binarysToHexString(encrypt_RSA_PublicKey));
			// System.out.println("公钥加密"+binarysToHexString(encrypt_RSA_PublicKey1));
			// System.out.println("公钥加密"+binarysToHexString(encrypt_RSA_PublicKey2));
			// System.out.println("公钥加密"+binarysToHexString(encrypt_RSA_PublicKey3));
			// System.out.println("私钥加密"+binarysToHexString(encrypt_RSA_PrivateKey));
			// System.out.println("私钥加密"+binarysToHexString(encrypt_RSA_PrivateKey2));
			// System.out.println("私钥加密"+binarysToHexString(encrypt_RSA_PrivateKey3));
			// System.out.println("私钥加密"+binarysToHexString(encrypt_RSA_PrivateKey4));

			// byte[] decrypt_RSA_PrivateKey =
			// decrypt_RSA_PrivateKey(encrypt_RSA_PublicKey, privatekey);
			// byte[] decrypt_RSA_PrivateKey1 =
			// decrypt_RSA_PrivateKey(encrypt_RSA_PublicKey1, privatekey2);
			// byte[] decrypt_RSA_PrivateKey2 =
			// decrypt_RSA_PrivateKey(encrypt_RSA_PublicKey2,
			// privateKeyByBinary);
			// byte[] decrypt_RSA_PrivateKey3 =
			// decrypt_RSA_PrivateKey(encrypt_RSA_PublicKey2,
			// privateKeyByBinary1);
			// byte[] decrypt_RSA_PublicKey =
			// decrypt_RSA_PublicKey(encrypt_RSA_PrivateKey, publickey);
			// byte[] decrypt_RSA_PublicKey2 =
			// decrypt_RSA_PublicKey(encrypt_RSA_PrivateKey2, publicKey2);
			// byte[] decrypt_RSA_PublicKey3 =
			// decrypt_RSA_PublicKey(encrypt_RSA_PrivateKey3,
			// publicKeyByBinary);
			// byte[] decrypt_RSA_PublicKey4 =
			// decrypt_RSA_PublicKey(encrypt_RSA_PrivateKey4,
			// publicKeyByBinary1);
			// System.out.println("私钥解密"+new String(decrypt_RSA_PrivateKey));
			// System.out.println("私钥解密"+new String(decrypt_RSA_PrivateKey1));
			// System.out.println("私钥解密"+new String(decrypt_RSA_PrivateKey2));
			// System.out.println("私钥解密"+new String(decrypt_RSA_PrivateKey3));
			// System.out.println("公钥解密"+new String(decrypt_RSA_PublicKey));
			// System.out.println("公钥解密"+new String(decrypt_RSA_PublicKey2));
			// System.out.println("公钥解密"+new String(decrypt_RSA_PublicKey3));
			// System.out.println("公钥解密"+new String(decrypt_RSA_PublicKey4));

			// byte[] signByMD5WithRSA = signByMD5WithRSA(rsa_data.getBytes(),
			// privatekey);
			// System.out.println(Base64.encodeBase64String(signByMD5WithRSA));
			// boolean verifySignByMD5WithRSA =
			// verifySignByMD5WithRSA(rsa_data.getBytes(), publickey,
			// signByMD5WithRSA);
			// System.out.println(verifySignByMD5WithRSA);
			// byte[] signBySHA1withRSA = signBySHA1withRSA(rsa_data.getBytes(),
			// privatekey2);
			// System.out.println(binarysToHexString(signBySHA1withRSA));
			// boolean verifySignBySHA1withRSA =
			// verifySignBySHA1withRSA(rsa_data.getBytes(), publicKey2,
			// signBySHA1withRSA);
			// System.out.println(verifySignBySHA1withRSA);

			// "DHPrivateKey"
			// "DHPublicKey"
			// A的密钥对
			Map<String, Object> keyMap1 = initKey();
			// B的密钥对是根据A的公钥生成
			Map<String, Object> keyMap2 = getKeyPair(((Key) keyMap1.get("DHPublicKey")).getEncoded());

			System.out.println("A的公钥：" + Base64.encodeBase64String(((Key) keyMap1.get("DHPublicKey")).getEncoded()));
			System.out.println();
			System.out.println("A的私钥：" + Base64.encodeBase64String(((Key) keyMap1.get("DHPrivateKey")).getEncoded()));
			System.out.println();
			System.out.println("B的公钥：" + Base64.encodeBase64String(((Key) keyMap2.get("DHPublicKey")).getEncoded()));
			System.out.println();
			System.out.println("B的私钥：" + Base64.encodeBase64String(((Key) keyMap2.get("DHPrivateKey")).getEncoded()));

			// A的公钥和B的私钥
			byte[] secretKey1 = getSecretKey(((Key) keyMap1.get("DHPublicKey")).getEncoded(),
					((Key) keyMap2.get("DHPrivateKey")).getEncoded());
			// B的公钥和A的私钥
			byte[] secretKey2 = getSecretKey(((Key) keyMap2.get("DHPublicKey")).getEncoded(),
					((Key) keyMap1.get("DHPrivateKey")).getEncoded());
			System.out.println();
			System.out.println(Base64.encodeBase64String(secretKey1));
			System.out.println(Base64.encodeBase64String(secretKey2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 用于建立十六进制字符的输出的小写字符数组
	 */
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * 用于建立十六进制字符的输出的大写字符数组
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * 16进制字符串(不区分大小写)转2进制字符串
	 * 
	 * @Description: "f" -> 00001111 "ff" -> "11111111"
	 * @Check parameters by the 【caller】
	 * @param hexStr
	 * @return
	 */
	public static String hexStringToBinaryString(String hexStr) throws Exception {
		if (hexStr == null || "".equals(hexStr)) {
			return "";
		}
		while (hexStr.length() % 2 != 0) {
			hexStr = "0" + hexStr;
		}
		String bString = "";
		String tmp;
		for (int i = 0; i < hexStr.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexStr.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	/**
	 * 2进制字符串转16进制字符串 （默认小写）
	 * 
	 * @Description: "11111111" -> "ff" "00001111" -> "0f"
	 * @Check parameters by the 【caller】
	 * @param binaryStr
	 * @return
	 */
	public static String binaryStringToHexString(String binaryStr) throws Exception {
		if (binaryStr == null || "".equals(binaryStr)) {
			return "";
		}
		while (binaryStr.length() % 8 != 0) {
			binaryStr = "0" + binaryStr;
		}

		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < binaryStr.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(binaryStr.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return tmp.toString();
	}

	/**
	 * 字符串转ASCII字符串
	 * 
	 * @Description: "123456abcdefABCDEF" ->
	 *               313233343536616263646566414243444546
	 * @Check parameters by the 【caller】
	 * @param value
	 * @return
	 */
	public static String stringToAscii(String value) throws Exception {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toString(chars[i], 16));
		}
		return sbu.toString();
	}

	/**
	 * ASCII字符串转字符串
	 * 
	 * @Description: "313233343536" -> "123456"
	 * @Check parameters by the 【caller】
	 * @param value
	 * @return
	 */
	public static String asciiToString(String value) throws Exception {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length() - 1; i += 2) {
			String output = value.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
		}
		return sb.toString();
	}

	/**
	 * 将字节数组转换为十六进制字符串(默认小写)
	 * 
	 * @Description: [1, 15, 16, -16] -> "010f10f0"
	 * @Check parameters by the 【caller】
	 * @param data
	 * @return
	 */
	public static String binarysToHexString(byte[] data) throws Exception {
		return binarysToHexString(data, true);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @Description: [1, 15, 16, -16],true -> "010f10f0" [1, 15, 16,
	 *               -16],false-> "010F10F0"
	 * @Check parameters by the 【caller】
	 * @param data
	 * @param toLowerCase
	 *            响应的十六进制大小写控制 true为小写false为大写
	 * @return
	 */
	public static String binarysToHexString(byte[] data, boolean toLowerCase) throws Exception {
		return new String(binarysToHexString(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER));
	}

	/**
	 * 将字节数组转换为字符数组
	 * 
	 * @Description: [1, 15, 16, -16] -> [0, 1, 0, f, 1, 0, f, 0]
	 * @Check parameters by the 【caller】
	 * @param data
	 * @param toDigits
	 *            用于控制输出的char[]
	 * @return
	 */
	public static char[] binarysToHexString(byte[] data, char[] toDigits) throws Exception {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	/**
	 * 将十六进制字符串转换为字节数组
	 * 
	 * @Description: "f11F" -> [-15, 31]
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param data
	 * @return
	 */
	public static byte[] hexStringToBinarys(String data) throws Exception {
		int len = data.length();
		while ((len & 0x01) != 0) {
			data = "0" + data;
			len = data.length();
		}
		byte[] out = new byte[len >> 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data.charAt(j), j) << 4;
			j++;
			f = f | toDigit(data.charAt(j), j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}

	/**
	 * 将十六进制字符转换成一个整数
	 * 
	 * @param ch
	 *            十六进制char
	 * @param index
	 *            十六进制字符在字符数组中的位置
	 * @return 一个整数
	 * @throws RuntimeException
	 *             当ch不是一个合法的十六进制字符时，抛出运行时异常
	 */
	private static int toDigit(char ch, int index) throws Exception {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
		}
		return digit;
	}

	/**
	 * @Description: des加密算法
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加/解密密钥，长度不可小于8
	 * @param data
	 *            待加/解密数据，如果不是8的整数倍，需要填充，建议pkcs5padding
	 * @param cipherMode
	 *            模式 [加密]：Cipher.ENCRYPT_MODE [解密]：Cipher.DECRYPT_MODE
	 * @param mode
	 *            "CBC" or "ECB"
	 * @param padding
	 *            "Nopadding" or "PKCS5Padding" or "ISO10126Padding"（不推荐使用）
	 * @param iv
	 *            初始向量 长度必须是8
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static byte[] des(byte[] key, byte[] data, int cipherMode, String mode, String padding, byte[] iv)
			throws Exception {
		// 密钥转换,将字节数组转为密钥对象
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory skFactory = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skFactory.generateSecret(dks);

		Cipher cipher = null;
		String transformation = "";

		if (mode == null || !"CBC".equals(mode)) // 为空，或不为CBC都默认为ECB
		{
			if (padding == null || "".equals(padding)) {
				transformation = "DES/ECB/NoPadding";
			} else {
				transformation = "DES/ECB/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk);
		} else // 不为空，且为CBC
		{
			if (padding == null) {
				transformation = "DES/CBC/NoPadding";
			} else {
				transformation = "DES/CBC/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk, new IvParameterSpec(iv));
		}
		return cipher.doFinal(data);
	}

	/**
	 * @Description: DES加密 ECB模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_DES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return des(key, data, Cipher.ENCRYPT_MODE, "ECB", "NoPadding", null);

	}

	/**
	 * @Description: DES加密 ECB模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_DES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return des(key, data, Cipher.ENCRYPT_MODE, "ECB", "PKCS5Padding", null);

	}

	/**
	 * @Description: DES加密 CBC模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_DES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return des(key, data, Cipher.ENCRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: DES加密 CBC模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_DES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return des(key, data, Cipher.ENCRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: DES解密 CBC模式 PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_DES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return des(key, data, Cipher.DECRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: DES解密 CBC NoPadding填充方式
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_DES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return des(key, data, Cipher.DECRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: DES解密 ECB PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_DES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return des(key, data, Cipher.DECRYPT_MODE, "ECB", "PKCS5Padding", null);
	}

	/**
	 * @Description: DES解密 ECB NoPadding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_DES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return des(key, data, Cipher.DECRYPT_MODE, "ECB", "NoPadding", null);
	}

	/**
	 * @Description: tripleDes(3DES) 加/解密
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加/解密密钥 长度不能少于24
	 * @param data
	 *            待加/解密的数据 如果不是8的整数倍，需要填充，建议pkcs5padding
	 * @param cipherMode
	 *            模式 [加密]：Cipher.ENCRYPT_MODE [解密]：Cipher.DECRYPT_MODE
	 * @param mode
	 *            "CBC" or "ECB"
	 * @param padding
	 *            "Nopadding" or "PKCS5Padding" or "ISO10126Padding"（不推荐使用）
	 * @param iv
	 *            初始向量 长度必须是8
	 * @return
	 * @throws Exception
	 */
	private static byte[] tripleDes(byte[] key, byte[] data, int cipherMode, String mode, String padding, byte[] iv)
			throws Exception {
		// 密钥转换,将字节数组转为密钥对象
		DESedeKeySpec dks = new DESedeKeySpec(key);
		SecretKeyFactory skFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey sk = skFactory.generateSecret(dks);

		Cipher cipher = null;
		String transformation = "";

		if (mode == null || !"CBC".equals(mode)) // 为空，或不为CBC都默认为ECB
		{
			if (padding == null || "".equals(padding)) {
				transformation = "DESede/ECB/NoPadding";
			} else {
				transformation = "DESede/ECB/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk);
		} else // 不为空，且为CBC
		{
			if (padding == null) {
				transformation = "DESede/CBC/NoPadding";
			} else {
				transformation = "DESede/CBC/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk, new IvParameterSpec(iv));
		}
		return cipher.doFinal(data);
	}

	/**
	 * @Description: TripleDES加密 ECB模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_TripleDES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return tripleDes(key, data, Cipher.ENCRYPT_MODE, "ECB", "NoPadding", null);
	}

	/**
	 * @Description: TripleDES加密 ECB模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_TripleDES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return tripleDes(key, data, Cipher.ENCRYPT_MODE, "ECB", "PKCS5Padding", null);
	}

	/**
	 * @Description: TripleDES加密 CBC模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_TripleDES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return tripleDes(key, data, Cipher.ENCRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: TripleDES加密 CBC模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_TripleDES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return tripleDes(key, data, Cipher.ENCRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: TripleDES解密 CBC模式 PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_TripleDES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return tripleDes(key, data, Cipher.DECRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: TripleDES解密 CBC NoPadding填充方式
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_TripleDES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return tripleDes(key, data, Cipher.DECRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: TripleDES解密 ECB PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_TripleDES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return tripleDes(key, data, Cipher.DECRYPT_MODE, "ECB", "PKCS5Padding", null);
	}

	/**
	 * @Description: TripleDES解密 ECB NoPadding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_TripleDES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return tripleDes(key, data, Cipher.DECRYPT_MODE, "ECB", "NoPadding", null);
	}

	/**
	 * @Description: AES加/解密(128bit)
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加/解密密钥 长度必须是16
	 * @param data
	 *            待加/解密的数据 如果不是16的整数倍，需要填充，建议pkcs5padding
	 * @param cipherMode
	 *            模式 [加密]：Cipher.ENCRYPT_MODE [解密]：Cipher.DECRYPT_MODE
	 * @param mode
	 *            "CBC" or "ECB"
	 * @param padding
	 *            "Nopadding" or "PKCS5Padding" or "ISO10126Padding"（不推荐使用）
	 * @param iv
	 *            初始向量 长度必须是16
	 * @return
	 * @throws Exception
	 */
	private static byte[] aes(byte[] key, byte[] data, int cipherMode, String mode, String padding, byte[] iv)
			throws Exception {
		// 密钥转换,将字节数组转为密钥对象
		SecretKey sk = new SecretKeySpec(key, "AES");

		Cipher cipher = null;
		String transformation = "";

		if (mode == null || !"CBC".equals(mode)) // 为空，或不为CBC都默认为ECB
		{
			if (padding == null || "".equals(padding)) {
				transformation = "AES/ECB/NoPadding";
			} else {
				transformation = "AES/ECB/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk);
		} else // 不为空，且为CBC
		{
			if (padding == null) {
				transformation = "AES/CBC/NoPadding";
			} else {
				transformation = "AES/CBC/" + padding;
			}
			cipher = Cipher.getInstance(transformation);
			cipher.init(cipherMode, sk, new IvParameterSpec(iv));
		}
		return cipher.doFinal(data);
	}

	/**
	 * @Description: AES加密 ECB模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_AES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return aes(key, data, Cipher.ENCRYPT_MODE, "ECB", "NoPadding", null);
	}

	/**
	 * @Description: AES加密 ECB模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_AES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return aes(key, data, Cipher.ENCRYPT_MODE, "ECB", "PKCS5Padding", null);
	}

	/**
	 * @Description: AES加密 CBC模式，NoPadding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_AES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return aes(key, data, Cipher.ENCRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: AES加密 CBC模式，PKCS5Padding填充
	 * @Check parameters by the 【caller】
	 * @param key
	 *            加密密钥
	 * @param data
	 *            待加密数据
	 * @param iv
	 *            初始向量
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt_AES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return aes(key, data, Cipher.ENCRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: AES解密 CBC模式 PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_AES_CBC_PKCS5Padding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return aes(key, data, Cipher.DECRYPT_MODE, "CBC", "PKCS5Padding", iv);
	}

	/**
	 * @Description: AES解密 CBC NoPadding填充方式
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @param iv
	 *            初始向量
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_AES_CBC_NoPadding(byte[] key, byte[] data, byte[] iv) throws Exception {
		return aes(key, data, Cipher.DECRYPT_MODE, "CBC", "NoPadding", iv);
	}

	/**
	 * @Description: AES解密 ECB PKCS5Padding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_AES_ECB_PKCS5Padding(byte[] key, byte[] data) throws Exception {
		return aes(key, data, Cipher.DECRYPT_MODE, "ECB", "PKCS5Padding", null);
	}

	/**
	 * @Description: AES解密 ECB NoPadding填充方式
	 * @Check parameters by the 【caller】
	 * @param key
	 *            解密密钥
	 * @param data
	 *            待解密数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decrypt_AES_ECB_NoPadding(byte[] key, byte[] data) throws Exception {
		return aes(key, data, Cipher.DECRYPT_MODE, "ECB", "NoPadding", null);
	}

	// //TODO:待删除
	public static Map<String, Object> getPriAndPulKeys() throws Exception {
		KeyPairGenerator keypairgen = KeyPairGenerator.getInstance("RSA");
		keypairgen.initialize(2048);
		KeyPair keypair = keypairgen.generateKeyPair();
		RSAPublicKey public1 = (RSAPublicKey) keypair.getPublic();
		RSAPrivateKey private1 = (RSAPrivateKey) keypair.getPrivate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("publickey", public1);
		map.put("privatekey", private1);
		return map;
	}

	/**
	 * @Description: 根据模 和 指数 获取公钥
	 * @Check parameters by the 【caller】
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return 公钥
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) throws Exception {
		BigInteger modulus1 = new BigInteger(modulus);
		BigInteger exponent1 = new BigInteger(exponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus1, exponent1);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}

	/**
	 * @Description: 根据模 和 指数 获取私钥
	 * @Check parameters by the 【caller】
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return 公钥
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) throws Exception {
		BigInteger modulus1 = new BigInteger(modulus);
		BigInteger exponent1 = new BigInteger(exponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus1, exponent1);
		return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	}

	/**
	 * @Description: 公钥加密
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待加密数据
	 * @param publicKey
	 *            公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt_RSA_PublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int maxlength = publicKey.getModulus().bitLength() / 8 - 11;
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > maxlength) {
				cache = cipher.doFinal(data, offSet, maxlength);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * maxlength;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * @Description: 私钥解密
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待解密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt_RSA_PrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int maxlength = privateKey.getModulus().bitLength() / 8;
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > maxlength) {
				cache = cipher.doFinal(data, offSet, maxlength);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * maxlength;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * @Description: 公钥解密
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待解密数据
	 * @param publicKey
	 *            公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt_RSA_PublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		int maxlength = publicKey.getModulus().bitLength() / 8;
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > maxlength) {
				cache = cipher.doFinal(data, offSet, maxlength);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * maxlength;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * @Description: 私钥加密
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt_RSA_PrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		int maxlength = privateKey.getModulus().bitLength() / 8 - 11;
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > maxlength) {
				cache = cipher.doFinal(data, offSet, maxlength);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * maxlength;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * @Description: 将字符串格式的公钥转换
	 * @Check parameters by the 【caller】
	 * @param key
	 *            字符串格式公钥
	 * @param isHexorBase64
	 *            true：16进制格式 false:base64格式(需要引用apche commons codec工具包)
	 * @return 返回rsa公钥
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKeyByBinary(String key, boolean isHexorBase64) throws Exception {
		byte[] keyBytes;
		if (isHexorBase64) {
			keyBytes = hexStringToBinarys(key);
		} else {
			keyBytes = Base64.decodeBase64(key);
		}
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		RSAPublicKey public1 = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		return public1;
	}

	/**
	 * @Description: 将字符串格式的私钥转换
	 * @Check parameters by the 【caller】
	 * @param key
	 *            字符串格式私钥
	 * @param isHexorBase64
	 *            true：16进制格式 false:base64格式(需要引用apche commons codec工具包)
	 * @return 返回rsa私钥
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKeyByBinary(String key, boolean isHexorBase64) throws Exception {
		byte[] keyBytes;
		if (isHexorBase64) {
			keyBytes = hexStringToBinarys(key);
		} else {
			keyBytes = Base64.decodeBase64(key);
		}
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec pkcs8encodedkeyspec = new PKCS8EncodedKeySpec(keyBytes);
		RSAPrivateKey private1 = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8encodedkeyspec);
		return private1;
	}

	/**
	 * @Description: RSA 私钥签名
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待签名数据
	 * @param privateKey
	 *            私钥
	 * @return 签名后的数据
	 * @throws Exception
	 */
	public static byte[] signByRSA(byte[] data, RSAPrivateKey privateKey, String signType) throws Exception {
		Signature signature = Signature.getInstance(signType);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * @Description: RSA 公钥验签
	 * @Check parameters by the 【caller】
	 * @param data
	 *            待验签数据
	 * @param publicKey
	 *            公钥
	 * @param signdata
	 *            签名数据
	 * @return boolean结果
	 * @throws Exception
	 */
	public static boolean verifySignByRSA(byte[] data, RSAPublicKey publicKey, String signType, byte[] signdata)
			throws Exception {
		Signature signature = Signature.getInstance(signType);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(signdata);
	}

	/**
	 * @Description: 生成DH密钥对
	 * @Check parameters by the 【caller】
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("DH");
		keypairgenerator.initialize(1024);
		KeyPair keypair = keypairgenerator.generateKeyPair();
		DHPublicKey dhpublickey = (DHPublicKey) keypair.getPublic();
		DHPrivateKey dhprivatekey = (DHPrivateKey) keypair.getPrivate();
		map.put("DHPrivateKey", dhprivatekey);
		map.put("DHPublicKey", dhpublickey);
		return map;
	}

	/**
	 * @Description: 根据公钥生成DH密钥对
	 * @Check parameters by the 【caller】
	 * @param publickey
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getKeyPair(byte[] publickey) throws Exception {
		X509EncodedKeySpec x509keyspec = new X509EncodedKeySpec(publickey);
		KeyFactory keyfactory = KeyFactory.getInstance("DH");
		PublicKey publickey1 = keyfactory.generatePublic(x509keyspec);
		DHParameterSpec dhparameterspec = ((DHPublicKey) publickey1).getParams();
		KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance(keyfactory.getAlgorithm());
		keypairgenerator.initialize(dhparameterspec);
		KeyPair keypair = keypairgenerator.generateKeyPair();
		Map<String, Object> map = new HashMap<String, Object>();
		DHPublicKey dhpublickey = (DHPublicKey) keypair.getPublic();
		DHPrivateKey dhprivatekey = (DHPrivateKey) keypair.getPrivate();
		map.put("DHPrivateKey", dhprivatekey);
		map.put("DHPublicKey", dhpublickey);
		return map;
	}

	/**
	 * @Description: 根据公私钥生成本地密钥
	 * @Check parameters by the 【caller】
	 * @param publicKey
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception {
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance("DH");
		// 初始化公钥
		// 密钥材料转换
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
		// 产生公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		// 初始化私钥
		// 密钥材料转换
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		// 产生私钥
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 实例化
		KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
		// 初始化
		keyAgree.init(priKey);
		keyAgree.doPhase(pubKey, true);
		// 生成本地密钥
		SecretKey secretKey = keyAgree.generateSecret("AES");
		return secretKey.getEncoded();
	}

	/**
	 * @Description: md5加密
	 * @Check parameters by the 【caller】
	 * @param str
	 *            待加密字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String md5(String str) throws Exception {
		byte[] md5 = DigestUtils.md5(str);
		String binarysToHexString = binarysToHexString(md5);
		return binarysToHexString;
	}

	/**
	 * @Description: sha1加密
	 * @Check parameters by the 【caller】
	 * @param str
	 *            待加密字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String sha1(String str) throws Exception {
		byte[] sha1 = DigestUtils.sha1(str);
		String binarysToHexString = binarysToHexString(sha1);
		return binarysToHexString;
	}

	/**
	 * @Description: sha256加密
	 * @Check parameters by the 【caller】
	 * @param str
	 *            待加密字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String sha256(String str) throws Exception {
		byte[] sha256 = DigestUtils.sha256(str);
		String binarysToHexString = binarysToHexString(sha256);
		return binarysToHexString;
	}

	/**
	 * @Description: Base64解密
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param str
	 *            待解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String Base64decode(String str) throws Exception {
		byte[] decodeBase64 = Base64.decodeBase64(str);
		String result = new String(decodeBase64, "utf-8");
		return result;
	}

	/**
	 * @Description: Base64解密
	 * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
	 * @param str
	 *            待解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static byte[] Base64decode2Bytes(String str) throws Exception {
		byte[] decodeBase64 = Base64.decodeBase64(str);
		return decodeBase64;
	}

	/**
	 * @Description: Base64加密
	 * @Check parameters by the 【caller】
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String Base64encode(String str) throws Exception {

		byte[] encodeBase64 = Base64.encodeBase64(str.getBytes());
		String result = new String(encodeBase64, "utf-8");
		return result;
	}

	/**
	 * @Description: Base64加密
	 * @Check parameters by the 【caller】
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String Base64encode(byte[] bytes) throws Exception {

		byte[] encodeBase64 = Base64.encodeBase64(bytes);
		String result = new String(encodeBase64, "utf-8");
		return result;
	}

	/**
	 * 字符转义，避免【% _】mysql模糊查询时出现错误
	 * 
	 * @param string
	 */
	public static String StringSymbol(String string) {
		if (string == null || "".equals(string)) {
			return null;
		}
		String symbol = "\\";
		symbol += string;
		return symbol;

	}
}
