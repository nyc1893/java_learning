package web_design;

import com.spire.doc.*;
import com.spire.doc.documents.*;
//import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;


import com.spire.doc.FieldType;
import com.spire.doc.FileFormat;
import com.spire.doc.HeaderFooter;
import com.spire.doc.documents.HorizontalAlignment;



import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;


import java.awt.*;

public class CreateTable {
	
	   public static void main(String[] args){
	        //创建Document对象
		   	String textstyle = "Times New Roman";
	        Document doc = new Document();
	        Section sec = doc.addSection();
	        Paragraph para1 = sec.addParagraph();
	        para1.appendText("Yee Chong Hon Co.");

	       
	        Paragraph para5 = sec.addParagraph();
	        Paragraph para4 = sec.addParagraph();
	        para5.appendText("Wholesaler & Distributor");
	        para4.appendText("(775) 409-4002           (775) 409-4003");

	        Paragraph para2 = sec.addParagraph();
	        para2.appendText("FAX: (775) 409-4005\n");
	        

	        
	        
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
	        style2.getCharacterFormat().setBold(true);
	        style2.getCharacterFormat().setTextColor(Color.BLACK);
	        style2.getCharacterFormat().setFontName(textstyle);
	        style2.getCharacterFormat().setFontSize(14f);
	        doc.getStyles().add(style2);
	        para2.applyStyle("bigphone");
	        
	        para2.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	        para2.getFormat().setAfterSpacing(20f);	        
	        
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
	        
	        String[][] data2 =
	                {
	                  
	                        new String[]{"Sold To:",""},
	                        new String[]{"    Egg Roll King (Reno)","Invoice Number:0"},
	                        new String[]{"    t635 Booth St","Date:4-15-2020"},
	                        new String[]{"    Reno,NV 89509","P.O.Number:"},
	                        new String[]{"    Tel:775474324","Terms:30days"},
	
	                };

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
	        

	        	        
	        //声明数组内容
	        String[] header = {"Qty","Description","Price", "", "Total"};
	        String[][] data =
	                {
	                        new String[]{"1 bag"," 鞍山的hi金娃汉武帝华为U2313213Iohweu  太太基金", "S22.00", "", "S22.00"},
	                        new String[]{"1 pkg","花椒粒", "S22.00", "", "S22.00"},
	                        new String[]{"1 bag","芽菜", "S22.00", "", "S22.00"},
	  
	                };

	        //添加表格
	        Table table = sec.addTable(true);
	        table.resetCells(data.length + 1, header.length);

	        //设置表格第一行作为表头，写入表头数组内容，并格式化表头数据
	        row = table.getRows().get(0);
//	        TableRow row = table.getRows().get(0);
	        table.getRows().get(0).getCells().get(0).setWidth(120);
	        table.getRows().get(0).getCells().get(1).setWidth(900);
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
	        }

	        //写入剩余组内容到表格，并格式化数据
	        for (int r = 0; r < data.length; r++) {
	            TableRow dataRow = table.getRows().get(r + 1);
	            dataRow.getCells().get(0).setWidth(120);
	            dataRow.getCells().get(1).setWidth(900);
//	            dataRow.setHeight(25);
//	            dataRow.setHeightType(TableRowHeightType.Exactly);
//	            dataRow.getRowFormat().setBackColor(Color.white);
	            for (int c = 0; c < data[r].length; c++) {
	
            		dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
	                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
	                
	                range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
	                range2.getCharacterFormat().setFontName(textstyle);


	   
	                range2.getCharacterFormat().setFontSize(11f);
	
	            }
	        }


	        Paragraph para3 = sec.addParagraph();
	        para3.appendText("\n\n\n\n");

	        String[][] data3 =
                {
                        new String[]{"					","Subtotal",  "S168.00"},
                        new String[]{"					","Tax", 		"S22.00"},
                        new String[]{"Number of Items:1","Total:", 		"S22.00", },
                        new String[]{" "," ", " " },
                };	        

	        //添加表格
	        Table table3 = sec.addTable(true);
	        table3.resetCells(data3.length, data3[0].length);
	        table3.getRows().get(0).getCells().get(0).setWidth(660);
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
	        doc.saveToFile("./data/CreateTable.docx", FileFormat.Docx_2013);
	        System.out.println("save done");
	    }

}
