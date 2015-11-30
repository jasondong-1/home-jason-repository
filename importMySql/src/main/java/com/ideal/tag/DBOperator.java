package com.ideal.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBOperator {
    private static final Logger log = LoggerFactory.getLogger(DBOperator.class);
	
    public static Long insert(Connection conn, String sql) throws SQLException {
        // String upd="insert into test (id,name) values(1001,xuzhaori)";
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return null;
    }
    
    public static void update(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
    
    public static ResultSet query(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
    
    public static void delete(Connection conn, String sql) throws SQLException {
    	Statement st = conn.createStatement();
    	int result = st.executeUpdate(sql);
    	//处理结果
        if(result>0){
            log.info("删除成功");
        }else{
        	log.error("删除失败");
        }
    }
    
}
