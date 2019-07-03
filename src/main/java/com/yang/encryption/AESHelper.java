package com.yang.encryption;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * AES算法：AES 加密算法是密码学中的 高级加密标准，该加密算法采用 对称分组密码体制，密钥长度的最少支持为 128 位、 192 位、256 位，分组长度 128 位，
 * 算法应易于各种硬件和软件实现。这种加密算法是美国联邦政府采用的 区块加密标准。AES 本身就是为了取代 DES 的，AES 具有更好的 安全性、效率 和 灵活性。
 */
public class AESHelper {

    public static void main(String[] args) throws Exception {
        AESHelper aesHelper = new AESHelper();
        String content = "SecretKey 负责保存对称密钥";
        byte[] bytes = aesHelper.encrypt(content);
        System.out.println("加密后：" + SignatureUtils.byte2hex(bytes));
        byte[] decryptContent = aesHelper.decrypt(bytes);
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

    private static final String AES = "AES";

    public AESHelper() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new SunJCE());
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance(AES);
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
