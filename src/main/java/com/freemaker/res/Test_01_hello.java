package com.freemaker.res;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
 *  入门程序  
 */  
public class Test_01_hello {  
      
    @Test  
    public void test_1() throws Exception{  
//      1. 创建配置类  
        Configuration cfg = new Configuration();  
//          1.1 设置模板加载目录  
        File file = new File("test/resources/templates");
        System.out.println(file.getName());
        System.out.println(file.isDirectory());
        System.out.println(file.list()[0]);
        
        cfg.setDirectoryForTemplateLoading(file);  
        
//          1.2 设置编码  
        cfg.setDefaultEncoding("UTF-8");  
//          1.3 设置模板更新延迟时间  
        cfg.setTemplateUpdateDelay(0);  
          
//      2. 创建数据模型:模型数据一般用一个map 来构建  
        Map<String,Object> root = new HashMap<String,Object>();  
        root.put("title", "Hello World");  
        root.put("name", "键盘上的幽灵");  
        root.put("date", new Date());  
//      3. 加载模板文件  
        Template template = cfg.getTemplate("hello.ftl");  
          
//      4. 整合数据 和 模板，输出到控制台  
        Writer out = new OutputStreamWriter(System.out);   
        template.process(root, out);  
        out.flush();  
        out.close();  
    }  
    
    
    public static void main(String[] args) {  
        try {  
            //创建一个合适的Configration对象  
            Configuration configuration = new Configuration();  
            configuration.setDirectoryForTemplateLoading(new File("D:\\project\\webProject\\WebContent\\WEB-INF\\template"));  
            configuration.setObjectWrapper(new DefaultObjectWrapper());  
            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
            //获取或创建一个模版。  
            Template template = configuration.getTemplate("static.html");  
            Map<String, Object> paramMap = new HashMap<String, Object>();  
            paramMap.put("description", "我正在学习使用Freemarker生成静态文件！");  
              
            List<String> nameList = new ArrayList<String>();  
            nameList.add("陈靖仇");  
            nameList.add("玉儿");  
            nameList.add("宇文拓");  
            paramMap.put("nameList", nameList);  
              
            Map<String, Object> weaponMap = new HashMap<String, Object>();  
            weaponMap.put("first", "轩辕剑");  
            weaponMap.put("second", "崆峒印");  
            weaponMap.put("third", "女娲石");  
            weaponMap.put("fourth", "神农鼎");  
            weaponMap.put("fifth", "伏羲琴");  
            weaponMap.put("sixth", "昆仑镜");  
            weaponMap.put("seventh", null);  
            paramMap.put("weaponMap", weaponMap);  
              
            Writer writer  = new OutputStreamWriter(new FileOutputStream("success.html"),"UTF-8");  
            template.process(paramMap, writer);  
              
            System.out.println("恭喜，生成成功~~");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
          
    }  
  
}  
