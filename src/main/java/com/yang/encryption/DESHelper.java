package com.yang.encryption;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * DES算法：DES 加密算法是一种 分组密码，以 64 位为 分组对数据 加密，它的 密钥长度 是 56 位，加密解密 使用 同一算法。
 * DES 加密算法是对 密钥 进行保密，而 公开算法，包括加密和解密算法。这样，只有掌握了和发送方 相同密钥 的人才能解读由 DES加密算法加密的密文数据。
 * 因此，破译 DES 加密算法实际上就是 搜索密钥的编码。对于 56 位长度的 密钥 来说，如果用 穷举法 来进行搜索的话，其运算次数为 2 ^ 56 次。
 */
public class DESHelper {

    public static void main(String[] args) throws Exception {
        DESHelper desHelper = new DESHelper();
        String content = "KeyGenerator 提供对称密钥生成器的功能，支持各种算法";
        byte[] bytes = desHelper.encrypt(content);
        System.out.println("加密后：" + SignatureUtils.byte2hex(bytes));
        byte[] decryptContent = desHelper.decrypt(bytes);
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

    private static final String DES = "DES";

    public DESHelper() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new SunJCE());
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);
        // 生成秘钥
        secretKey = keyGenerator.generateKey();
        // 生成Cipher对象，指定其支持的DES算法
        cipher = Cipher.getInstance(DES);
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
