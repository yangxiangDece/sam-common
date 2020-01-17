package com.yang.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA算法：RSA 加密算法是目前最有影响力的 公钥加密算法，并且被普遍认为是目前 最优秀的公钥方案 之一。RSA 是第一个能同时用于 加密 和 数字签名 的算法，
 * 它能够 抵抗 到目前为止已知的 所有密码攻击，已被 ISO 推荐为公钥数据加密标准。
 * <p>
 * RSA 加密算法 基于一个十分简单的数论事实：将两个大 素数 相乘十分容易，但想要对其乘积进行 因式分解 却极其困难，因此可以将 乘积 公开作为 加密密钥。
 */
public class RSAHelper {

    public static void main(String[] args) throws NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        RSAHelper rsaHelper = new RSAHelper();
        String content = "RSA加密算法基于一个十分简单的数论事实";

        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为1024位
        pairGenerator.initialize(1024);
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = pairGenerator.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 用公钥加密
        byte[] srcBytes = content.getBytes();
        byte[] resultBytes = rsaHelper.encrypt(publicKey, srcBytes);
        System.out.println("用公钥加密后：" + SignatureUtils.byte2hex(resultBytes));

        // 用私钥解密
        byte[] decBytes = rsaHelper.decrypt(privateKey, resultBytes);
        System.out.println("用私钥解密后：" + new String(decBytes, 0, decBytes.length, StandardCharsets.UTF_8));
    }

    private final static String RSA = "RSA";

    private byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        if (publicKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance(RSA);
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(srcBytes);
        }
        return null;
    }

    private byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        if (privateKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance(RSA);
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(srcBytes);
        }
        return null;
    }
}
