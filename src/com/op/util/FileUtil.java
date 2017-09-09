package com.op.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;


/**
 * 文件处理工具类
 * @author WinZhong
 *
 */
public class FileUtil {

	
	
	/**
	 * base64字符串转化文件
	 * @param content Base64内容
	 * @param savePath  保存路径
	 * @param fileName  文件名字
	 * @return
	 * @throws IOException 
	 */
    public static boolean Base64TurnFile(String content,String savePath,String fileName) throws IOException{  
    		if (content == null){// 数据为空
    			return false;
    		}else{
    			// 检查目录
    	        File uploadDir = new File(savePath);
    			if (!uploadDir.exists()) {
    				uploadDir.mkdirs();
    			}
    			byte[] bs = Base64.decodeBase64(content);
    			FileOutputStream os = new FileOutputStream(savePath+"/"+fileName);
    			os.write(bs);
    			os.close();
    			return true;
    		}
    }
	
	
}
