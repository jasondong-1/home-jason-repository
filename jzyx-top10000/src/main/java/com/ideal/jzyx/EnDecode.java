package com.ideal.jzyx;

import com.sun.crypto.provider.SunJCE;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Security;

/**
 * Created by Administrator on 2015/11/28.
 */
public class EnDecode {
    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher负责完成加密或解密工作
    private Cipher c;
    //该字节数组负责保存加密的结果
    private String instance="AES";

    public EnDecode(String path) throws Exception{
            Security.addProvider(new SunJCE());
            //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
            keygen = KeyGenerator.getInstance("AES");
            //生成密钥
            deskey = SecretKeyUtil.deserialize(path);
            c = Cipher.getInstance(instance);

    }



    public String Encrytor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] cipherByte = c.doFinal(str.getBytes("utf-8"));
        String s=new BASE64Encoder().encode(cipherByte);
        return s;
    }


    public String Decryptor(String mi) throws Exception,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(mi);
        byte[] b=c.doFinal(decordedValue);
        return new String(b);
    }

}
