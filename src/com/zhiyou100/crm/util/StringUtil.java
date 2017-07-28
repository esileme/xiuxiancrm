package com.zhiyou100.crm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static void main(String[] args) {

		transferToNomal("user_name_sex");
	}
	
	
	public static String transferToNomal(String param) {
		 if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       StringBuilder sb=new StringBuilder(param);  
	       Matcher mc= Pattern.compile("_").matcher(param);  
	       int i=0;  
	       while (mc.find()){ 
	    	   int end = mc.end();
	           int position=end-(i++); 
	           sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());  
	       }  
	       //System.out.println(sb.toString());
	       return sb.toString();  
		
	}

}
