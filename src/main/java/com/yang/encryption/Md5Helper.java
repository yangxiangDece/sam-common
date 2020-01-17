package com.yang.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法：MD5 用的是 哈希函数，它的典型应用是对一段信息产生 信息摘要，以 防止被篡改。严格来说，MD5 不是一种 加密算法 而是 摘要算法。无论是多长的输入，
 * MD5 都会输出长度为 128bits 的一个串 (通常用 16 进制 表示为 32 个字符)。
 */
public class Md5Helper {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String content = "MD5 即Message-Digest Algorithm 5（信息-摘要算法 5）a";
        byte[] resultBytes = Md5Helper.encrypt(content);

        System.out.println("加密后：" + SignatureUtils.byte2hex(resultBytes));
    }

    public static byte[] encrypt(String content) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = content.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        return md5.digest();
    }
}