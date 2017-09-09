package com.op.service.synQx.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.ShiroDTO;
import com.op.service.synQx.impl.AbstractFilterChainDefinitionsService;

/**
 * 项目名：outdoorPortal
 * 类描述：继承自抽象实现类，利用Spring注入该父类的原始系统资源节点，此类加载第三方资源
 * 创建人：Yan
 * 创建时间： 2015-11-7 上午11:28:46
 * 最后修改时间：2015年11月25日22:17:10
 */
public class LoadingDBChainDefinitions extends AbstractFilterChainDefinitionsService {
	
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：加载数据库资源配置
	 * 实现接口：@see com.op.service.synQx.impl.AbstractFilterChainDefinitionsService#initOtherPermission()
	 * @return
	 */
	@Override
	public Map<String, String> initOtherPermission() {
		Map<String,String> map = new HashMap<String,String>();
		try {
			System.out.println("-------------------------------加载第三方资源至shiro权限	开始-------------------------------------");
			List<ShiroDTO> shiroList = (List<ShiroDTO>) dao.findForList("opAuthorizationMapper.findAuthorizationForMenus", null);
			// 封装Map结构
			for (ShiroDTO dto : shiroList) {
				String path = dto.getPath();
				/*------------------重构资源路径--------------------------*/
				int indexOf = path.indexOf(".");
				
				// 修改访问路径通配符
				if(indexOf != -1){
					path = path.substring(0, indexOf)+"**";
				}else{
					path = path+"**";
				}
				// 根目录设置
				if(path.indexOf("/") != 0){
					path = "/"+path;
				}
				/*------------------重构资源路径--------------------------*/
				
				// 判断该资源是否已经对应了角色
				String roles = map.get(path);
				// 确定路径是否具有角色映射资源
				if(StringUtils.isEmpty(roles)){
					map.put(path, "authc,roleOrFilter[\""+dto.getRoleId()+"\"]");
				}else{
					// 如果该路径已经对应角色，向该路径添加下一个角色的对应关系(/controller/method** = authc,roleOrFilter["role1,role2"])
					roles = roles.substring(roles.indexOf("\"")+1, roles.lastIndexOf("\""));// 取出原来的角色字符串集合
					map.put(path, "authc,roleOrFilter[\""+roles+","+dto.getRoleId()+"\"]");// 重新封装
				}
			}
			System.out.println("-------------------------------加载第三方资源至shiro权限	结束-------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
		}
		return map;
	}

}
