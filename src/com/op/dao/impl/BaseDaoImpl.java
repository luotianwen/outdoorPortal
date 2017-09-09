package com.op.dao.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.op.dao.BaseDao;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：DaoSupport   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年11月4日 下午2:31:56   
* 修改人：Win Zhong   
* 修改时间：2016年3月11日16:34:48
* 修改备注：   
* @version    
*
 */
@Repository("baseDaoImpl")
public class BaseDaoImpl implements BaseDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object save(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}
	
	/**
	 * 批量更新
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchSave(String str, List objs )throws Exception{
		return sqlSessionTemplate.insert(str, objs);
	}
	
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}

	/**
	 * 批量更新
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchUpdate(String str, List objs )throws Exception{
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		//批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
		try{
			if(objs!=null){
				for(int i=0,size=objs.size();i<size;i++){
					sqlSession.update(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 批量更新
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchDelete(String str, List objs )throws Exception{
		return sqlSessionTemplate.delete(str, objs);
	}
	
	/**
	 * 删除对象 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}
	 
	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}
	 
	/**
	 * 查找对象
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findForObject(String str) throws Exception {
		return sqlSessionTemplate.selectOne(str);
	}

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}	
	
	/**
	 * 查找对象
	 * @param str
	 * @return
	 * @throws Exception
	 */	
	@Override
	public Object findForList(String str) throws Exception {
		return sqlSessionTemplate.selectList(str);
	}
	
	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
		return sqlSessionTemplate.selectMap(str, obj, key);
	}
	 
}


