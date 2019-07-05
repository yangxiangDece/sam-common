package com.yang.encryption;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数字证书：https://www.cnblogs.com/Jtianlin/p/7922512.html
 * <p>
 * 1、首先，客户端向服务器发出加密请求。
 * 2、服务器用自己的私钥加密网页以后，连同本身的数字证书，一起发送给客户端。
 * 3、客户端（浏览器）的"证书管理器"，有"受信任的根证书颁发机构"列表。客户端会根据这张列表，查看解开数字证书的公钥是否在列表之内。
 * 4、如果数字证书记载的网址，与你正在浏览的网址不一致，就说明这张证书可能被冒用，浏览器会发出警告。
 * 5、如果这张数字证书不是由受信任的机构颁发的，浏览器会发出另一种警告。
 * 6、数字证书如果是可靠的，客户端就可以使用证书中的服务器公钥，对信息进行加密，然后与服务器交换加密信息。
 * <p>
 * 在签名的过程中，有一点很关键，收到数据的一方，需要自己保管好公钥，但是要知道每一个发送方都有一个公钥，那么接收数据的人需要保存非常多的公钥，这根本就管理不过来。
 * 并且本地保存的公钥有可能被篡改替换，无从发现。怎么解决这一问题了？由一个统一的证书管理机构来管理所有需要发送数据方的公钥，对公钥进行认证和加密。这个机构也就是
 * 我们常说的CA。认证加密后的公钥，即是证书，又称为CA证书，证书中包含了很多信息，最重要的是申请者的公钥。
 * <p>
 * CA机构在给公钥加密时，用的是一个统一的密钥对，在加密公钥时，用的是其中的私钥。这样，申请者拿到证书后，在发送数据时，用自己的私钥生成签名，将签名、证书和发送内
 * 容一起发给对方，对方拿到了证书后，需要对证书解密以获取到证书中的公钥，解密需要用到CA机构的”统一密钥对“中的公钥，这个公钥也就是我们常说的CA根证书，通常需要我们
 * 到证书颁发机构去下载并安装到相应的收取数据的客户端，如浏览器上面。这个公钥只需要安装一次。有了这个公钥之后，就可以解密证书，拿到发送方的公钥，然后解密发送方发过
 * 来的签名，获取摘要，重新计算摘要，作对比，以验证数据内容的完整性。
 * <p>
 * <p>
 * <p>
 * 加密算法：https://blog.csdn.net/baidu_22254181/article/details/82594072
 * 对称加密和非对称加密：
 * 1、加密算法分 对称加密 和 非对称加密，其中对称加密算法的加密与解密 密钥相同，非对称加密算法的加密密钥与解密 密钥不同，此外，还有一类 不需要密钥 的 散列算法。
 * 2、常见的 对称加密 算法主要有 DES、3DES、AES 等，常见的 非对称算法 主要有 RSA、DSA 等，散列算法 主要有 SHA-1、MD5 等。
 * <p>
 * 对称加密：
 * 1、对称加密算法 是应用较早的加密算法，又称为 共享密钥加密算法。在 对称加密算法 中，使用的密钥只有一个，发送 和 接收 双方都使用这个密钥对数据进行 加密 和 解密。
 * 这就要求加密和解密方事先都必须知道加密的密钥。
 * a、数据加密过程：在对称加密算法中，数据发送方 将 明文 (原始数据) 和 加密密钥 一起经过特殊 加密处理，生成复杂的 加密密文 进行发送。
 * b、数据解密过程：数据接收方 收到密文后，若想读取原数据，则需要使用 加密使用的密钥 及相同算法的 逆算法 对加密的密文进行解密，才能使其恢复成 可读明文。
 * <p>
 * 2、非对称加密算法，又称为 公开密钥加密算法。它需要两个密钥，一个称为 公开密钥 (public key)，即 公钥，另一个称为 私有密钥 (private key)，即 私钥。
 * 因为 加密 和 解密 使用的是两个不同的密钥，所以这种算法称为 非对称加密算法。
 * a、如果使用 公钥 对数据 进行加密，只有用对应的 私钥 才能 进行解密。
 * b、如果使用 私钥 对数据 进行加密，只有用对应的 公钥 才能 进行解密。
 * <p>
 * 常见的签名加密算法：
 * 1、MD5算法：MD5 用的是 哈希函数，它的典型应用是对一段信息产生 信息摘要，以 防止被篡改。严格来说，MD5 不是一种 加密算法 而是 摘要算法。无论是多长的输入，
 * MD5 都会输出长度为 128bits 的一个串 (通常用 16 进制 表示为 32 个字符)。
 * <p>
 * 2、SHA1算法：SHA1 是和 MD5 一样流行的 消息摘要算法，然而 SHA1 比 MD5 的 安全性更强。对于长度小于 2 ^ 64 位的消息，SHA1 会产生一个 160 位的 消息摘要。
 * 基于 MD5、SHA1 的信息摘要特性以及 不可逆 (一般而言)，可以被应用在检查 文件完整性 以及 数字签名 等场景。
 * <p>
 * 3、HMAC算法：HMAC 是密钥相关的 哈希运算消息认证码，HMAC 运算利用 哈希算法 (MD5、SHA1 等)，以 一个密钥 和 一个消息 为输入，生成一个 消息摘要 作为 输出。
 * HMAC 发送方 和 接收方 都有的 key 进行计算，而没有这把 key 的第三方，则是 无法计算 出正确的 散列值的，这样就可以 防止数据被篡改。
 * <p>
 * 4、AES/DES/3DES算法：AES、DES、3DES 都是 对称 的 块加密算法，加解密 的过程是 可逆的。常用的有 AES128、AES192、AES256 (默认安装的JDK尚不支持AES256，需要安装对应的jce补丁进行升级 jce1.7，jce1.8)。
 * a、DES算法：DES 加密算法是一种 分组密码，以 64 位为 分组对数据 加密，它的 密钥长度 是 56 位，加密解密 使用 同一算法。
 * DES 加密算法是对 密钥 进行保密，而 公开算法，包括加密和解密算法。这样，只有掌握了和发送方 相同密钥 的人才能解读由 DES加密算法加密的密文数据。
 * 因此，破译 DES 加密算法实际上就是 搜索密钥的编码。对于 56 位长度的 密钥 来说，如果用 穷举法 来进行搜索的话，其运算次数为 2 ^ 56 次。
 * b、3DES算法：是基于 DES 的 对称算法，对 一块数据 用 三个不同的密钥 进行 三次加密，强度更高。3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，
 * NIST将3-DES指定为过渡的加密标准），是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加密算法，其具体实现如下：
 * 设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密文，这样：
 * 3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
 * 3DES解密过程为：P=Dk1((EK2(Dk3(C)))
 * c、AES算法：AES 加密算法是密码学中的 高级加密标准，该加密算法采用 对称分组密码体制，密钥长度的最少支持为 128 位、 192 位、256 位，分组长度 128 位，
 * 算法应易于各种硬件和软件实现。这种加密算法是美国联邦政府采用的 区块加密标准。AES 本身就是为了取代 DES 的，AES 具有更好的 安全性、效率 和 灵活性。
 * <p>
 * 5、RSA算法：RSA 加密算法是目前最有影响力的 公钥加密算法，并且被普遍认为是目前 最优秀的公钥方案 之一。RSA 是第一个能同时用于 加密 和 数字签名 的算法，
 * 它能够 抵抗 到目前为止已知的 所有密码攻击，已被 ISO 推荐为公钥数据加密标准。
 * RSA 加密算法 基于一个十分简单的数论事实：将两个大 素数 相乘十分容易，但想要对其乘积进行 因式分解 却极其困难，因此可以将 乘积 公开作为 加密密钥。
 */
public class SignatureUtils {

    public static void main(String[] args) throws Exception {
        String content = "ldfjlasdjflajsldfjlasfl2342342342";
        String encryptName = "SHA1";
        System.out.println(encryptStr(content, encryptName));
    }

    public static String encryptStr(String content, String encryptName) {
        MessageDigest messageDigest;
        String strDes;

        byte[] bt = content.getBytes();
        try {
            // 默认使用MD5加密
            if (StringUtils.isBlank(encryptName)) {
                encryptName = "MD5";
            }
            messageDigest = MessageDigest.getInstance(encryptName);
            messageDigest.update(bt);
            strDes = byte2hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder des = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = (Integer.toHexString(bytes[i] & 0xFF));
            if (temp.length() == 1) {
                des.append("0");
            }
            des.append(temp);
        }
        return des.toString();
    }
}
