package com.ideal.tag;

import com.ideal.tag.entity.ConnectionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionManager {
	private static final Logger logger = LoggerFactory.getLogger(DBConnectionManager.class);
	private Connection connection;
	
	private DBConnectionManager() {
		try {
			ConnectionConfig config = ConnectionConfig.from();
			this.connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPass());
			// this.connection =
			// DriverManager.getConnection("jdbc:mysql://192.168.1.96:3306/jzyx?useUnicode=true&characterEncoding=UTF-8","propm","jzyx");
		} catch (SQLException e) {
			logger.error("数据库连接失败", e);
			return;
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					closeConnection();
				} catch (Exception e) {
					logger.error("关闭数据库连接失败", e);
				}
			}
		});
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		if (this.connection == null)
			return;
		try {
			this.connection.close();
		} catch (SQLException e) {
			logger.error("closeConnection error", e);
		}
		this.connection = null;
	}

	public static DBConnectionManager instance() {
		if (SingletonLazy.lazy == null) {
			SingletonLazy.lazy = new DBConnectionManager();
		}
		return SingletonLazy.lazy;
	}

	private static class SingletonLazy {
		public static DBConnectionManager lazy;
	}


	
}
