package com.yang.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1算法：SHA1 是和 MD5 一样流行的 消息摘要算法，然而 SHA1 比 MD5 的 安全性更强。对于长度小于 2 ^ 64 位的消息，SHA1 会产生一个 160 位的 消息摘要。
 * 基于 MD5、SHA1 的信息摘要特性以及 不可逆 (一般而言)，可以被应用在检查 文件完整性 以及 数字签名 等场景。
 */
public class SHAHelper {

    public static void main(String args[]) throws NoSuchAlgorithmException {

        String content = "SHA1算法：SHA1 是和 MD5 一样流行的 消息摘要算法";
        byte[] resultBytes = SHAHelper.encrypt(content);

        System.out.println("加密后：" + SignatureUtils.byte2hex(resultBytes));
    }

    public static byte[] encrypt(String content) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] srcBytes = content.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        return md5.digest();
    }
}