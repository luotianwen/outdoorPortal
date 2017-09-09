package com.op.controller.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.op.controller.BaseController;
import com.op.util.DateUtil;
import com.op.util.aliyun.AliyunOSSFactory;
import com.op.util.aliyun.AliyunOSSProperties;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：PicturesController   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年12月2日 下午3:05:36   
* 修改人：Win Zhong   
* 修改时间：2015年12月2日 下午3:05:36   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="/oss")
public class OSSFileUploadController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 上传文件   返回OSS直传签名
	 */
	@RequestMapping(value="/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response){
		//判断用户是否登录，未登录不返回OSS直传签名
		if(this.getSessionUser() != null){
			//上传类型  图片：img   文件：file
			String type = this.getParameter("type");
	        Map<String, String> respMap = new LinkedHashMap<String, String>();
		    String dir = null;
		    if("img".equals(type)){
		    	dir = "img/"+DateUtil.getDays()+"/";
		    	 respMap.put("host", AliyunOSSProperties.IMGVISITURL);
		    }else{
		    	dir = "file/"+DateUtil.getDays()+"/";
		    	respMap.put("host", AliyunOSSProperties.FILEVISITURL);
		    }
	        OSSClient client = AliyunOSSFactory.getOSSClient();
	        try {
	        	long expire =  new Date().getTime() + 1000 * 60; // 1 minute to expire;
	            Date expiration = new Date(expire);
	            PolicyConditions policyConds = new PolicyConditions();
	            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
	            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
	
	            String postPolicy = client.generatePostPolicy(expiration, policyConds);
	            byte[] binaryData = postPolicy.getBytes("utf-8");
	            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
	            String postSignature = client.calculatePostSignature(postPolicy);
	            
	            String callbackStr = "{\"callbackUrl\":\""+AliyunOSSProperties.CALLBACKURL+"\","
	            				+"\"callbackHost\":\"www.wanrma.com\","
	            				+"\"callbackBody\":\"filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}\","
	            				+"\"callbackBodyType\":\"application/x-www-form-urlencoded\"}";
	            logger.info(callbackStr);
	            respMap.put("accessid", AliyunOSSProperties.ACCESSKEYID);
	            respMap.put("policy", encodedPolicy);
	            respMap.put("signature", postSignature);
	            respMap.put("dir", dir);
	            
	            //上传回调
	            respMap.put("callback",  BinaryUtil.toBase64String(callbackStr.getBytes()));
	            
	            respMap.put("expire", String.valueOf(expire / 1000));
	            JSONObject ja1 = JSONObject.fromObject(respMap);
	           log.info(ja1.toString());
	            response.setHeader("Access-Control-Allow-Origin", "*");
	            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
	            response(request, response, ja1.toString());
	            //client.shutdown(); 
	        } catch (Exception e) {
	           e.getMessage();
	        }
		}else{
			 try {
				response(request, response,"未登录");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}  
   private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
    		String callbackFunName = request.getParameter("callback");
    		response.setContentType("text/html; charset=UTF-8");
    		if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
    			response.getWriter().println(results);
    		else
    			response.getWriter().println(callbackFunName + "( "+results+" )");
    		response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
            response.getWriter().close();
   }
	
   /**
    * OSS文件上传回调验证
    * @param request
    * @param response
    * @throws NumberFormatException
    * @throws IOException
    */
   @RequestMapping(value="/verify")
	protected void verify(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException{
	   
	   String ossCallbackBody = GetPostBody(request.getInputStream(), Integer.parseInt(request.getHeader("content-length")));
		boolean ret = VerifyOSSCallbackRequest(request, ossCallbackBody);
		System.out.println("verify result:" + ret);
		System.out.println("OSS Callback Body:" + ossCallbackBody);
		
		Map<String, Object> map = this.findKeyMapByRequest(request);
		logger.info("OSS上传文件参数:" + map);
		boolean isAdopt = false;
		String msg = null;
		switch (map.get("mimeType").toString()) {
			//*.jpe *.jpeg *.jpg  文件格式
			case "image/jpeg":
				isAdopt = true;
			break;
			//*.png  文件格式
			case "image/png":
				isAdopt = true;
			break;
			//*.gif  文件格式
			case "image/gif":
				isAdopt = true;
			break;
			//*.mp3	文件格式
			case "audio/mp3":
				isAdopt = true;
				break;
			//*.mp3	文件格式
			case "audio/mpeg":
				isAdopt = true;
				break;
			//其他文件格式，直接删除，返回上传失败信息	
			default:
				//未被承认的或不受支持的文件类型！
				msg = "Unrecognized or unsupported file type!";
				isAdopt = false;
				// 初始化OSSClient
				OSSClient client = AliyunOSSFactory.getOSSClient();
				//删除文件
				client.deleteObject(AliyunOSSProperties.BUCKETNAME, map.get("filename").toString());
				break;
		}
		//String resultMsg = java.net.URLEncoder.encode("中文", "utf-8"); 
		if (ret && isAdopt){
			response(request, response, "{\"resultCode\":0,\"resultMsg\":\"OK\"}", HttpServletResponse.SC_OK);
		}
		else{
			if(null == msg){
				msg = "verify not ok";
			}
			response(request, response, "{\"resultCode\":400,\"resultMsg\":\""+msg+"\"}", HttpServletResponse.SC_BAD_REQUEST);
		}
	   
	}
	public String GetPostBody(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen - readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}
				return new String(message);
			} catch (IOException e) {
			}
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	protected boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) throws NumberFormatException, IOException
	{
		
	    /*Enumeration<String> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	       log.info(key+"="+value);
	    }*/
	    
	/*	boolean ret = false;	
		String autorizationInput = request.getHeader("authorization");*/
		String pubKeyInput = request.getHeader("x-oss-pub-key-url");
		/*System.out.println("autorizationInput======"+autorizationInput);
		System.out.println(ossCallbackBody+"pubKeyInput======"+pubKeyInput);
		byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);*/
		byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
		String pubKeyAddr = new String(pubKey);
		if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/"))
		{
			System.out.println("pub key addr must be oss addrss");
			return false;
		}
		/*String retString = executeGet(pubKeyAddr);
		retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
		retString = retString.replace("-----END PUBLIC KEY-----", "");
		retString = retString.replaceAll("(\r\n|\r|\n|\n\r)", "");  
		String queryString = request.getQueryString();
		String uri = request.getRequestURI();
		String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
		String authStr = decodeUri;
		if (queryString != null && !queryString.equals("")) {
			authStr += "?" + queryString;
		}
		authStr += "\n" + ossCallbackBody;
		System.out.println(authStr.trim());
		System.out.println(authorization == null);
		System.out.println(retString);
		ret = doCheck(authStr.trim(), authorization, retString);
		
		
		logger.info("RSA验证结果:"+ret);*/
		/*return ret;*/
		
		return true;
	}
	@SuppressWarnings({ "finally" })
	public String executeGet(String url) {
		BufferedReader in = null;

		String content = null;
		try {
			// 定义HttpClient
			CloseableHttpClient  client = HttpClients.createDefault();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			CloseableHttpResponse  response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
			 
            response.close();
            client.close();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}
	public static boolean doCheck(String content, byte[] sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes());
			boolean bverify = signature.verify(sign);
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private void response(HttpServletRequest request, HttpServletResponse response, String results, int status) throws IOException {
		String callbackFunName = request.getParameter("callback");
		response.addHeader("Content-Length", String.valueOf(results.length()));
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(status);
		response.flushBuffer();
	}
 
}
