package com.op.controller.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.op.controller.BaseController;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.FileUpload;
import com.op.util.UuidUtil;

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
public class FileUploadController extends BaseController {
	
	//@Resource(name="fileUploadService")
	//private FileUploadService fileUploadService;
	
	String savePath;
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value="/fileUpload")
	@ResponseBody
	public Object fileUpload(@RequestParam(required=false) MultipartFile file,HttpServletRequest request) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();;
			String  day = DateUtil.getDays(), fileName = "";
	        ServletContext application = request.getSession().getServletContext();
	        String savePath = application.getRealPath("/") + "upload/temp/"+day;
	        String saveUrl = "upload/temp/"+day+"/";
	        // 检查目录
	        File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
		 
			if (null != file && !file.isEmpty()) {
			 	//文件上传路径
				fileName = FileUpload.fileUp(file, savePath, UuidUtil.get32UUID());				//执行上传
				//加水印
				//ImageMarkLogoUtil.markImageByText(" 仲成 \r\n\r\n www.winzhong.net", savePath+"/"+fileName, savePath+"/"+fileName, -45);  
				map.put("src", saveUrl+fileName);
				map.put("result", "true");
				//session.setAttribute(key, uploadFilesList);
			}else{
				System.out.println("上传文件出错！");
				map.put("msg", "上传文件出错！");
				map.put("result", "err");
			}
		 
			
		 
		 
			
		return map;
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value="/fileUploadTest")
	@ResponseBody
	public Object fileUploadTest(@RequestParam(required=false) MultipartFile file,HttpServletRequest request,HttpSession session) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();;
			String key = request.getParameter("key");
			List<String> uploadFilesList = (List<String>)session.getAttribute(key);;
			if(StringUtils.isEmpty(uploadFilesList)){
				uploadFilesList = new ArrayList<String>();
			}else{
				for(String str:uploadFilesList){
					System.err.println("---《src="+str+"》---");
				}
			}
			System.err.println("------------《key="+key+"》--------------");
			String  day = DateUtil.getDays(), fileName = "";
	        ServletContext application = request.getSession().getServletContext();
	        String savePath = application.getRealPath("/") + "upload/temp/"+day;
	        String saveUrl = "upload/temp/"+day+"/";
	        // 检查目录
	        File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
		 
			if (null != file && !file.isEmpty()) {
			 	//文件上传路径
				fileName = FileUpload.fileUp(file, savePath, UuidUtil.get32UUID());				//执行上传
				uploadFilesList.add(saveUrl+fileName);
				map.put("fileName", fileName);
				map.put("result", "true");
				//session.setAttribute(key, uploadFilesList);
			}else{
				System.out.println("上传文件出错！");
				map.put("msg", "上传文件出错！");
				map.put("result", "err");
			}
		 
			//加水印
			//Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
		 
		 
			
		return map;
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value="/headUpload")
	@ResponseBody
	public Object headUpload(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();;
		String  day = DateUtil.getDays(), fileName = UuidUtil.get32UUID()+".png";
		ServletContext application = request.getSession().getServletContext();
        String savePath = application.getRealPath("/") + "upload/temp/"+day;
        String saveUrl = "upload/temp/"+day+"/";
		String result = this.getParameter("image");         
		if(Base64TurnPicture(result,savePath,fileName)){
			map.put("src", saveUrl+fileName);
			map.put("result", "true");
		}else{
			map.put("result", "err");
		}
		return map;
	}
	
	//base64字符串转化成图片  
    public boolean Base64TurnPicture(String content,String savePath,String fileName){   
    	if (content == null) // 图像数据为空
    		return false;
    	//System.out.println("========"+content);
		try {
			// 检查目录
	        File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			byte[] bs = Base64.decodeBase64(content.split(",")[1]);
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
	
	
	/**
	 * 删除
	 */
	/*@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Pictures");
		PageData pd = new PageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){
				pd = this.getPageData();
				DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("PATH")); //删除图片
				picturesService.delete(pd);
			}
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}*/
	
	/**
	 * 修改
	 */
	/*@RequestMapping(value="/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile file,
			@RequestParam(value="tpz",required=false) String tpz,
			@RequestParam(value="PICTURES_ID",required=false) String PICTURES_ID,
			@RequestParam(value="TITLE",required=false) String TITLE,
			@RequestParam(value="MASTER_ID",required=false) String MASTER_ID,
			@RequestParam(value="BZ",required=false) String BZ
			) throws Exception{
		logBefore(logger, "修改Pictures");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			pd.put("PICTURES_ID", PICTURES_ID);		//图片ID
			pd.put("TITLE", TITLE);					//标题
			pd.put("MASTER_ID", MASTER_ID);			//属于ID
			pd.put("BZ", BZ);						//备注
			
			if(null == tpz){tpz = "";}
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
				pd.put("PATH", ffile + "/" + fileName);				//路径
				pd.put("NAME", fileName);
			}else{
				pd.put("PATH", tpz);
			}
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
			picturesService.edit(pd);				//执行修改数据库
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}*/
	

	
	/**
	 * 方法描述：上传各种文件，自带校验
	 * 返回类型：PageData
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
    public void uploadBrand(@RequestParam(value="file") MultipartFile file
    		,HttpServletRequest request
    		,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 未登录判断
		if(request.getSession().getAttribute(Const.SESSION_USER) == null){
			try {
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "no_login");
				response.getWriter().write(JSONObject.fromObject(map).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		// 默认返回true
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		// 当前日期
		String ffile = DateUtil.getDays();
		// 文件原始名字
		String fileName = file.getOriginalFilename();
		// Servlet上下文
        ServletContext application = request.getSession().getServletContext();
        // 本地存储路径
        String locationPath =  "upload/temp/";
        // 保存绝对路径
        String savePath = application.getRealPath("/")+locationPath;
        
        // 标识是否已经出错
        boolean is_error = false;
        
		if (null != file && !file.isEmpty()) {
			// 文件大小验证
			String maxSize = request.getParameter("maxSize");
			if(!StringUtils.isEmpty(maxSize)){
				long maxSizeLong = Long.parseLong(maxSize);
				if(file.getSize() > maxSizeLong){
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "超出了规定的文件大小。\n请上传大小不超过"+(maxSizeLong/1024)+"k的文件");
					is_error = true;
				}
			}
			
			// 后缀验证
			String ext = "jpg,png,gif,jpeg";//;request.getParameter("ext");
			if(!StringUtils.isEmpty(ext) && !is_error){
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.asList(ext.split(",")).contains(fileExt)){
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "上传文件扩展名是不允许的扩展名。\n只允许" + ext + "格式。");
					is_error = true;
				}
				
				// 文件名称长度
				String nameMaxLength = request.getParameter("nameMaxLength");
				if(!StringUtils.isEmpty(nameMaxLength) 
						&& fileName.indexOf(".") > Integer.parseInt(nameMaxLength)
						&& !is_error){
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "图片名字过长，请修改后重新上传，建议长度不超过20。");
					is_error = true;
				}
			}
			try {
				if(!is_error){
					// 是否生成新的名称
					if(Boolean.parseBoolean(request.getParameter("isGeneraFileName"))){
						// 截取文件后缀
						String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
						// 生成新的名称
						StringBuffer generaNewName = new StringBuffer(Long.toString(new Date().getTime()));
						// 添加文件后缀
						generaNewName.append(fileExt);
						// 赋值新的名称
						fileName = generaNewName.toString();
					}
					// 创建上传文件对象
					File uploadedFile = new File(savePath+ffile,fileName);
					// 文件原始名称
					map.put("fileName", file.getOriginalFilename());
					// 本地保存路径
					map.put("savePath", locationPath+ffile+"/"+fileName);
					// 检查目录是否存在，不存在则创建
					if(!uploadedFile.exists()){
						uploadedFile.mkdirs();  
			        }
					file.transferTo(uploadedFile);//执行上传
					// 检测规定的宽高
					String width = request.getParameter("width"),height=request.getParameter("height");
					if(!StringUtils.isEmpty(width) 
							&& !StringUtils.isEmpty(height)){
						BufferedImage  image = ImageIO.read(uploadedFile);
						int widthInt = Integer.parseInt(width);
						int heightInt = Integer.parseInt(height);

						// 像素比较类型，默认是等于
						String pixelType = request.getParameter("pixelType");
						if(StringUtils.isEmpty(pixelType)){
					        if(image.getWidth() != widthInt || image.getHeight() != heightInt){
					        	uploadedFile.delete();
					        	map.put(Const.RESPONSE_STATE, 500);
					        	map.put(Const.ERROR_INFO, "为了展示更好的效果。<br>请上传宽度等于"+width+"，高度等于"+height+"的图片。");
					        	is_error = true;
						    }
						}
						// 上传的图片要大于此像素
						else if(">".equals(pixelType)){
					        if(image.getWidth() < widthInt || image.getHeight() < heightInt){
					        	uploadedFile.delete();
					        	map.put(Const.RESPONSE_STATE, 500);
					        	map.put(Const.ERROR_INFO, "为了展示更好的效果。<br>请上传宽度不小于"+width+"，高度不小于"+height+"的图片。");
					        	is_error = true;
						    }
						}
					}
				}
			} catch (Exception e) {
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "存储文件异常,请稍后重试!");
			}
		}else{
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "获取文件异常,请稍后重试!");
		}
		
		response.setContentType("text/html");
		try {
			response.getWriter().write(JSONObject.fromObject(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	/**
	 * 上传文件
	 */
	@RequestMapping(value="/uploadImg")
	@ResponseBody
	public Map<String,Object> uploadImg(HttpServletRequest request){
		Map<String,Object> res = new HashMap<String,Object>();
		
		// 当天的文件夹名
		String day = DateUtil.getDays();
		
		// 文件名称
		String fileName = Long.toString(new Date().getTime())+".png";
		
		// servlet上下文
		ServletContext application = request.getSession().getServletContext();
		
		// 绝对路径
        savePath = application.getRealPath("/") + "upload/temp/"+day;
        
        // 保存地址，用于返回生成图片
        String saveUrl = "upload/temp/"+day+"/";
        
        // 获取图片
		String dataURL = request.getParameter("dataUrl");
		
		// base64字符串转化成图片  
		if(Base64TurnPicture(dataURL,savePath,fileName)){
			res.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			res.put("savePath", saveUrl+fileName);
		}else{
			res.put(Const.RESPONSE_STATE, "500");
			res.put(Const.ERROR_INFO, "上传失败，请稍后重试或联系管理员，给您带来的不便敬请谅解！");
		}
		return res;
	}
	
	
}
