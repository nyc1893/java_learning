package javaGui;


import com.spire.doc.*;
import com.spire.doc.documents.*;
//import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.util.ArrayList;

public class Statement {
	
	   public static void main(String[] args)
	   {
	        String[]temp2 =
                {
                	"0","127 Sushi AFC/Raley’s","2","3","4","5","6","7","2020-5-22","2020-12-22",""};

//
//
//                };
//	        
	        
	        String[][] Item =
                {
                        new String[]{"229912","2020-6-19", "22.00"},
                        new String[]{"231338","2020-6-26", "22.00"},
                        new String[]{"231577","2020-8-14", "22.00"},
  
                };
	        top(Item,temp2);
	        

	   }
	   
	   public static void top(String[][]Item, String[] temp2)
	   {
	        
	        String total = gentotal(Item);
	        String[][] data = gendata(Item);
	        String[][] data2 = genadd(temp2);
	        lowLevel(data,data2,total);		   
		   
		   
	   }
	   
	   
	   
	   public static String getTime(String tt)
	   {
		   String arr[] = tt.split("-");
		   String res = "";	   
			
		   res = arr[1]+"/"+ arr[2] + "/"+arr[0];
	        return res;
	   }
	   
	   
	   public static String[][] gendata(String [][] In )  // add Invoice Number here...
	   {
		   
		   ArrayList<String[]> arr = new ArrayList<>();
		   int size = In.length;
		   for(int i = 0;i<size;i++)
		   {
		
			   String[] a = {In[i][0], getTime(In[i][1]),"$"+In[i][2]} ;
			   arr.add(a);
		   }
		   
		   return (String[][])arr.toArray(new String[0][]);
	   }
	   
	   	   
	   
	   public static String[][] genadd(String [] input )  // add Invoice Number here...
	   {
//		   String sysTime = getSysTime();
	        
		   String[][] res =
			   {
				   new String[]{"Date："+ getTime(input[8]) +" - " +getTime(input[9])," "},   
				   new String[]{"Sold To: "+input[1],""},   

			   };
		   return res;
	   }
	   
