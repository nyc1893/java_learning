package web_design;

//read xls file only be attenion that the int col of input file need to be non-null;
//otherwise it will have problem

//read1() is to import the custom.xls data into the CustomList table;
//read2() is to import the product.xls data into the InventoryList table;

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
    int line = 0;
//    String filename = "./data/product.xls";
    String filename = "";
    



	public static void main(String[] args) 
	{
		
		rd_sql in = new rd_sql();
        in.Read3();

	}
	
	
    public static String Addrd2(String tablename,
    		int id,
    		String ch1,
    		String ch2,
    		int cnum,
    		String ch3, 
    		
    		int times,
    		String ch4,
    		String ch5,
    		String ch6,
    		String ch7
    		)
	{
    	String result = String.format("insert into %s values("
    			+ "%d,\"%s\",\"%s\", %d,  \"%s\",  "
    			+ "%d,\"%s\",%s,\"%s\",\"%s\")",
    			tablename,id,ch1,ch2,cnum,ch3,times,ch4,ch5,ch6,ch7);
    	System.out.println(result);
    	return result;

	}	
    
    public static String Addrd1(String tablename,int id, String ch1,String ch2,
    		String ch3, String ch4,String ch5,String ch6, String ch7,String ch8)
	{
    	String result = String.format("insert into %s values(%d,\"%s\",\"%s\""
    			+ ",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",
    			tablename,id,ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8);
    	System.out.println(result);
    	return result;

	}	

    public static String Addrd3(String tablename,int id, String ch1,String ch2,
    		String ch3, String ch4,String ch5,String ch6, String ch7,String ch8,
    		String ch9, String ch10,String ch11)
	{
    	String result = String.format("insert into %s values(%d,\"%s\",\"%s\""
    			+ ",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\", "
    			+ "\"%s\",\"%s\",\"%s\" )",
    			tablename,id,ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11);
    	System.out.println(result);
    	return result;

	}	    
    
    
	public void Read1()  
	{
		filename = "./data/custom.xls";
		ArrayList<String[]> arr = new ArrayList<>();	
		long s1=System.currentTimeMillis();
		
		try 
		{
        //1:创建workbook
            Workbook workbook=Workbook.getWorkbook(new File(filename)); 
            //2:获取第一个工作表sheet
            Sheet sheet=workbook.getSheet(0);
            line = sheet.getRows();
            //3:获取数据

//          3:获取数据
          for(int i=0;i<sheet.getRows();i++)
          {
          	String tt[] = new String[sheet.getColumns()];
              for(int j=0;j<sheet.getColumns();j++){
              	
                  Cell cell=sheet.getCell(j,i);
//                  System.out.print(cell.getContents()+" ");
                  tt[j] = cell.getContents();
              }
              arr.add(tt);
//              System.out.println();
          }
//          
            
            
            
            
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
            String temp [][] = (String[][])arr.toArray(new String[0][]);
           String tablename = "customlist";
//           stat.executeUpdate("DROP TABLE "+tablename);
           
           stat.executeUpdate("create table IF NOT EXISTS "+tablename+" "
           		+ "(id int not null,name varchar(80), phone varchar(20),addr varchar(80),  "
           		+ "city varchar(20), state varchar(20), zip varchar(20) ,"
           		+ "tax varchar(20), note varchar(250))");

//           int i= 1;
           for(int i =1; i<line ;i++)
           stat.executeUpdate(Addrd1(tablename,Integer.parseInt(temp[i][0]),temp[i][1],
        		   temp[i][2],temp[i][3],temp[i][4],temp[i][5],temp[i][6],temp[i][7],
        		   temp[i][8].replace("\"", "") ) );


				
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
        
        long s2=System.currentTimeMillis();
        System.out.println("Runing Time = "+(s2-s1)/1000+" s");  
        System.out.println("Goodbye!");
        
        
        
        
        
	}    
   

		
	public void Read2()  
	{
		filename = "./data/product.xls";
		ArrayList<String[]> arr = new ArrayList<>();	
		long s1=System.currentTimeMillis();
		try 
		{
        //1:创建workbook
            Workbook workbook=Workbook.getWorkbook(new File(filename)); 
            //2:获取第一个工作表sheet
            Sheet sheet=workbook.getSheet(0);
            System.out.println(sheet.getRows());
            System.out.println(sheet.getColumns());
            line = sheet.getRows();
//            3:获取数据
            
//            sheet.getRows()
            for(int i=0;i<sheet.getRows();i++)
            {
            	String tt[] = new String[sheet.getColumns()];
                for(int j=0;j<sheet.getColumns();j++){
                	
                    Cell cell=sheet.getCell(j,i);
//                    System.out.print(cell.getContents()+", ");
                    tt[j] = cell.getContents();
                }
                arr.add(tt);
//                System.out.println();
            }
//            
            //最后一步：关闭资源
            workbook.close();
            

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
            

            String temp [][] = (String[][])arr.toArray(new String[0][]);
//            PRIMARY KEY
           String tablename = "InventoryList";
           stat.executeUpdate("DROP TABLE "+tablename);
           stat.executeUpdate("create table IF NOT EXISTS "+tablename+" "
           		+ "(inv_id int not null ,\r\n" + 
           		"en_name varchar(80),\r\n" + 
           		"Caseprice decimal(12,2),\r\n" + 
           		"CATEGORY int  ,\r\n" + 
           		"ch_name varchar(80),  \r\n" + 
           		"Itempercase int,\r\n" + 
           		"Itemprice decimal(12,2),\r\n" + 
           		"Taxable boolean,\r\n" + 
           		"caseunit varchar(20) ,\r\n" + 
           		"itemunit varchar(20) )");
           
//           line =5;
           for(int i =1; i<line;i++)
           {
//        	   if(temp[i][5]=="")   System.out.println(" My god");
	           stat.executeUpdate(
	        		   Addrd2(
    				   tablename,
	        		   Integer.parseInt(temp[i][0]),
	        		   temp[i][1].replace("\"","inches"),
	        		   (temp[i][2]=="")?"0.00" :temp[i][2].replace("$","").replace(",",""),
	        		   (temp[i][3]=="")?100:Integer.parseInt(temp[i][3]),
	        		   temp[i][4].replace("\"","inches"),
	        		   (temp[i][5]=="")?1:Integer.parseInt(temp[i][5]),
        			   (temp[i][6]=="")?"0.00" :temp[i][6].replace("$","").replace(",",""),
	        		  temp[i][7],
//	        		  (temp[i][8]=="")? "ea":
	        			  temp[i][8],
//	        		  (temp[i][9]=="")? "ea":
	        			  temp[i][9]
	        				  )
        		   );
           }
//           (temp[i][5]=="")?"0.00":temp[i][5].replace("$","").replace(",","")
   

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
        long s2=System.currentTimeMillis();
        System.out.println("Runing Time = "+(s2-s1)/1000+" s");  
        System.out.println("Goodbye!");
        
	    }
	
	public void Read3()  
	{
		filename = "./data/supplier.xls";
		ArrayList<String[]> arr = new ArrayList<>();	
		long s1=System.currentTimeMillis();
		try 
		{
        //1:创建workbook
            Workbook workbook=Workbook.getWorkbook(new File(filename)); 
            //2:获取第一个工作表sheet
            Sheet sheet=workbook.getSheet(0);
            System.out.println(sheet.getRows());
            System.out.println(sheet.getColumns());
            line = sheet.getRows();
//            3:获取数据
            
//            sheet.getRows()
            for(int i=0;i<sheet.getRows();i++)
            {
            	String tt[] = new String[sheet.getColumns()];
                for(int j=0;j<sheet.getColumns();j++){
                	
                    Cell cell=sheet.getCell(j,i);
//                    System.out.print(cell.getContents()+", ");
                    tt[j] = cell.getContents();
                }
                arr.add(tt);
//                System.out.println();
            }
//            
            //最后一步：关闭资源
            workbook.close();
            

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
            

            String temp [][] = (String[][])arr.toArray(new String[0][]);
//            PRIMARY KEY
           String tablename = "SellerList";
           stat.executeUpdate("DROP TABLE "+tablename);
           stat.executeUpdate("create table IF NOT EXISTS "+tablename+" "
           		+" (id int not null,  \r\n" + 
           		"name varchar(80), \r\n" + 
           		"addr varchar(80),  \r\n" + 
           		"city varchar(20),\r\n" + 
           		" state varchar(20), \r\n" + 
           		"zip varchar(20) , \r\n" + 
           		"phone varchar(100),\r\n" + 
           		"Fax varchar(100),\r\n" + 
           		"cname varchar(80),\r\n" + 
           		"ctitle varchar(80),\r\n" + 
           		"email varchar(50),\r\n" + 
           		"note varchar(250) )");
           
//           line =5;
           for(int i =1; i<line;i++)
           {
//        	   if(temp[i][5]=="")   System.out.println(" My god");
	           stat.executeUpdate(
	        		   Addrd3(
    				   tablename,
	        		   Integer.parseInt(temp[i][0]),
	        		   temp[i][1].replace("\"","inches").replace("/","~"),
	        		   temp[i][2].replace("\"","inches").replace("/","~"),
	        		   temp[i][3].replace("\"","inches").replace("/","~"),
	        		   temp[i][4].replace("\"","inches").replace("/","~"),
	        		   temp[i][5].replace("\"","inches").replace("/","~"),
	        		   temp[i][6].replace("\"","inches").replace("/","~"),
	        		   temp[i][7].replace("\"","inches").replace("/","~"),
	        		   temp[i][8].replace("\"","inches").replace("/","~"),
	        		   temp[i][9].replace("\"","inches").replace("/","~"),
	        		   temp[i][10].replace("\"","inches").replace("/","~"),
	        		   temp[i][11].replace("\"","inches").replace("/","~")
	        				  )
        		   );
           }
//           (temp[i][5]=="")?"0.00":temp[i][5].replace("$","").replace(",","")
   

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
        long s2=System.currentTimeMillis();
        System.out.println("Runing Time = "+(s2-s1)/1000+" s");  
        System.out.println("Goodbye!");
        
	    }		

}
