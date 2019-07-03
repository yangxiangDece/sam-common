package com.yang.encryption;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * 3DES算法：是基于 DES 的 对称算法，对 一块数据 用 三个不同的密钥 进行 三次加密，强度更高。3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，
 * NIST将3-DES指定为过渡的加密标准），是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加密算法，其具体实现如下：
 * 设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密文，这样：
 * 3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
 * 3DES解密过程为：P=Dk1((EK2(Dk3(C)))
 */
public class ThreeDESHelper {

    public static void main(String[] args) throws Exception {
        ThreeDESHelper threeDESHelper = new ThreeDESHelper();
        String content = "SecretKey 负责保存对称密钥";
        byte[] bytes = threeDESHelper.encrypt(content);
        System.out.println("加密后：" + SignatureUtils.byte2hex(bytes));
        byte[] decryptContent = threeDESHelper.decrypt(bytes);
        System.out.println("解密后：" + new String(decryptContent));
    }

    /**
     * SecretKey 负责保存对称密钥
     */
    private SecretKey secretKey;
    /**
     * Cipher 负责完成加密或解密工作
     */
    private Cipher cipher;

    private static final String DES_EDE = "DESede";

    public ThreeDESHelper() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new SunJCE());
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES_EDE);
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance(DES_EDE);
    }

    // 加密
    public byte[] encrypt(String content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = content.getBytes();
        // 加密
        return cipher.doFinal(src);
    }

    // 解密
    public byte[] decrypt(byte[] bytes) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(bytes);
    }
}