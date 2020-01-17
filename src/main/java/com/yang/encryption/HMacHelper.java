package com.yang.encryption;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * HMAC算法 是密钥相关的 哈希运算消息认证码，HMAC 运算利用 哈希算法 (MD5、SHA1 等)，以 一个密钥 和 一个消息 为输入，生成一个 消息摘要 作为 输出。
 * HMAC 发送方 和 接收方 都有的 key 进行计算，而没有这把 key 的第三方，则是 无法计算 出正确的 散列值的，这样就可以 防止数据被篡改。
 * <p>
 * HMAC 算法实例在 多线程环境 下是 不安全的。但是需要在 多线程访问 时，进行同步的辅助类，使用 ThreadLocal 为 每个线程缓存 一个实例可以避免进行锁操作。
 */
public class HMacHelper {

    public static void main(String[] args) {
        String content = "aallaaallallaa";
        String key = "111111113234243";
        HMacHelper hMacHelper = new HMacHelper(key);
        byte[] bytes = hMacHelper.sign(content.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密后转为十六进制为：" + SignatureUtils.byte2hex(bytes));
        System.out.println("验证加密结果：" + hMacHelper.verify(bytes, content.getBytes(StandardCharsets.UTF_8)));
    }

    private Mac mac;
    /**
     * MAC算法可选以下多种算法
     * HmacMD5/HmacSHA1/HmacSHA256/HmacSHA384/HmacSHA512
     */
    private static final String KEY_MAC = "HmacMD5";

    private HMacHelper() {
    }

    public HMacHelper(String key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), KEY_MAC);
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
        } catch (Exception e) {
            // 出错了 打印日志
        }
    }

    private byte[] sign(byte[] content) {
        return mac.doFinal(content);
    }

    private boolean verify(byte[] signature, byte[] content) {
        try {
            return Arrays.equals(signature, mac.doFinal(content));
        } catch (Exception e) {
            // 出错了 打印日志
        }
        return false;
    }
}