	   public static String gentotal(String [][] In)
	   {
		   
		   java.text.DecimalFormat myformat=new java.text.DecimalFormat("#0.00");
		   int size = In.length;
		   double sum= 0;

		   for(int i = 0;i<size;i++)
		   {

	
			   double price = Double.parseDouble(In[i][2]);//装换为double类型
			   
			   price=Double.parseDouble(myformat.format(price));//保留bai2为小数
			   sum+=price;

		   }
		   System.out.print(sum+" ");
//		   sum = Double.parseDouble(myformat.format(sum));

		   String res  = myformat.format(sum);
		   System.out.print(res);
		   return res;
	   }
	   	   	   
	   
	   
	   
	        public static void lowLevel(String data[][],String data2[][],String total){   
	        //创建Document对象
		   	String textstyle = "Times New Roman";
	        Document doc = new Document();
	        Section sec = doc.addSection();
	        Paragraph para1 = sec.addParagraph();
	        para1.appendText("Yee Chong Hon Co.");

	       
	        Paragraph para5 = sec.addParagraph();
	        Paragraph para4 = sec.addParagraph();
	        para5.appendText("Wholesaler & Distributor");
	        para4.appendText("(775) 409-4002  (775) 409-4003");

	        Paragraph para2 = sec.addParagraph();
	        para2.appendText("Fax: (775) 409-4005\n");
	        

	        
	        
	        ParagraphStyle style1 = new ParagraphStyle(doc);
	        style1.setName("titleStyle");
	        style1.getCharacterFormat().setBold(true);
	        style1.getCharacterFormat().setTextColor(Color.BLACK);
	        style1.getCharacterFormat().setFontName(textstyle);
	        style1.getCharacterFormat().setFontSize(24f);
	        doc.getStyles().add(style1);
	        para1.applyStyle("titleStyle");
	        
	        para1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para1.getFormat().setAfterSpacing(2f);

	        ParagraphStyle style2 = new ParagraphStyle(doc);
	        style2.setName("bigphone");
//	        style2.getCharacterFormat().setBold(true);
	        style2.getCharacterFormat().setTextColor(Color.BLACK);
	        style2.getCharacterFormat().setFontName(textstyle);
	        style2.getCharacterFormat().setFontSize(12f);
	        doc.getStyles().add(style2);
	        para2.applyStyle("bigphone");
	        
	        para2.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para2.getFormat().setAfterSpacing(2f);	        
	        
	        ParagraphStyle style3 = new ParagraphStyle(doc);
	        style3.setName("soldto");
	        style3.getCharacterFormat().setBold(true);
	        style3.getCharacterFormat().setTextColor(Color.BLACK);
	        style3.getCharacterFormat().setFontName(textstyle);
	        style3.getCharacterFormat().setFontSize(10f);
	        doc.getStyles().add(style3);
//	        

	        
	        ParagraphStyle style4 = new ParagraphStyle(doc);
	        style4.setName("smallphone");
//	        style4.getCharacterFormat().setBold(true);
	        style4.getCharacterFormat().setTextColor(Color.BLACK);
	        style4.getCharacterFormat().setFontName(textstyle);
	        style4.getCharacterFormat().setFontSize(12f);
	        doc.getStyles().add(style4);
	        para4.applyStyle("smallphone");

	        
	        ParagraphStyle style5 = new ParagraphStyle(doc);
	        style5.setName("whole");
//	        style4.getCharacterFormat().setBold(true);
	        style5.getCharacterFormat().setTextColor(Color.BLACK);
	        style5.getCharacterFormat().setFontName(textstyle);
	        style5.getCharacterFormat().setFontSize(10f);
	        doc.getStyles().add(style5);
	        para5.applyStyle("whole");
	       
	        
	
	        
	        para2.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para2.getFormat().setAfterSpacing(2f);	        
	        
	        para5.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para5.getFormat().setAfterSpacing(2f);	  
	        
	        para4.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para4.getFormat().setAfterSpacing(2f);	
	        
//	        Paragraph para6 = sec.addParagraph();
//	        para6.applyStyle("whole");
//	        
//	        para6.appendText("Mailing Address \t\t\t warehouse\n" 
//	        		+ "P.O.Box 21090                    115E. Moana Lane\n"
//	        		+ "Reno,NV 89515					Reno NV89502");   
//	        
//	        para6.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
//	        para6.getFormat().setAfterSpacing(12f);	        
	        //声明数组内容
	        String[][] data4 =
	        	{
	        		      new String[]{"Mailing Address\nP.O.Box 21090\nReno,NV 89515",
                 		"Warehouse\n115E. Moana Lane\nReno NV 89502"},

	        	};
	        Table table4 = sec.addTable(true);
	        table4.resetCells(data4.length, data4[0].length);
	        TableRow row = table4.getRows().get(0);
//	        row.setHeight(80);
	        //写入剩余组内容到表格，并格式化数据
	        for (int r = 0; r < data4.length; r++) 
	        {
	            TableRow dataRow = table4.getRows().get(r );
	            dataRow.setHeight(35);
	            dataRow.setHeightType(TableRowHeightType.Exactly);
//	            dataRow.getRowFormat().setBackColor(Color.white);
	            for (int c = 0; c < data4[r].length; c++) 
	            {

	            	dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);

	                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data4[r][c]);
	                range2.getCharacterFormat().setFontName(textstyle);

	            	range2.getCharacterFormat().setFontSize(9f); 
	                if(c == data4[0].length-1)
	                	 range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
	                else
	                {
	                	range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	            		
	                }
	    	        		
               }
	             
           }        

	        Paragraph para6 = sec.addParagraph();
	        para6.appendText("");
	        


	        //添加表格
	        Table table2 = sec.addTable(true);
	        table2.resetCells(data2.length, data2[0].length);
	        table2.getRows().get(0).getCells().get(0).setWidth(800);
	        table2.getRows().get(0).getCells().get(1).setWidth(400);
	        row = table2.getRows().get(0);
//	        row.setHeight(5);
	        //写入剩余组内容到表格，并格式化数据
	        for (int r = 0; r < data2.length; r++) {
	            TableRow dataRow = table2.getRows().get(r );
	            dataRow.setHeight(14);
	            dataRow.setHeightType(TableRowHeightType.Exactly);
//	            dataRow.getRowFormat().setBackColor(Color.white);
	            for (int c = 0; c < data2[r].length; c++) 
	            {

	            	dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);

	                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data2[r][c]);
	                range2.getCharacterFormat().setFontName(textstyle);

                  	range2.getCharacterFormat().setFontSize(11f);
               	range2.getCharacterFormat().setBold(true);
     
      
	                	range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	 
   
               }
	             
           }
   
	        Paragraph para7 = sec.addParagraph();
	        para7.appendText("");    
	        
	        Paragraph para8 = sec.addParagraph();
	        para8.appendText("Statement");   
	        para8.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para8.getFormat().setAfterSpacing(2f);
	        
	        Paragraph para9 = sec.addParagraph();
	        para9.appendText("");   
	        
	        ParagraphStyle style6 = new ParagraphStyle(doc);
	        style6.setName("state");
	        style6.getCharacterFormat().setBold(true);
	        style6.getCharacterFormat().setTextColor(Color.BLACK);
	        style6.getCharacterFormat().setFontName(textstyle);
	        style6.getCharacterFormat().setFontSize(14f);
	        doc.getStyles().add(style6);
	        para8.applyStyle("state");
	        
	        //声明数组内容
	        String[] header = {"Invoice #","Order Date","Order Total"};


	        //添加表格
	        Table table = sec.addTable(true);
	        table.resetCells(data.length + 1, header.length);

	        //设置表格第一行作为表头，写入表头数组内容，并格式化表头数据
	        row = table.getRows().get(0);
//	        TableRow row = table.getRows().get(0);
	        table.getRows().get(0).getCells().get(0).setWidth(244);
	        table.getRows().get(0).getCells().get(1).setWidth(244);
	        table.getRows().get(0).getCells().get(2).setWidth(244);
	        row.isHeader(true);
