package com.op.dao;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：DAO   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年11月4日 下午2:31:38   
* 修改人：Win Zhong   
* 修改时间：2016年3月11日16:34:58
* 修改备注：   
* @version    
*
 */
public interface BaseDao {
	
	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object save(String str, Object obj) throws Exception;
	
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception;
	
	/**
	 * 删除对象 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception;

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception;
	
	/**
	 * 查找对象
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str) throws Exception;
	
	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception;
	
	/**
	 * 查找对象
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str) throws Exception;
	
	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String sql, Object obj, String key , String value) throws Exception;
	
}
