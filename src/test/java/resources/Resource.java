package resources;

import java.io.IOException;
import java.net.URL;

public class Resource {
	public  void getResource() throws IOException{      
        //查找指定资源的URL，其中res.txt仍然开始的bin目录下     
	  URL fileURL=this.getClass().getResource("/digesterRules.xml");     
	  System.out.println(fileURL.getFile());    
	}    
	public static void main(String[] args) throws IOException {    
	  Resource res=new Resource();    
	  res.getResource();    
	}    
}
