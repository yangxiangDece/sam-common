package com.yang.encryption;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES算法：AES 加密算法是密码学中的 高级加密标准，该加密算法采用 对称分组密码体制，密钥长度的最少支持为 128 位、 192 位、256 位，分组长度 128 位，
 * 算法应易于各种硬件和软件实现。这种加密算法是美国联邦政府采用的 区块加密标准。AES 本身就是为了取代 DES 的，AES 具有更好的 安全性、效率 和 灵活性。
 */
public class AESHelper {
    public static void main(String[] args) {

    }

    private SecretKeySpec keySpec;
    private IvParameterSpec iv;

    public AESHelper(byte[] aesKey, byte[] iv) {
        if (aesKey == null || aesKey.length < 16 || (iv != null && iv.length < 16)) {
            throw new RuntimeException("错误的初始秘钥...");
        }
    }
}
