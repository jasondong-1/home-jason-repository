package com.ideal.tag;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
    	// 初始化 Map tag, area;
    	Initial.initial();
    	
    	// 省份，日期
    	String resultFile = args[0];
    	String area = args[1];
    	//String date = args[2];
    	
    	Manager manager = new Manager(area, Initial.area, Initial.tag, null, resultFile);
    	try {
			manager.process();
		} catch (IOException | SQLException e) {
			log.error(e.getMessage(), e);
		}
    }
}
