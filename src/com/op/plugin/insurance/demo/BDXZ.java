package com.op.plugin.insurance.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.aliyun.oss.OSSClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.channel.api.model.common.CommonResult;
import com.hzins.channel.api.model.req.InsureDownloadReq;
import com.hzins.channel.api.model.resp.InsureDownloadResp;
import com.hzins.openapi.client.Configure;
import com.hzins.openapi.client.OpenApiRemoteOperation;
import com.hzins.openapi.client.common.ProxyFactory;
import com.op.plugin.insurance.BaoXianProperties;
import com.op.util.DateUtil;
import com.op.util.Tools;
import com.op.util.aliyun.AliyunOSSFactory;
import com.op.util.aliyun.AliyunOSSProperties;
/**
 * 保单下载
 * @author WinZhong
 *
 */
public class BDXZ {

    static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);
    
    ObjectMapper objectMapper=new ObjectMapper();
    
     
    
    public static void main(String[] args) throws JsonProcessingException {
		
		// 流水号
		String transNo = "WM" + DateUtil.getTimes() + Tools.getRandomNum();
		InsureDownloadReq req = new InsureDownloadReq();
		//transNo	String	必填	交易流水号，每次请求不能相同
		req.setTransNo(transNo);
		//partnerId	int	必填	渠道商身份标识，由慧择指定
		req.setPartnerId(BaoXianProperties.partnerId);
		//insureNum	String	必填	投保单号
		req.setInsureNum("20161215013107");
		
		
		Configure.Channel.channelKey = BaoXianProperties.channelKey;
		Configure.Channel.partnerId = BaoXianProperties.partnerId;
		//sign	String	必填	签名，预签名数据：密钥+渠道商身份标识+交易流水号+方案代码
		System.out.println("流水号："+transNo);
		CommonResult<InsureDownloadResp> res = operation.download(req);
		InsureDownloadResp resp = res.getData();
		
		System.out.println(resp.transNo)	;//String	交易流水号，与请求报文的流水号一致
		System.out.println(resp.fileName)	;//String	文件名称
		System.out.println(resp.fileSize)	;//Long	文件大小 单位：字节(B)
		System.out.println(resp.contentType)	;//String	文件类型，默认application/pdf
		//System.out.println(resp.data)	;//String	文件流内容，采用Base64编码
		BDXZ.Base64TurnPDF(resp.data, "E:/", resp.fileName);
		
		OSSClient ossClient = AliyunOSSFactory.getOSSClient();
		// 上传
		byte[] content = Base64.decodeBase64(resp.data.getBytes());
		ossClient.putObject(AliyunOSSProperties.BUCKETNAME,  "policy/"+resp.fileName, new ByteArrayInputStream(content));
		// 关闭client
		ossClient.shutdown();
    }

	//base64字符串转化成PDF
    public static boolean Base64TurnPDF(String content,String savePath,String fileName){   
    	if (content == null) // 数据为空
    		return false;
		try {
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    
}
