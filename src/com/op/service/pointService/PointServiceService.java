package com.op.service.pointService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.pointService.auth.InsertPointAuthDTO;
import com.op.dto.pointService.evaluate.PointEvaluateInfo;
import com.op.dto.pointService.insert.fabu.PointServiceInfoDTO;
import com.op.dto.pointService.insert.ruzhu.InsertPointServiceDTO;
import com.op.dto.pointService.show.PointServiceImgDTO;
import com.op.dto.pointService.show.PointServiceProjectDTO;
import com.op.dto.pointService.show.PointServiceShowDTO;
import com.op.dto.pointService.update.point.PointServiceUpdateDTO;
import com.op.plugin.page.Page;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：地点服务Service
 * 创建人：Yan
 * 创建时间： 2016-6-20
 * modification list：
 * =============================================================
 */
@Service(value="pointServiceService")
public interface PointServiceService {

	/**
	 * 方法描述：新增地点服务
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	String insertReplyPointService(InsertPointServiceDTO dto) throws Exception;
	
	/**
	 * 方法描述： 发布项目/完善商户信息
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveProjectAndPointServiceInfo(PointServiceInfoDTO dto,String psp_update_id) throws Exception;
	
	/**
	 * 方法描述：发布项目认证校验
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void checkAuth(Map<String,String> map) throws Exception;
	
	/**
	 * 方法描述：添加商户认证数据
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void insertAuth(InsertPointAuthDTO dto)throws Exception;
	
	/**
	 * 方法描述：修改商户认证数据
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void updateAuth(InsertPointAuthDTO dto)throws Exception;
	
	/**
	 * 场馆展示
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	void pointServiceShow(String id,String ftl,String targetFile ,String contextPath) throws Exception;
	
	/**
	 * 项目收藏数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	String pointCollection(String id) throws Exception;
	
	/**
	 * 收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void addConllection(String id,String userId) throws Exception;
	
	/**
	 * 取消收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void cancelCollection(String id,String userId) throws Exception;
	
	/**
	 * 是否收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	boolean isCollection(String id,String userId) throws Exception;
	
	/**
	 * 场馆评论页面显示信息
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	PointEvaluateInfo pointEvaluate(String id) throws Exception;
	
	/**
	 * 场馆图片
	 * @param id
	 * @throws Exception
	 */
	List<PointServiceImgDTO> findPointServiceImg(String id) throws Exception;
	
	/**
	 * 查询场馆详细信息
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	PointServiceUpdateDTO findInsertPointServiceDTO(String id,String userId) throws Exception;
	
	/**
	 * 验证场馆创建人
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	boolean checkPointCreateUser(String id,String userId) throws Exception;
	
	/**
	 * 查询场馆描述信息
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	PointServiceInfoDTO findPointServiceInfoByPsId(String id) throws Exception;
	
	/**
	 * 商家所有项目展示
	 * @param id 商家ID
	 * @return
	 * @throws Exception
	 */
	List<PointServiceProjectDTO> findPointServiceProjectDTOByPsId(Page<String> page) throws Exception;
	
	/**
	 * 商家所有项目个人中心展示
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	List<PointServiceProjectDTO> findPointServiceProjectDTO(Page<Map<String,String>> page) throws Exception;
	
	/**
	 * 购买人数
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	String findPointPurchaseNumber(String id) throws Exception;
	
	/**
	 * 是否认证
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	boolean isAuth(String id) throws Exception;
	
	/**
	 * 场馆信息
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	List<PointServiceShowDTO> findPointService(String userId) throws Exception;
	
	/**
	 * 场馆认证信息
	 * @param id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	InsertPointAuthDTO findPointServiceAuth(String userId) throws Exception;
	
	/**
	 * 查询该用户名下所有场馆
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	List<PointServiceShowDTO> allPoint(String userId) throws Exception;
}
