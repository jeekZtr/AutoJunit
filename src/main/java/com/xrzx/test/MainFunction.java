package com.xrzx.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xrzx.util.CommonUtil;
import com.xrzx.util.ExportMapExcel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



public class MainFunction {
	
	private static final Logger logger = LoggerFactory.getLogger(MainFunction.class);
	
	private static List paraNameList = new ArrayList();  // 参数类型list
	private static String returnTypeName = "";
	private static Class<?>[] parameterTypes ;
	//@filePath()
	//@mothed()
	public static void main(String args[]){
		  //第三种方式获取Class对象  
        try {  
            //Class c3 = Class.forName("com.xrzx.util.ReadExcel");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名  
            //Method[] ms = c3.getMethods();
            
            //logger.info( ms[0].getName() );//判断三种方式是否获取的是同一个Class对象  
            //logger.info( ms[0].getTypeParameters() );//判断三种方式是否获取的是同一个Class对象  
        	
        	
        	//Class c3 = Class.forName("com.newstrength.utils.BaseMail");
        	
        	//String s = TextFile.read("D:/CMSDev/workspace3/autoMakeJunitClass/src/com/xrzx/util/ReadExcel.java");
//        	String s = TextFile.read("D:/CMSDev/workspace3/fe/src/com/newstrength/utils/BaseMail.java");
//        	logger.info(s);
        	
        	/*
        	ExcelUtil excelUtil = new ExcelUtil();
        	File file = new File("F:/compute.xls");
        	List<ExcelSheetPO> sheetPOs = ExcelUtil.readExcel(file, 2, 2);
        	for(int y=0; y<sheetPOs.size() ; y++ ){
        		 ExcelSheetPO esp = sheetPOs.get(y);
        		 List<List<Object>> dataList = esp.getDataList();
        		 for(int j=0;j<dataList.size();j++){
        			 List<Object> list = dataList.get(j);
        			 for(int x=0;x<list.size();x++){
        				 Object o = list.get(x);
        				 logger.info("坐标x  " +x +"  坐标y   " +j  +"  结果:" +o);	
        			 }
        			 //logger.info(list);
        		 }
        	 }
        	 * */
        	mainTestCase("asfdf","","");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 
	 * mothodPath  com.xrzx.testcase.Calculator.add22(Double, Double)
	 * filePath  "F://junitsSuitePara.xls"
	 * classPath "D:/CMSDev/workspace3/fe/test/com/xrzx/testcase/CalculatorTest.java"
	 * 
	 * @param mothodPath   方法的路径
	 * @param filePath  生成的excel文件的路径
	 * @param calssPath  测试的生成路径
	 */
	
	public static void mainTestCase(String mothodPath ,String filePath , String calssPath) {
		//1,输入参数
		//   哪个方法       com.xrzx.testcase.Calculator.div(double,double,int);
		//   生成文件到的地址         D:/CMSDev/workspace3/autoMakeJunitClass/test/resources/templates/
		
		//2, 生成方法的参数excel 
		
		//3, 生成方法的testcase
		
		//4,  
		
		//5,
		
		// 参数:生成参数参数填写的excel地址
		String excelFilePath = "F://junitsSuitePara.xls";
		// 参数:生成参数测试用例的地址
		String calssFilePath = "D:/CMSDev/workspace3/fe/test/com/xrzx/testcase/CalculatorTest.java" ;
		
		try {
			// 参数:测试方法java地址
			Map map = getMethodInfo("com.xrzx.testcase.Calculator.add22(Double, Double)"); 
			logger.info("==============================================================================");
			logger.info("===================================主函数结果==================================");
			logger.info("==============================================================================");
			
			String claseName = map.get("className")+"";
			String isHavaMethod = map.get("isHaveMethod")+"";
			logger.info("是否有方法 --> "+isHavaMethod);
			if("true".equals(isHavaMethod)){
				
				int paraLength = Integer.parseInt(""+map.get("paraLength")); 
				
				String method = map.get("methodName")+"";
				
				Class clazz = Class.forName(claseName);
				if( paraLength > 0 ){
					Class[] para = new Class[paraLength];
					for(int i=0;i<paraLength;i++){
						logger.info("方法参数类型 map.get(\"para\""+i+")--> "+map.get("para"+i));
						if(getDataType((map.get("para"+i)+"").trim())){
							para[i] = getDataTypeClass( (map.get("para"+i)+"").trim() );
						}else{
							para[i] = Class.forName( (map.get("para"+i)+"").trim() );
						}
					}
					Method md  = clazz.getMethod(method,para);
					logger.info("方法-->"+md);
					
					//1.生成填写方法参数excel
					createParamExcel(clazz, md , excelFilePath); //
					try {
						//2,生成 测试的java类
						createTestCase(clazz, md , excelFilePath , calssFilePath );
					} catch (IOException e) {
						e.printStackTrace();
					} catch (TemplateException e) {
						e.printStackTrace();
					}
				}else{
					//方法中没有参数的调用
					Method md  = clazz.getMethod(method);
					System.out.println(md);
					// 方法的调用
					md.invoke(clazz, null);
				}
				
			}else{
				logger.info("参数错误。。。");
			}
			logger.info("===================================主函数结束==================================");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.info("参数错误,没有这个方法..");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void createTestCase(Class clazz ,Method md ,String excelFilePath , String classPath) throws IOException, TemplateException{
		//1. 创建配置类  
        Configuration cfg = new Configuration();  
//          1.1 设置模板加载目录  
        File file = new File("D:/Dev-Workspace-eclipse/ncl-git/AutoJunit/src/test/java/resources/templates");
        
        cfg.setDirectoryForTemplateLoading(file);  
        
//          1.2 设置编码  
        cfg.setDefaultEncoding("UTF-8");  
//          1.3 设置模板更新延迟时间  
        cfg.setTemplateUpdateDelay(0);  
        
        
        
        Map map = new HashMap();
        map.put("className", clazz.getName() );
        
        Map methodMap = new HashMap();
        methodMap.put("signature", "123141234123");
        methodMap.put("name", upperCase( md.getName() ) ); // 测试方法的名称
        
        //TODO  参数的类型转换问题
        String methodParameters = "";
        String paraNames = "";
        String returnType = returnTypeName ;
        for(int i=0;i<paraNameList.size()-2;i++){
        	if(i < paraNameList.size() - 3 ){
        		// 生成测试方法的参数转换语句   java语句
        		methodParameters += CommonUtil.getType(parameterTypes[i]+"")+" " + paraNameList.get(i) +"="+CommonUtil.getTypeParse(parameterTypes[i]+"")+"( list.get("+(i+1)+") + \"\" ) ;\n" ;
        		paraNames +=  paraNameList.get(i) +",";
        	}else{
        		methodParameters += returnType + " result = "+CommonUtil.getTypeParse(returnType)+"( list.get("+((i+1))+")+ \"\"  ) ;";
        	}
        }
        
        logger.info(methodParameters);
        
        int finalResult = 2+parameterTypes.length;
        int finalResultIndex = finalResult+1;
        
        paraNames =  paraNames.substring(0, paraNames.length()-1);
        
//      2. 创建数据模型:模型数据一般用一个map 来构建  
        Map<String,Object> root = new HashMap<String,Object>();  
        root.put("entry", map);  
        root.put("method", methodMap );  
        root.put("date", new Date());  
        root.put("basePackage", clazz.getPackage());  
        root.put("testClass", clazz.getName() );  
        root.put("filePath",excelFilePath );  // excel的文件
        root.put("paraSize", md.getTypeParameters().length);  // 方法的参数的长度
        root.put("testClassName", clazz.getSimpleName() );   // 需要得到类的名称
        root.put("methodName",  md.getName() );  // 需要调用的方法
        root.put("methodParameters",  methodParameters );  // 需要调用的方法
        root.put("finalResult",  finalResult );  // 需要调用的方法
        root.put("finalResultIndex",  finalResultIndex );  // 需要调用的方法
        root.put("paraNames",  paraNames );  // 需要参数的方法名称
        
        
         
//      3. 加载模板文件  
        Template template = cfg.getTemplate("TestCase.ftl");   // 固定的
          
//      4. 整合数据 和 模板，输出到控制台  
        //Writer out = new OutputStreamWriter(System.out);   
        
        String dirPath =  classPath.substring( 0 , classPath.lastIndexOf("/") +1 );
        
        File newFP = new File(dirPath);
        
        newFP.mkdirs();
        File newFile = new File(classPath);
        newFile.createNewFile();
        Writer out  = new OutputStreamWriter(new FileOutputStream(newFile),"UTF-8");  
        template.process(root, out);  
        out.flush();  
        out.close();  
	}
	/**
	 * 生成用于填写参数的excel
	 * @param ms
	 * @return
	 */
	public static boolean createParamExcel(Class clazz ,Method ms ,String excelFilePath){
		
		 // 得到方法的返回值类型的类类型
//        Class<?> returnType = ms.getReturnType();
//        System.out.print(returnType.getName() + " ");
//        // 得到方法的名称
//        System.out.print(ms.getName() + "(");
//		 // 获取参数类型
//        Class[] paramTypes = ms.getParameterTypes();
//        for (Class class2 : paramTypes) {
//            System.out.print(class2.getName() + ",");
//        }
//        logger.info(")");
		
        try { 
        	ClassPool pool = ClassPool.getDefault();    
			CtClass cc = pool.get(clazz.getName());
			
			String modifier = Modifier.toString(ms.getModifiers()); 
			
			Class<?> returnType = ms.getReturnType();  
            String name = ms.getName();  
            
            parameterTypes = ms.getParameterTypes();  
            
            returnTypeName = returnType.getName() + "";
            System.out.print("方法-->"+modifier+" "+returnType.getName()+" "+name+" (");  
            
            //使用 javassist 反射查询出 参数的名称
            CtMethod ctm = cc.getDeclaredMethod(name);  
            MethodInfo methodInfo = ctm.getMethodInfo();  
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
            LocalVariableAttribute attribute = (LocalVariableAttribute)codeAttribute.getAttribute(LocalVariableAttribute.tag); 
            
            int pos = Modifier.isStatic(ctm.getModifiers()) ? 0 : 1;  
            for (int i=0;i<ctm.getParameterTypes().length;i++) {  
                //System.out.print(parameterTypes[i]+" "+attribute.variableName(i+pos));  
                System.out.print(attribute.variableName(i+pos));  // 参数名称
                
                paraNameList.add( attribute.variableName(i+pos) ); // 
                
                if (i<ctm.getParameterTypes().length-1) {  
                    System.out.print(",");  
                }  
            } 
            
            logger.info(")");
            System.out.println();
            
//            Class<?>[] exceptionTypes = ms.getExceptionTypes();  
//            if (exceptionTypes.length>0) {  
//               System.out.print(" throws ");  
//               int j=0;  
//               for (Class<?> cl:exceptionTypes) {  
//                   System.out.print(cl.getName());  
//                   if (j<exceptionTypes.length-1) {  
//                       System.out.print(",");  
//                   }  
//                   j++;  
//               }  
//            }
            paraNameList.add("expect_result");
            paraNameList.add("final_result");
            paraNameList.add("result");
            
            List<Map<String, Object>> listB = new ArrayList<Map<String, Object>>();
    		for (int t = 0; t < paraNameList.size() ; t++) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("paraName", paraNameList.get(t) );
    			listB.add(map);
    		}
    		
    		logger.info("=============导出Excel开始=============");
            ExportMapExcel.expExl(listB , excelFilePath ); // 
            logger.info("=============导出Excel结束=============");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}  
		
		return false;
	}
	
	public static Map<String,String> getMethodInfo(String ms) throws ClassNotFoundException{
        //com.xrzx.testcase.Calculator.add(java.util.ArrayList,String)
		Map<String,String> msMap = new HashMap<String,String>();
		String[] arrStr = ms.split("");
		
		String regx = "\\(.*\\)";
		
		//1.将正在表达式封装成对象Patten 类来实现 
		Pattern pattern = Pattern.compile(regx); 
		//logger.info(pattern);
		
		// 2.将字符串和正则表达式相关联 
		Matcher matcher = pattern.matcher(ms); 
		//3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。 
		//matcher.matches();
		//logger.info("是否含有方法--"+matcher.find()); 
		
		//查找符合规则的子串 
		if(matcher.find()){ 
			msMap.put("isHaveMethod", "true");
			
			msMap.put("getGroup", matcher.group()); //获取 字符串
			msMap.put("start", matcher.start()+""); //获取的字符串的首位置
			msMap.put("end", matcher.end()+"" ); //获取的字符串末位置
			//获取 字符串
			String pamString = matcher.group();
			logger.info("==============================================================================");
			logger.info("===================================格式化方法==================================");
			logger.info("==============================================================================");
			logger.info("方法参数----"+pamString);
			
			String className = ms.replace(pamString, "");
			String className1 = className.substring(0, className.lastIndexOf(".")); //1
			String className2 = className.substring(className.lastIndexOf(".")+1, className.length() ); //1
			
			logger.info("clazz名称---"+className1);
			msMap.put("className", className1 ); //获取测试 的类的名称
			
			logger.info("method名称--"+className2);
			msMap.put("methodName", className2 ); //获取测试 的方法的名称
			
			pamString = pamString.replace("(", "");
			pamString = pamString.replace(")", "");
			logger.info("方法参数去掉()后: "+pamString);
			
			if(null!= pamString && "".equals(pamString.trim())){
				msMap.put("paraIsNull","true");
				msMap.put("paraLength","0");
			}else{
				String[] para = pamString.split(","); //2
				msMap.put("paraIsNull","false");
				msMap.put("paraLength",para.length+"");
				
				logger.info("方法的参数长度:"+para.length);
				
				for(int i=0;i<para.length;i++){
					logger.info("方法参数"+i+"--"+para[i].trim());
					msMap.put("para"+i, para[i].trim() );
				}
			}
		}else{
			msMap.put("isHaveMethod", "false");
		}
		return msMap;
	}	
	
	public static boolean getDataType(String type){
		String[] typeArr = {"int","long","float","double","byte","short","char","boolean",
							"Integer","Long","Float","Double","Byte","Short","Character","Boolean"
				           };
		return Arrays.asList(typeArr).contains(type);
	}
	
	public static String upperCase(String str) {  
	    char[] ch = str.toCharArray();  
	    if (ch[0] >= 'a' && ch[0] <= 'z') {  
	        ch[0] = (char) (ch[0] - 32);  
	    }  
	    return new String(ch);  
	}
	
	@SuppressWarnings("rawtypes")
	public static Class getDataTypeClass(String type){
		Class returnType = null;
		if("int".equals(type)){
			returnType = int.class;
		}
		if("Integer".equals(type)){
			returnType = Integer.class;
		}
		if("double".equals(type) ){
			returnType = double.class;
		}
		if("Double".equals(type) ){
			returnType = Double.class;
		}
		if("float".equals(type)){
			returnType = float.class;
		}
		if("Float".equals(type)){
			returnType = Float.class;
		}
		if("long".equals(type)){
			returnType = long.class;
		}
		if("Long".equals(type)){
			returnType = Long.class;
		}
		if("byte".equals(type)){
			returnType = byte.class;
		}
		if("Byte".equals(type)){
			returnType = Byte.class;
		}
		if("short".equals(type)){
			returnType = short.class;
		}
		if("Short".equals(type)){
			returnType = Short.class;
		}
		if("char".equals(type)){
			returnType = char.class;
		}
		if("Character".equals(type)){
			returnType = Character.class;
		}
		if("boolean".equals(type)){
			returnType = boolean.class;
		}
		if("Boolean".equals(type)){
			returnType = Boolean.class;
		}
		return returnType;
	}
}
