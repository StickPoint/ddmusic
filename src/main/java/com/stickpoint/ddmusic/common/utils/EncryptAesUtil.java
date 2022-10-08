package com.stickpoint.ddmusic.common.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.stickpoint.ddmusic.common.constriant.SystemCache;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * aes加解密工具类
 *
 * @author fntp
 * @version 1.0
 * @date 2021/8/3 10:48
 * @since JDK 17
 */
public class EncryptAesUtil {
	
	/**
	 * 构建一个全局日志对象
	 */
    private static final Logger logger = SystemCache.logger;
    
    /**
     * 密钥 AES加解密要求key必须要128个比特位（这里需要长度为16，否则会报错）
     */
    private static final String KEY = "dingdian:$:music";

    /**
     * 算法
     */
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    
    /**
     * 静态常量
     */
    private static final String AES = "AES";

    /**
     * 获取 cipher
     * 这里的秘钥需要经过getBytes处理获得Key字符串的字节数组
     * @param model 传入一个model对象，意为模式，有解密模式，加密模式
     * @return 返回一个经过构建的cipher对象
     * @throws Exception 无异常抛出
     */
    private static Cipher getCipher(int model) throws Exception {
    	byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        logger.log(Level.INFO,"初始化cipher成功！");
        return cipher;
    }

    /**
     * AES加密
     * @param data 传入一个待加密的字符串
     * @return 返回一个加完密后的字符串
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
    	 try {
             //获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
             byte [] byteContent = data.getBytes(StandardCharsets.UTF_8);
             //密码器加密数据
             byte [] encodeContent = getCipher(Cipher.ENCRYPT_MODE).doFinal(byteContent);
             //将加密后的数据转换为字符串返回
             logger.log(Level.INFO,"加密成功！");
             return java.util.Base64.getEncoder().encodeToString(encodeContent);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }

    /**
     * AES解密方法
     * @param data 加密过后的字符串
     * @return 返回一个解密后的字符串
     * @throws Exception 无异常
     */
    public static String decrypt(String data) throws Exception {
    	try {
    		//把密文字符串转回密文字节数组
            byte [] encodeContent = Base64.getDecoder().decode(data); 
            //密码器解密数据
            byte [] byteContent = getCipher(Cipher.DECRYPT_MODE).doFinal(encodeContent); 
            //将解密后的数据转换为字符串返回
            logger.log(Level.INFO,"解密成功！");
            return new String(byteContent,StandardCharsets.UTF_8); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

