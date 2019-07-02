package com.yang.encryption;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static MessageDigest MESSAGE_DIGEST;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // 出错了 打印日志
        }
    }

    public static byte[] digest(byte[] key) {
        return MESSAGE_DIGEST.digest(key);
    }

    public static byte[] digest(String content) {
        return MESSAGE_DIGEST.digest(content.getBytes(Charset.forName("UTF-8")));
    }
}