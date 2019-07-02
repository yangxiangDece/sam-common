package com.yang.encryption;

import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * RSA算法：RSA 加密算法是目前最有影响力的 公钥加密算法，并且被普遍认为是目前 最优秀的公钥方案 之一。RSA 是第一个能同时用于 加密 和 数字签名 的算法，
 * 它能够 抵抗 到目前为止已知的 所有密码攻击，已被 ISO 推荐为公钥数据加密标准。
 * RSA 加密算法 基于一个十分简单的数论事实：将两个大 素数 相乘十分容易，但想要对其乘积进行 因式分解 却极其困难，因此可以将 乘积 公开作为 加密密钥。
 */
public class RSAHelper {

    public static void main(String[] args) {

    }

    private RSAPublicKey publicKey;
    private RSAPublicKeySpec privateKey;

    static {
        // 使用BouncyCastle作为加密算法实现
//        Security.addProvider(new BouncyCastleProvider());
    }
}