//	        row.setHeight(20);
//	        row.setHeightType(TableRowHeightType.Exactly);
//	        row.getRowFormat().setBackColor(Color.ORANGE);
	        for (int i = 0; i < header.length; i++) {
	            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
	            Paragraph p = row.getCells().get(i).addParagraph();
	            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	            TextRange range1 = p.appendText(header[i]);
	            range1.getCharacterFormat().setFontName(textstyle);
	            range1.getCharacterFormat().setFontSize(11f);
	            range1.getCharacterFormat().setBold(true);
//	            range1.getCharacterFormat().setTextColor(Color.white);
	            
                if(i == data[0].length-1)
               	 range1.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
               else
               {
               	range1.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
           		
               }
	            
	        }

	        //写入剩余组内容到表格，并格式化数据
	        for (int r = 0; r < data.length; r++) {
	            TableRow dataRow = table.getRows().get(r + 1);
//	            dataRow.getCells().get(0).setWidth(330);
//	            dataRow.getCells().get(1).setWidth(330);
//	            dataRow.getCells().get(2).setWidth(400);
//	            dataRow.setHeight(25);
//	            dataRow.setHeightType(TableRowHeightType.Exactly);
//	            dataRow.getRowFormat().setBackColor(Color.white);
	            for (int c = 0; c < data[r].length; c++) {
	
           		dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
	                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
	                
	                range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	                range2.getCharacterFormat().setFontName(textstyle);
	                if(c == data[0].length-1)
	                	 range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
	                else
	                {
	                	range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	            		
	                }

	   
	                range2.getCharacterFormat().setFontSize(11f);
	
	            }
	        }


	        Paragraph para3 = sec.addParagraph();
	        para3.appendText("");

	        String[][] data3 =
               {
                       new String[]{"","Balance Due:",  "$"+total},

               };	        

	        //添加表格
	        Table table3 = sec.addTable(true);
	        table3.resetCells(data3.length, data3[0].length);
	        table3.getRows().get(0).getCells().get(0).setWidth(280);
	        table3.getRows().get(0).getCells().get(1).setWidth(350);
//	        table3.getRows().get(0).getCells().get(2).setWidth(250);
	        row = table3.getRows().get(0);
	        row.setHeight(30);
	        //写入剩余组内容到表格，并格式化数据
	        for (int r = 0; r < data3.length; r++) {
	            TableRow dataRow = table3.getRows().get(r);
	            dataRow.setHeight(16);
	            dataRow.setHeightType(TableRowHeightType.Exactly);
	            dataRow.getRowFormat().setBackColor(Color.white);
	            
	            for (int c = 0; c < data3[r].length; c++) {
	            	
	            	dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
	                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data3[r][c]);
	                if(c==0)
	                range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	                else if(c>0)
	                	range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
	                range2.getCharacterFormat().setFontName(textstyle);
	                range2.getCharacterFormat().setBold(true);

	            }
	        }
	        
	        //设置表格边框样式
	        table.getTableFormat().getBorders().setBorderType(BorderStyle.Single);
//	        table.autoFit(AutoFitBehaviorType.Auto_Fit_To_Contents);
	        table2.getTableFormat().getBorders().setBorderType(BorderStyle.None);
	        table4.getTableFormat().getBorders().setBorderType(BorderStyle.None);
//	        table2.autoFit(AutoFitBehaviorType.Fixed_Column_Widths);
//	        table2.autoFit(AutoFitBehaviorType.Auto_Fit_To_Contents);
	        table3.getTableFormat().getBorders().setBorderType(BorderStyle.None);
	        //保存文档
	        
	        HeaderFooter footer = sec.getHeadersFooters().getFooter();
	        Paragraph fpara= footer.addParagraph();
//	        fpara.appendField("页码",FieldType.Field_Page);
	        fpara.appendText("\nAll accounts are due and payable not later than the tenth of the\nmonth "
	        		+ "following purchase.  There will be a 1 1/2% per month charge\non balances due 30 days "
	        		+ "or more.  This amounts to 18% per annum.");
//	        fpara.appendField("总页数",FieldType.Field_Num_Pages);
	        fpara.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        fpara.getFormat().getBorders().getTop().setBorderType(BorderStyle.None);
	        fpara.getFormat().getBorders().getTop().setLineWidth(0f);
	        fpara.getFormat().getBorders().getTop().setSpace(2f);
	        
	        
	        ParagraphStyle style10 = new ParagraphStyle(doc);
	        style10.setName("foot");
//	        style4.getCharacterFormat().setBold(true);
	        style10.getCharacterFormat().setTextColor(Color.BLACK);
	        style10.getCharacterFormat().setFontName(textstyle);
	        style10.getCharacterFormat().setFontSize(10f);
	        doc.getStyles().add(style10);
	        fpara.applyStyle("foot");	  
	        
	        doc.saveToFile("./img/Statement.docx", FileFormat.Docx_2013);
	        System.out.println("save done");
	    }


}

