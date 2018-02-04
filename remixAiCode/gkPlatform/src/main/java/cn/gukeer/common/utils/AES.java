package cn.gukeer.common.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;


/*
 * AES对称加密和解密
 */
public class AES {
    private static final String AESTYPE ="AES/ECB/PKCS5Padding";

    /*
    加密
     */
    public static String AES_Encrypt(String keyStr, String plainText) {
        byte[] encrypt = null;
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    /*
    解密
     */
    public static String AES_Decrypt(String keyStr, String encryptData) {

     //   byte[] res  = Base64.decodeBase64(encryptData);
      //  encryptData = new String(res).trim();

        byte[] decrypt = null;
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
        }catch(Exception e){
            e.printStackTrace();
        }
        return URLEncoder.encode(new String(decrypt).trim());//解决中文乱码
//        return new String(decrypt).trim();
    }

    private static Key generateKey(String key)throws Exception{
        try{
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String skjf = "+TxM0cUGaCZV/ZsJJgB3dTz3JyeUI9PWHwPpUdi7ToXG7tu31KukIURWSZNjvE/aYIthBytvjWekI97LE0gCow==";
                      //"fKTkogRHk+cHI0ed2c9++hwfz9WyP9Cmc0Yji+SsFo6y/+IFgyt4qX2997JXxrXIQ02MxPFbZ7rPWsDVPZ+ZQ2YB5DyplaRwAvdxPNRQYZe8GxwKpqBUnmHHrf3mq6JE";
        //String userMsg= AES.AES_Decrypt("mooc!@#123cksns6",skjf);

        //String aaa = URLDecoder.decode(userMsg,"UTF-8");
//        System.out.println(URLDecoder.decode(URLDecoder.decode(AES.AES_Decrypt("mooc!@#123cksns6",skjf), "UTF-8")));

       // String res = AES.AES_Encrypt("mooc!@#123cksns6","zhoujie");

        String res1 = AES.AES_Decrypt("CD93nWSNVSFk86b1","eGL5MqYWTeEkJbRSMmTdraStX0fcw4Esr2NrANes6e1wFmW+0QXJ3HX0kYt8rsCyh+mIA7l02WqHjcknR/knn+ooSODX9ALV5CBIUo0V0xZhDbpTHMh5cfTDbGNwLxmF");
        String[] abc = res1.split(",");
        System.out.println();

    }

}
