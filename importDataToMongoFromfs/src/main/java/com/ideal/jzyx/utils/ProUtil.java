package com.ideal.jzyx.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * ��ȡ�����ļ�
 * @author Administrator
 *
 */
public class ProUtil {
	public static Properties getConf(){
		Properties conf=null;
		try{
			conf=new Properties();
			InputStream in=ProUtil.class.getClassLoader().getResourceAsStream("conf.properties");
			conf.load(in);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conf;
	}
}
