package cn.gukeer.common.security;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by connli on 16/4/8.
 */
public class AESencryptor {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    //0wwwicanmakeitcnreverse
    private static final String key = new String(
                    Base64Utils.decode(
                    Base64Utils.decodeFromString("Ym1OMGFXVnJZVzF1WVdOcGQzZDNNQT09")));

    private static byte[] getKeys() {

        byte[] keys = null;
        try {
            keys = key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keys = sha.digest(keys);
            keys = Arrays.copyOf(keys, 16); // use only first 128 bit

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keys;
    }

    /**
     * 使用AES 算法 加密，默认模式 AES/CBC/PKCS5Padding
     */
    public static String encryptCBCPKCS5Padding(String str) {

        String rstStr = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            SecretKeySpec secretKey = new SecretKeySpec(getKeys(), KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getKeys()));//使用加密模式初始化 密钥

            byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
            rstStr = Base64Utils.encodeToString(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rstStr;

    }

    /**
     * 使用AES 算法 解密，默认模式 AES/CBC/PKCS5Padding
     */
    public static String decryptCBCPKCS5Padding(String encryptsStr) {

        String rstStr = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            SecretKeySpec secretKey = new SecretKeySpec(getKeys(), KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getKeys()));//使用解密模式初始化 密钥

            byte[] encrypt = Base64Utils.decodeFromString(encryptsStr); //按单部分操作加密或解密数据，或者结束一个多部分操作。

            byte[] decrypt = cipher.doFinal(encrypt);
            rstStr = new String(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rstStr;
    }

    public static void main(String args[]) {
        String ori = "admin";
        String encodeStr = encryptCBCPKCS5Padding(ori);
        System.out.println(encodeStr);

    }
}
