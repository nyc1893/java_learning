// to test if connection is ok also need to add some jar file
//in file propoty --> add External JARs --> files
// file is located in G:\java_file\poi-bin-3.15-20160924\*.jar
package javaGui;
import java.sql.*;
import javax.swing.*;

import java.util.*;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class sqlcon {
 

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";

    
    

        Connection conn = null;
        Statement stat = null;

        public static Connection db()
        {
        	

        	
            Connection conn = null;


	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	            // 打开链接
	            System.out.println("connecting to database...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            	JOptionPane.showMessageDialog(null, "Connection Success");
        		return conn;
	        }
    		catch(Exception e)
    		{
            	JOptionPane.showMessageDialog(null, "Connection Error");
        		return null;
	        }
    
        }

       

}
