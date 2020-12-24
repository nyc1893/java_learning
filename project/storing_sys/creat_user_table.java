package web_design;

// new tables

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
 
public class creat_user_table {
 

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";

    
    
    public static void main(String[] args) 
    {
        Connection conn = null;
        Statement stat = null;
        
        
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");

            //String sql;
            
            stat = conn.createStatement();	
            //创建数据库hello
            String tablename = "";
            
            /*  OrderList Part */
            
            tablename = "orderList";
            
            stat.executeUpdate("create table IF NOT EXISTS "+tablename
               		+ " (id int,cid int,orderdate date, price decimal(12,2))");        
            
            stat.executeUpdate("insert into "+tablename
            		+ " values(1,1809, '2019-5-12', 22.22)");
            stat.executeUpdate("insert into "+tablename
            		+ " values(2,1809, '2019-12-12',5.03 )");      
            
            stat.executeUpdate("insert into "+tablename
            		+ " values(3,1814,'2020-4-12',25.03)");           
            stat.executeUpdate("insert into "+tablename
            		+ " values(4,1814,'2018-5-12',15.03)");     
            
            /*  LoginUser Part */
            /*
            tablename = "user";   
           stat.executeUpdate("DROP TABLE " + tablename);
           stat.executeUpdate("create table IF NOT EXISTS "+tablename
           		+ " (id int,name varchar(80),pass varchar(80),power varchar(10))");
           stat.executeUpdate(AddET3(tablename,1,"admin","admin","S"));
           stat.executeUpdate(AddET3(tablename,2,"yy","123","S"));
           
           tablename = "nuser";   
//          stat.executeUpdate("DROP TABLE " + tablename);
          stat.executeUpdate("create table IF NOT EXISTS "+tablename
          		+ " (id int,name varchar(80),pass varchar(80),power varchar(10))");     
           
           stat.executeUpdate(AddET3(tablename,3,"guest","guest","N"));
//           
            */
            
            /*  temporder Part */
//            7 variable 
//           stat.executeUpdate("create table IF NOT EXISTS temporder(id int,qty int)");
            
            
            /*  商品大类*/
            
//          tablename = "catelist"; 
//          stat.executeUpdate("create table IF NOT EXISTS catelist(cateid int,chcate varchar(80),encate varchar(80))");
//          stat.executeUpdate(AddET3(tablename,100,"蔬菜","Vegetable"));
//          stat.executeUpdate(AddET3(tablename,200,"冰冻食品","FreezingFood"));          
//          stat.executeUpdate(AddET3(tablename,300,"干货","Dry goods")); 
//          stat.executeUpdate(AddET3(tablename,400,"饮料","Drink")); 
//          stat.executeUpdate(AddET3(tablename,500,"零食","Snacks")); 
//          stat.executeUpdate(AddET3(tablename,600,"调味料","Daily necessities"));
//          stat.executeUpdate(AddET3(tablename,700,"日用品","123"));
//          stat.executeUpdate(AddET3(tablename,800,"其他","Other"));
          
          


            stat.close();
            conn.close();
            System.out.println(" copy down.");
   
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stat!=null) stat.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
    
    public static String AddET3(String tablename,int id, String ch1,String ch2,String ch3 )
	{
    	String result = String.format("insert into %s values(%d,\"%s\",\"%s\",\"%s\")", tablename,id,ch1,ch2,ch3);
    	System.out.println(result);
    	return result;

	}

    public static String AddET4(String tablename,int id, String ch1,double f1,double f2  )
	{
    	String result = String.format("insert into %s values(%d,\"%s\",%f,%f)", tablename,id,ch1,f1,f2);
    	System.out.println(result);
    	return result;

	}   
    
    
}
