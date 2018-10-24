package com.xrzx.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testTime {
   public static void main(String arags[]) throws IOException{
//	   Long  d = 1525226590979L; 
//	   Date da = new Date(d);
//	   
//	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
//	   String adas = sdf.format(da);
//	   System.out.println(adas);  
	   
	   /* 写入Txt文件 */  
	   //File writename = new File(".\\src\\com\\xrzx\\res\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
//	   File writename = new File(".\\src\\com\\xrzx\\res\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
//	   try {
//			writename.createNewFile();
//			BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
//			out.write("我会写入文件啦\r\n"); // \r\n即为换行  
//			out.write("我会写入文件啦\r\n"); // \r\n即为换行  
//			out.write("我会写入文件啦\r\n"); // \r\n即为换行  
//			out.flush(); // 把缓存区内容压入文件  
//			out.close(); // 最后记得关闭文件  
//	    }catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // 创建新文件  
	   
	   //File f = new File(".");
	   File f = new File(".");

	   String absolutePath = f.getAbsolutePath();

	   System.out.println(absolutePath);
	   
	   
	   //File fl = new File("config/1.txt");      
	   File f2 = new File("src/freemaker/test/mainTest.java");      
       System.out.println(f2.getAbsolutePath());
   }
   
   
}
