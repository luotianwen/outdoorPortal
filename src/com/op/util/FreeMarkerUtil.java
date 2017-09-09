package com.op.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 自定义导入模板解析类
 * 渲染模板用
 * @author WinZhong
 *
 */
@Service("freeMarkerUtil")
public class FreeMarkerUtil {
	
	/** * Freemarker配置 */
	@Resource(name = "freemarkerConfig")
	private FreeMarkerConfigurer freemarkerConfigurer;

	/**
	 *渲染视图,将数据合并至模板中. 返回生成的字符串
	 *@param ftlPath 
	 * @param model 
	 * @return String 
	 * @throws
	 * Exception
	 */
	public String generateStringByTemplate(String ftlPath, Map<String, Object> model)throws Exception {
		Template template = null;
		StringWriter swriter = new StringWriter();
		Configuration configuration = freemarkerConfigurer.getConfiguration();
		template = configuration.getTemplate(ftlPath);
		template.process(model, swriter);
		return swriter.toString();
	}

    /** 
     *  
     * @param ftl 模板文件名,相对上面的模版根目录templates路径,例如/module/view.ftl templates/module/view.ftl 
     * @param data 填充数据 
     * @param targetFile 要生成的静态文件的路径,相对设置中的根路径,例如 "jsp/user/1.html" 
     * @param contextPath servlet上下文，用于服务器在根目录之下寻找文件夹生成.html文件
     * @return 
     */  
    public boolean createFile(String ftl, Object data, String targetFile ,String contextPath) {  
    	
        try {  
            // 创建Template对象  
            Configuration cfg = freemarkerConfigurer.getConfiguration();  
            Template template = cfg.getTemplate(ftl,"UTF-8");  
            
            // 生成静态页面  
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(contextPath+targetFile), "UTF-8"));  
            template.process(data, out);  
            out.flush();
            out.close();
        } catch (IOException e) {  
            e.printStackTrace();  
            return false;  
        } catch (TemplateException e) {  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }  

	public static void main(String[] args) throws Exception {
		Map m = new HashMap();
	    m.put("name", "管理员");
	    m.put("date", new Date());
		
		/*String template = "你好${name}，今天是${date?string('yyyy-MM-dd')}"; //变量参考freemarker语法  
	    Map m = new HashMap();
	    m.put("name", "管理员");
	    m.put("date", new Date());
	   log.info(freemarkerProcess(m, template));*/
		
	}
}
