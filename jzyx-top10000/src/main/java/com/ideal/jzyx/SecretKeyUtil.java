package com.ideal.jzyx;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2015/11/28.
 */
public class SecretKeyUtil {
    public static SecretKey generateSecretkey() throws NoSuchAlgorithmException {
        SecretKey secretKey= KeyGenerator.getInstance("AES").generateKey();
        return secretKey;
    }
    public static void serialize(String path,Object object) throws Exception {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(object);
        out.flush();
        out.close();
    }
    public static SecretKey deserialize(String path) throws Exception{
        Configuration conf=new Configuration();
        FileSystem fs=FileSystem.get(conf);
        InputStream inputStream=fs.open(new Path(path));
        ObjectInputStream in=new ObjectInputStream(inputStream);
        SecretKey secretKey= (SecretKey) in.readObject();
        return secretKey;
    }

    public static void main(String[] args) {
        try {
            serialize("/home/vendorlx/jarAndShell/keygen",generateSecretkey());
//            SecretKey secretKey=deserialize("f:/keygen");
//            System.out.println(secretKey.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
