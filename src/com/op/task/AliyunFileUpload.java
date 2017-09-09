package com.op.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.op.entity.lines.LineImages;
import com.op.service.lines.LineImagesService;
import com.op.util.aliyun.AliyunOSSFactory;
import com.op.util.aliyun.AliyunOSSProperties;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：AliyunFileUpload   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月19日 上午10:33:31   
* 修改人：Win Zhong   
* 修改时间：2015年12月19日 上午10:33:31   
* 修改备注：   
* @version    
*
 */
public class AliyunFileUpload{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "lineImagesServiceImpl")
	LineImagesService lineImagesService;

	/*@Scheduled(cron = "0 0/30 *  * * ? ")// 每30分钟执行一次
	@Override*/
	public void UploadLinesImages() {

		String src = this.getClass().getResource("").getPath();
		src = src.substring(1, src.indexOf("WEB-INF"));
		logger.info("本地项目根路径："+src);
		logger.info("*************《每30分钟执行一次 ,上传线路图片到阿里云OSS 》***************");
		//判断服务器系统
		if(System.getProperties().getProperty("os.name").indexOf("Windows") == -1){
			src = "/"+src ;
		}
		List<LineImages> uploadSuccess = new ArrayList<LineImages>();
		try {
			List<LineImages> li = lineImagesService.notUploadOSSImages();
			// 初始化OSSClient
			OSSClient client = AliyunOSSFactory.getOSSClient();
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			File file;
			InputStream content;
			for (LineImages img : li) {
				try {
					// 获取指定文件的输入流
					file = new File(src + img.getLi_local_url());
					//判断文件是否存在 存在则上传
					if(file.exists()){
						content = new FileInputStream(file);
						// 必须设置ContentLength
						meta.setContentLength(file.length());
						// 上传Object.
						PutObjectResult result = client.putObject(AliyunOSSProperties.BUCKETNAME, "lineImages/"+file.getName(),content, meta);
						// 打印ETag
						//System.out.println(result.getETag());
						logger.info("线路图片文件上传阿里云OSS云存储成功,文件 id:"+img.getLi_id()+"文件ETag："+result.getETag());
						img.setLi_aliyun_url(AliyunOSSProperties.IMGVISITURL + "lineImages/"+file.getName());
						uploadSuccess.add(img);
					}else {
						logger.info("不存在的线路图片文件 id:"+img.getLi_id()+"  路径："+src + img.getLi_local_url());
					}
				} catch (OSSException e) {
					switch (e.getErrorCode()) {
						case "AccessDenied":
							logger.error("阿里云OSS云存储服务器拒绝访问："+e.getErrorMessage());
							break;
						case "EntityTooLarge":
							logger.error("文件上传阿里云OSS云存储 实体过大："+e.getErrorMessage());
							break;
						case "EntityTooSmall":
							logger.error("文件上传阿里云OSS云存储 实体过小："+e.getErrorMessage());
							break;
						case "FileGroupTooLarge":
							logger.error("文件上传阿里云OSS云存储 文件组过大："+e.getErrorMessage());
							break;
						case "InvalidArgument":
							logger.error("文件上传阿里云OSS云存储 参数格式错误："+e.getErrorMessage());
							break;
						case "InvalidObjectName":
							logger.error("文件上传阿里云OSS云存储 无效的Object名字："+e.getErrorMessage());
							break; 
						default:
							logger.error("文件上传阿里云OSS云存储错误，错误代码："+e.getErrorCode()+"\n"+e.getErrorMessage());
							break;
					}
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (uploadSuccess.size() > 0) {
				lineImagesService.updateUploadOSSState(uploadSuccess);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
