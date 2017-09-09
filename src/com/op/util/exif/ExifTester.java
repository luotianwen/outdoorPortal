package com.op.util.exif;
import java.io.File;
import java.util.Collection;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：ExifTester   
* 类描述：   测试用于读取图片的EXIF信息
* 创建人：Win Zhong   
* 创建时间：2015年12月14日 下午2:43:51   
* 修改人：Win Zhong   
* 修改时间：2015年12月14日 下午2:43:51   
* 修改备注：   
* @version    
*
 */
public class ExifTester {
	 public static void main(String[] args) throws Exception {
		  File mFile = new File("D:\\3.png");
		 
	        Metadata metadata = ImageMetadataReader.readMetadata(mFile);  
	        for (Directory directory : metadata.getDirectories()) {  
	        	for (Tag tag : directory.getTags()) {  
	                String tagName = tag.getTagName();  
	                if (tagName.equals("GPS Altitude")) {  
	                    //海拔  
	                	System.out.println("海拔   : " + tag.getDescription());  
	                } else if (tagName.equals("GPS Latitude")) {  
	                    //纬度  
	                	System.out.println("纬度 : " + pointToLatlong(tag.getDescription()));  
	                } else if (tagName.equals("GPS Longitude")) {  
	                    //经度  
	                	System.out.println("经度: " + pointToLatlong(tag.getDescription()));  
	                }  
	            }  
	            if("ExifSubIFDDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )){  
	  
	                //光圈F值=镜头的焦距/镜头光圈的直径  
	               System.out.println("光圈值: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER) );  
	  
	               System.out.println("曝光时间: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "秒" );  
	               System.out.println("ISO速度: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );  
	               System.out.println("焦距: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米" );  
	               System.out.println("拍照时间: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );  
	               System.out.println("宽: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );  
	               System.out.println("高: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );   
	                 
	            }  
	  
	            if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){  
	               System.out.println("照相机制造商: " + directory.getString(ExifIFD0Directory.TAG_MAKE) );  
	               System.out.println("照相机型号: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );  
	               System.out.println("水平分辨率: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );  
	               System.out.println("垂直分辨率: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );    
	            }  
	            
	        }  
	        Metadata meta = JpegMetadataReader.readMetadata(mFile);  
	        GpsDirectory gps = meta.getDirectory(GpsDirectory.class);  
	        if (gps != null) {  
	            Collection<Tag> tags = gps.getTags();  
	            for (Tag tag : tags) {  
	               System.out.println(tag);  
	            }  
	        }  
	 
	 
	 }
	 
	 
	   /** 
	     * 经纬度转换  度分秒转换 
	     * @param point 坐标点 
	     * @return 
	     */ 
	    public static String pointToLatlong (String point ) {  
	        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());  
	        Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());  
	        Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());  
	        Double duStr = du + fen / 60 + miao / 60 / 60 ;  
	        return duStr.toString();  
	    } 
}