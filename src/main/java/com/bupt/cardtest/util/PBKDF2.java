package com.bupt.cardtest.util;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;


/*
 * 文 件 名:  PBKDF2.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright 2009-2011,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  w00275872
 * 创建时间:  2015年8月1日
 */

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author w00275872
 * @version [华为终端云统一账号模块, 2015年8月1日]
 * @see [相关类/方法]
 */
public class PBKDF2
{
    /**
     * 使用 Bouncy Castle 支持PBKDF2的SHA256算法 <功能详细描述>
     * 
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @see [类、类#方法、类#成员]
     */
    public static String generateStorngPasswordHash256ByBCP(byte[] salt, String password)
            throws NoSuchAlgorithmException
    {
        int iterations = 500;
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()),
                salt, iterations);
        KeyParameter key1 = (KeyParameter) generator.generateDerivedMacParameters(256);
        return bytesToHexFun1(salt) + ":" +bytesToHexFun1(key1.getKey());
        //return "没实现的部分";
    }

    public static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[2];
        sr.nextBytes(salt);
        return Arrays.toString(salt);
    }
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 方法一：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }
}
