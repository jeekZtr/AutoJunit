package com.xrzx.testcase; //TODO

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.*;

import com.xrzx.excelutil.ExcelUtil;

/**
*
* com.xrzx.testcase.Calculator Tester.
*
* @author ztr
* @since <pre>2018-04-27 11:11:53 </pre>
* @version 1.0
*
*/

public class CalculatorTest213{
	
	com.xrzx.testcase.Calculator testObj = new com.xrzx.testcase.Calculator(); 
	
	@Before
	public void before() throws Exception{
	
	}
	/**
	*
	*Method:23543452
	*
	*/
	@Test
	public void testAdd()throws Exception{
		// excel文件的地址
		//TODO 读取参数
		ArrayList<ArrayList<String>> rowList = new ArrayList<ArrayList<String>>();
		
		int final_sult_row = 0;
		
		String filePath =  "F:/junitsSuitePara.xls"; //"F:/junitsSuitePara.xls";
    	if(ExcelUtil.isExcel(filePath)){
    		//FileOutputStream out = new FileOutputStream(filePath);
    		FileInputStream is = new FileInputStream(filePath);
    		ExcelUtil sd = new ExcelUtil(is, "2003");
    		
    		ArrayList<ArrayList<String>> lls = sd.read();
    		
    		for(int i=0;i<lls.get(0).size();i++){
    			rowList.add(new ArrayList<String>());
    		}
    		
    		for(int y=0; y<lls.size(); y++){
    			List<String> ls = lls.get(y);
    			String value  = "";
    			for(int x=0;x<ls.size();x++){
    				String cellValue = ls.get(x);
    				//System.out.println("坐标x  " + (x+1) +"  坐标y   " +(y+1)  +"  结果:" +cellValue);	
    				value += cellValue;
    				value += "         ";
    				
    				rowList.get(x).add(cellValue); // 格式化为 横排
    				
    				if(x == 0 && "expect_result".equals( value.trim() ) ){
    				}
    				// 最后结果的填写
    				if(x == 0 && "final_result".equals( value.trim() ) ){
    					final_sult_row = y;
    				}
    			}
    			System.out.println(value);
    		}
    		
    		// 输出   转换参数的数据类型..  TODO
    		// 需要 知道参数的类型 和  个数 , 名称      方法的名称
    		Integer paraSize = 2;
    		
    		for(int i=1;i<rowList.size();i++){
    			ArrayList list = rowList.get(i);
    			double v1=0.0 ,v2=0.0,result=0.0; //TODO
    			for(int j=0;j<list.size();j++){
    				if(j==1){
    					v1 = Double.parseDouble(list.get(j)+"");
    				}else if(j==2){
    					v2 = Double.parseDouble(list.get(j)+"");
    				}else if(j==3){
    					result = Double.parseDouble(list.get(j)+"");
    				}
    			}
    			
    			
    			double final_result =  testObj.add(v1, v2); //TODO
    			
    			list.set(4, final_result); // TODO
    			
    			System.out.print("期待结果"+result);
    			System.out.println("实际结果"+final_result);
    			/*
    			 * TODO 判断结果是否正确的参数  
    			 * 1,调用函数没有返回结果
    			 * 2,返回结果为汉字
    			 * 3,直接操作数据库
    			 * 4, 
    			 */
    			if(final_result - result == 0 ){  
    				System.out.println("结果正确");
    				list.set(5, "正确"); //TODO
    			}else{
    				System.out.println("结果错误");
    				list.set(5, "错误"); //TODO
    			}
    		}
    		// 对比结果 和 计算结果  填写到 excel中
    		Sheet sh = sd.getWorkbook().getSheetAt(0);
			Row row = sh.getRow(final_sult_row);
			Row row2 = sh.getRow(final_sult_row+1);
			
			for(int i=1;i<lls.get(0).size();i++){
				row.createCell(i);
				row2.createCell(i);
    		}
			
			for(int i=1;i<rowList.size();i++){
    			ArrayList list = rowList.get(i);
    			
    			Cell cell = row.getCell(i);
    			Cell cell2 = row2.getCell(i);
    			cell.setCellValue(list.get(final_sult_row)+"");
    			cell2.setCellValue(list.get(final_sult_row+1)+"");
			}
			
			File file = new File(filePath);
    		FileOutputStream  fops = new FileOutputStream(file);
    		
    		//sd.write(0, lls , false);
    		//sd.write(0, lls ,0);
    		
    		sd.getWorkbook().write(fops);
    		fops.flush();
    		fops.close();
			
    	}
	}
	
}


