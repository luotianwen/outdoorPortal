package com.op.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HtmlCombineJs {

	
	public static void main(String[] args) throws Exception{
		String  filePath= "E:/a.txt";
		InputStreamReader read = new InputStreamReader (new FileInputStream(filePath),"utf8");
		BufferedReader bw = new BufferedReader(read);

		String line = bw.readLine(),str="var str=";
		while (line != null) {
			if(!"".equals(line.trim())){
				str+="'"+line.trim()+"'";
				
				if((line=bw.readLine()) != null){
					str += "\n+";
				}else{
					str += ";";
				}
			}else{
				line=bw.readLine();
			}
			
		}
		System.out.println(str);
		System.exit(0);
	}
}
