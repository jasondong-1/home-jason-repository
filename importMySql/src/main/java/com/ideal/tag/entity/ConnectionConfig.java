package com.ideal.tag.entity;

import java.io.InputStream;
import java.util.Properties;



import com.ideal.tag.DBConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionConfig {
	private static final Logger log = LoggerFactory.getLogger(DBConnectionManager.class);
	
	private String ip;
	private String port;
	private String username;
	private String pass;
	private String url;
	private String database;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public ConnectionConfig(String ip, String port, String username, String pass, String url,String database) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.pass = pass;
		this.url = url;
		this.database=database;
	}
	public static ConnectionConfig from() {
		ConnectionConfig config = null;
		try{
			InputStream in=DBConnectionManager.class.getClassLoader().getResourceAsStream("db.property");
			Properties conf=new Properties();
			conf.load(in);
			String ip=conf.getProperty("ip");
			String port=conf.getProperty("port");
			String username=conf.getProperty("username");
			String pass=conf.getProperty("pass");
			String database=conf.getProperty("database");
			String url="jdbc:mysql://"+ip+":"+port+"/"+database+"?useUnicode=true&characterEncoding=UTF-8";
			config = new ConnectionConfig(ip, port, username, pass, url,database);
		}catch(Exception e){
			log.error("数据库配置文件加载失败");
			System.exit(-1);
		}
		return config;
	}
//	public static void main(String[] args) {
//		ConnectionConfig config = ConnectionConfig.from();
//		System.out.println(config.getIp());
//		System.out.println(config.getUsername());
//		System.out.println(config.getPass());
//		System.out.println(config.getUrl());
//		System.out.println(config.getPort());
//		System.out.println(config.getDatabase());
//	}

}
