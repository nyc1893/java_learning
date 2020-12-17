//read files from csv and new a SQL table 
// other due to the ',' with null value make the code not stable;

package web_design;

import java.io.BufferedReader; 
import java.io.FileReader; 


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
	
	String[][] temp = new String[2500][6];
	 public static void main(String[] args) 
	 {
		 rd_sql test = new rd_sql();
		 
        test.read();
  
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

	    public void read() {
        try {
	            //先FileReader把文件读出来再bufferReader按行读  reader.readLine(); 没有标题用不着了
	            BufferedReader reader = new BufferedReader(new FileReader("./data/a.csv"));
	            String line = null;
	            int index = 0;
	            while ((line = reader.readLine()) != null) {
	                String item[] = line.split(",");//一行数组
	                
	                System.out.println(item.length);
	                for(int i = 0;i<6;i++)
	                {
	                	temp[index][i] = item[i];
	                }
	                   //     String last = item[];
	                        //System.out.println(line);
	   
	                
	                index++;
	            }
	            /*
		        for(int i = 0;i<temp.length; i++)
		        {
		        	for(int j =0;j<temp[0].length;j++)
		        	System.out.print(temp[i][j]+' ');
		        	
		        	System.out.println(" ");
		        }
		       */ 
		        
	        } catch (Exception e) {
	            //在命令行打印异常信息在程序中出错的位置及原因。
	            e.printStackTrace();
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
           
           for(int i =1; i<4;i++)
           stat.executeUpdate(AddET7(tablename,Integer.parseInt(temp[i][0]),temp[i][1],
        		   Integer.parseInt(temp[i][2]),temp[i][3],"2015-09-28 11:50:36",temp[i][4],temp[i][5]
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
