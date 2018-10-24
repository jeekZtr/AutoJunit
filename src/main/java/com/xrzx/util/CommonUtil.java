package com.xrzx.util;

public class CommonUtil {
	//基本数据类型 和 其包装类
	private static String[] typeArr = { 
			"int","Integer","java.lang.Integer","Integer.parseInt",
			"long","Long","java.lang.Long","Long.parseLong",
			"float","Float","java.lang.Float","Float.parseFloat",
			"double","Double","java.lang.Double","Double.parseDouble",
			"byte","Byte","java.lang.Byte","Byte.parseByte",
			"short","Short","java.lang.Short","Short.parseShort",
			"char","Character","java.lang.Character","Character.valueOf",
			"boolean","Boolean","java.lang.Boolean","Boolean.valueOf"
	};
	// 获得转换函数
	public static String getTypeParse(String type){
		//System.out.println("=======================");
		//System.out.println(type);
		type = type.replaceAll("class ", "");
		//System.out.println(type);
		String  parseString = "";
		for(int i=0;i<typeArr.length;i++){
			if(typeArr[i].equals(type)){
				int indexTemp = i/4 ;
				int index = (indexTemp*4 + 3 ); 
				parseString = typeArr[index];
			}
		}
		return parseString.equals("")?type:parseString;
	}
	// 获得转换的数据类型
	public static String getType(String type){
		//System.out.println("=======================");
		type = type.replaceAll("class ", "");
		String  parseString = "";
		for(int i=0;i<typeArr.length;i++){
			if(typeArr[i].equals(type)){
				parseString = type;
			}
		}
		return parseString.equals("")?type:parseString;
	}
}
