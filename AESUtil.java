package com.cx.framework.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {


    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";

    public static String encodeECBPKCS5Padding(String content, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    public static String decodeECBPKCS5Padding(String content, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }
	
	/**
	 * AES加密
	 * @param sSrc
	 * @return
	 * @throws Exception
	 */
	public static String AESEncrypt(String value,String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		byte[] raw = key.getBytes("UTF-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
		String base64 = Base64.getEncoder().encodeToString(encrypted);// 此处使用BASE64做转码
		return URLEncoder.encode(base64, "UTF-8");//URL加密
	}
	
	/**
	 * AES加密 不进行URLEncoder
	 * @param sSrc
	 * @return
	 * @throws Exception
	 */
	public static String AESUNURLEncrypt(String value,String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		byte[] raw = key.getBytes("UTF-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encrypted);// 此处使用BASE64做转码
	}

	/**
	 * AES 解密
	 * @param sSrc
	 * @returnpackage com.cx.framework.utils;
	 *
	 * import java.net.URLDecoder;
	 * import java.net.URLEncoder;
	 * import java.util.Base64;
	 *
	 * import javax.crypto.Cipher;
	 * import javax.crypto.spec.SecretKeySpec;
	 *
	 * public class AESUtil {
	 *
	 *
	 *     /**
	 *      * 密鑰算法
	 *      */
	 *
	private static final String ALGORITHM = "AES";
	 *
			 *     /**
	  *      * 加解密算法/工作模式/填充方式
	  *      */
			 *
	private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";
	 *
			 *

	public static String encodeECBPKCS5Padding(String content, String secretKey) throws Exception {
	 *SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
	 *Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 創建密碼器
	 *cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
	 *return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
	 *}
	 *
			 *

	public static String decodeECBPKCS5Padding(String content, String secretKey) throws Exception {
	 *SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
	 *Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 創建密碼器
	 *cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	 *return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
	 *}
	 *
			 *    /**
	  * 	 * AES加密
	  * 	 * @param sSrc
	  * 	 * @return
	  * 	 * @throws Exception
	  *          */

			 * 	public static String AESEncrypt(String value, String key) throws Exception {
	 *Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	 *byte[] raw = key.getBytes("UTF-8");
	 *SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	 *cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	 *byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
	 *String base64 = Base64.getEncoder().encodeToString(encrypted);// 此處使用BASE64做轉碼
	 *return URLEncoder.encode(base64, "UTF-8");//URL加密
	 *}
	 *
			 *    /**
	  * 	 * AES加密 不進行URLEncoder
	  * 	 * @param sSrc
	  * 	 * @return
	  * 	 * @throws Exception
	  *          */

			 * 	public static String AESUNURLEncrypt(String value, String key) throws Exception {
	 *Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	 *byte[] raw = key.getBytes("UTF-8");
	 *SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	 *cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	 *byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
	 *return Base64.getEncoder().encodeToString(encrypted);// 此處使用BASE64做轉碼
	 *}
	 *
			 *    /**
	  * 	 * AES 解密
	  * 	 * @param sSrc
	  * 	 * @return
	  * 	 * @throws Exception
	  *          */

			 * 	public static String AESDecrypt(String value, String key, boolean isDecodeURL) throws Exception {
	 *try {
	 *byte[] raw = key.getBytes("UTF-8");
	 *SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	 *Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	 *cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	 *if (isDecodeURL) value = URLDecoder.decode(value, "UTF-8");
	 *byte[] encrypted1 = Base64.getDecoder().decode(value);// 先用base64解密
	 *byte[] original = cipher.doFinal(encrypted1);
	 *String originalString = new String(original, "UTF-8");
	 *return originalString;
	 *} catch (Exception ex) {
	 *ex.printStackTrace();
	 *return null;
	 *}
	 *}
	 *
}
	 * @throws Exception
	 */
	public static String AESDecrypt(String value,String key,boolean isDecodeURL) throws Exception {
		try {
			byte[] raw = key.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			if(isDecodeURL)	value = URLDecoder.decode(value, "UTF-8");
			byte[] encrypted1 = Base64.getDecoder().decode(value);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "UTF-8");
			return originalString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
