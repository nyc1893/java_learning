//read xls file only be attenion that the int col of input file need to be non-null;
// otherwise it will have problem

//read() is to import the custom.xls data into the CustomList table;
//read2() is to import the product.xls data into the InventoryList table;


package web_design;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;

//			"./data/a.csv"
public class rd_sql
{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";
    Connection connection = null;
 
//    String filename = "./data/product.xls";
    String filename = "./data/custom.xls";
//    String temp[][] = new String [2400][8];

    String temp[][] = new String [410][9];
    int lines = 0;
	public static void main(String[] args) 
	{
		
		rd_sql in = new rd_sql();
        in.Read();

	}
	
    	
    public static String AddET7(String tablename,int id, String ch1,int cnum,String ch2,
    		String ch3, String ch4,String ch5)
	{
    	String result = String.format("insert into %s values(%d,\"%s\",%d,\"%s\""
    			+ ",\"%s\",\"%s\",\"%s\",null)",
    			tablename,id,ch1,cnum,ch2,ch3,ch4,ch5);
    	System.out.println(result);
    	return result;

	}	
    
    public static String AddET9(String tablename,int id, String ch1,String ch2,
    		String ch3, String ch4,String ch5,String ch6, String ch7,String ch8)
	{
    	String result = String.format("insert into %s values(%d,\"%s\",\"%s\""
    			+ ",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",
    			tablename,id,ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8);
    	System.out.println(result);
    	return result;

	}	
    
	public void Read()  
	{
    		
		try 
		{
        //1:创建workbook
            Workbook workbook=Workbook.getWorkbook(new File(filename)); 
            //2:获取第一个工作表sheet
            Sheet sheet=workbook.getSheet(0);
            //3:获取数据
            lines = sheet.getRows();
            for(int i=0;i<sheet.getRows();i++)
            {
                for(int j=0;j<sheet.getColumns();j++){
                    Cell cell=sheet.getCell(j,i);
//                    System.out.print(cell.getContents()+" ");
                    temp[i][j] = cell.getContents();
                }
                System.out.println();
            }
            
            //最后一步：关闭资源
            workbook.close();
            
//            for(int i=0;i<6;i++)
//            {
//                for(int j=0;j<temp[0].length;j++)
//                {
//          
//                   System.out.print( temp[i][j]+" ");
//                }        
//                System.out.println();
//            }
		
			}
            catch(Exception se)
			{
                se.printStackTrace();
            }
            
	    Connection conn = null;
        Statement stat = null;
        
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            
            stat = conn.createStatement();	

           String tablename = "customlist";
//           stat.executeUpdate("DROP TABLE "+tablename);
           
           stat.executeUpdate("create table IF NOT EXISTS "+tablename+" "
           		+ "(id int not null,name varchar(80), phone varchar(20),addr varchar(80),  "
           		+ "city varchar(20), state varchar(20), zip varchar(20) ,"
           		+ "tax varchar(20), note varchar(250))");

//           int i= 1;
           for(int i =161; i<lines;i++)
           stat.executeUpdate(AddET9(tablename,Integer.parseInt(temp[i][0]),temp[i][1],
        		   temp[i][2],temp[i][3],temp[i][4],temp[i][5],temp[i][6],temp[i][7],
        		   temp[i][8])  );


				
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
   
        

		
	public void Read2()  
	{
    		
		try 
		{
        //1:创建workbook
            Workbook workbook=Workbook.getWorkbook(new File(filename)); 
            //2:获取第一个工作表sheet
            Sheet sheet=workbook.getSheet(0);
            //3:获取数据
            for(int i=0;i<sheet.getRows();i++)
            {
                for(int j=0;j<sheet.getColumns();j++){
                    Cell cell=sheet.getCell(j,i);
                  //  System.out.print(cell.getContents()+" ");
                    temp[i][j] = cell.getContents();
                }
                System.out.println();
            }
            
            //最后一步：关闭资源
            workbook.close();
            
            for(int i=88;i<92;i++)
            {
                for(int j=0;j<temp[0].length;j++)
                {
          
                   System.out.print( temp[i][j]+" ");
                }        
                System.out.println();
	            }
			}
            catch(Exception se)
			{
                se.printStackTrace();
            }
            
	    Connection conn = null;
        Statement stat = null;
        
        
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            
            stat = conn.createStatement();	
            //创建数据库hello
            

           
           
           String tablename = "InventoryList";
           stat.executeUpdate("DROP TABLE "+tablename);
           stat.executeUpdate("create table IF NOT EXISTS "+tablename+" "
           		+ "(inv_id int not null,en_name varchar(80) not null, CATEGORY int  ,ch_name varchar(80),  "
           		+ "pricetime datetime  , Taxable varchar(10)  ,Units varchar(20) ,"
           		+ "price decimal(6,2)) ");
           
           for(int i =1833; i<2400;i++)
           stat.executeUpdate(AddET7(tablename,Integer.parseInt(temp[i][0]),temp[i][1],
        		   Integer.parseInt(temp[i][2]),temp[i][3],"2015-09-28 11:50:36",temp[i][6],temp[i][7]
        				   ));

   

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
		

}
