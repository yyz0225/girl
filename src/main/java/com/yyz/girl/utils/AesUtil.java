package com.yyz.girl.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author yyz
 * AES加密解密工具类
 */
public class AesUtil {

    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static final String LOGINSALT = "N26KpOrMfGVCSUSu";
    public static final String REGISTERSALT = "RdT9oAOvwNp3kgOz";
    public static final String UPDATESALT = "D14NBYiQCiUDNw/P";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String( bytes );
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty( base64Code ) ? null : new BASE64Decoder().decodeBuffer( base64Code );
    }

   public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
       SecureRandom secureRandom = SecureRandom.getInstance( "SHA1PRNG" );
       secureRandom.setSeed( encryptKey.getBytes() );
        KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
        kgen.init( 128 ,secureRandom);
        Cipher cipher = Cipher.getInstance( ALGORITHMSTR );
        cipher.init( Cipher.ENCRYPT_MODE, new SecretKeySpec( encryptKey.getBytes() , "AES") );
        return cipher.doFinal( content.getBytes( "utf-8" ) );
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode( aesEncryptToBytes( content, encryptKey ) );
    }

    public static String aesDecryptToBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance( "SHA1PRNG" );
        secureRandom.setSeed( decryptKey.getBytes() );
        KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
        kgen.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance( ALGORITHMSTR );
        cipher.init( Cipher.DECRYPT_MODE, new SecretKeySpec( decryptKey.getBytes() , "AES") );
        byte[] decryptBytes = cipher.doFinal( encryptBytes );
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty( encryptStr ) ? null : aesDecryptToBytes( base64Decode( encryptStr ), decryptKey );
    }

    public static void main(String[] args) throws Exception {
        /*String loginSalt = GenerateRandomKeyUtil.getRandomKey( 16 );
        String registerSalt = GenerateRandomKeyUtil.getRandomKey( 16 );
        String updateSalt = GenerateRandomKeyUtil.getRandomKey( 16 );
        System.out.println("loginSalt：  " + loginSalt);
        System.out.println("registerSalt：  " + registerSalt);
        System.out.println("updateSalt：  " + updateSalt);*/


        String name = aesEncrypt( "admin",  "N26KpOrMfGVCSUSu");
        String password = aesEncrypt( "a1234567", "N26KpOrMfGVCSUSu" );
        System.out.print("加密前：admin  ");
        System.out.println("加密后：" + name );
        System.out.println("解密后：" + aesDecrypt( name, "N26KpOrMfGVCSUSu" ) );

        System.out.print("加密前：a1234567  ");
        System.out.println("加密后：" + password );
        System.out.print("解密后：" + aesDecrypt( password, "N26KpOrMfGVCSUSu" ) );

    }

}
