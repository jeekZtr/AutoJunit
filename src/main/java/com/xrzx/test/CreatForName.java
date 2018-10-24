package com.xrzx.test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class CreatForName {
	public static  void  main(String args[]){
		String className = "";
		String mothdName = "";
		String arg3 = "";
		String arg4 = "";
		System.out.println("as发送到发送到");
		try {
			System.out.println("as发送到发送到1231241231");
			CreatForName.CreateTestCaseFile();
			System.out.println("as发送到发送到999999999");
			System.out.println("as发送到发送到");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void  CreateTestCaseFile() throws Throwable{
		try {
			
			 //通过反射获取Class对象  
	        Class CalClass = Class.forName(getValue("className"));//"cn.fanshe.Student"  
	        //2获取show()方法  
	        Method m = CalClass.getMethod(getValue("methodName")  );//show  
	        System.out.println(m);
	        
	        Method[] ms =  CalClass.getDeclaredMethods();
	        for(int i=0;i<ms.length ; i++){
	        	System.out.println(ms[i]);
	        }
	        
	        //3.调用show()方法  
	        m.invoke( CalClass.getConstructor().newInstance() );  
	          
			//clazz.
			//需要的参数
			//Class  clazz = Class.forName("com.xrzx.testcase.Calculator");
	        
	        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("生成测试用例文件失败.........");
		}
	}
	
    //此方法接收一个key，在配置文件中获取相应的value  
    public static String getValue(String key) throws IOException{  
        Properties pro = new Properties();//获取配置文件的对象  
        FileReader in = new FileReader("pro.txt");//获取输入流  
        pro.load(in);//将流加载到配置文件对象中  
        in.close();  
        return (pro.getProperty(key)).trim();//返回根据key获取的value值  
    }  
}
