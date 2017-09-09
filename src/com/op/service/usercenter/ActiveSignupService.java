package com.op.service.usercenter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.op.dto.activity.activeSignUpPay.ActiveSignUpPay;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.signUp.ActiveAndSignUpDTO;
import com.op.dto.signUp.InsertSignUpDTO;
import com.op.dto.usercenter.leader.activeManager.activeRefund.LeaderActiveRefundsDTO;
import com.op.dto.usercenter.leader.activeManager.activeRefund.LeaderSearchActiveRefundDTO;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.LeaderSignUp;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.MySignUp;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.SignUpList;
import com.op.entity.activity.Activity;
import com.op.entity.alipay.Alipay;
import com.op.entity.usercenter.ActiveSignup;
import com.op.entity.usercenter.ActiveSignupInfo;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;

/**
 * 项目名：outdoorPortal
 * 类描述：活动报名接口
 * 创建人：Yan
 * 创建时间： 2015-12-16 上午11:15:04
 * 最后修改时间：2015-12-16上午11:15:04
 */
@Service("activeSignupService")
public interface ActiveSignupService {
	/**
	 * 我的活动(报名的活动或者发布的活动)或者退款管理
	 */
	List<ActiveSignupInfo> selectActive(Page<ActiveSignupInfo> page) throws Exception;
	/**
	 * 查询报名信息
	 */
	Map<String, Object> selectSignupInfo(Map<String, Object> map) throws Exception;
	/**
	 * 查询是否存在传入的订单id
	 */
	int selectOrderId(Map<String, Object> map) throws Exception;
	/**
	 * 修改状态
	 */
	void updateActive(Map<String, Object> map) throws Exception;
//	/**
//	 * 报名详情
//	 */
//	ActiveSignupInfo selectDetails(Map<String, Object> map) throws Exception;
	/**
	 * 方法描述：报名添加
	 * 返回类型：void
	 * @param signUp 报名人信息
	 * @param fullPay 预付款/全款标识（0：预付款；1：全款；）
	 * @param activeId 活动ID
	 * @param map ajax提示信息
	 * @throws Exception
	 */
	public void insertActiveSignUp(InsertSignUpDTO dto,int fullPay,int activeId,Map<String,Object> map,HttpSession session,String notes) throws Exception;
	
	/**
	 * 方法描述：根据订单ID查询活动信息
	 * 返回类型：ActiveSignup
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ActiveAndSignUpDTO findActiveSignUpById(String asu_id) throws Exception;
	
	
	/**
	 * 方法描述：
	 * 返回类型：boolean
	 * @param a 活动
	 * @param map 返回提示信息
	 * @param num 报名人数
	 * @param cd 当前时间
	 * @return
	 */
	public boolean checkActivity(Activity a,Map<String, Object> map,int num,Date cd);
	
	/**
	 * 方法描述：领队报名退款订单
	 * 返回类型：List<LeaderActiveRefundsDTO>
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<LeaderActiveRefundsDTO> leaderRefunds (Page<LeaderSearchActiveRefundDTO> page) throws Exception;
	
	
	/**
	 * 方法描述：领队设置退款订单的退款金额和退款备注
	 * @param asu_active_id	活动ID
	 * @param asu_id	订单ID
	 * @param asu_refund_price	退款金额
	 * @param asu_leader_refund_area	退款备注
	 * @throws Exception
	 */
	public void updateRefundOfLeader(Map<String,Object> map) throws Exception;
	
	/** -------------------------------------------        分割线                ---------------------------------------- */
	
	
	
	
	/**
	 * 新增订单
	 * @param activeSignup 订单实体类
	 */
	String saveOrder(ActiveSignup activeSignup,QueryActivity queryActivity,Map<String,Object> map,Users users) throws Exception;
	
	/**
	 * 付款
	 * @param asu_id 订单ID
	 */
	ActiveSignUpPay findActivitySignUpPay(String asu_id) throws Exception;
	
	/**
	 * 方法描述：修改支付订单信息
	 * 返回类型：void
	 */
	public void updateActiveSignUp(Alipay ap) throws Exception;
	
	/**
	 * 方法描述：根据支付流水号查询该订单数据，处理支付宝异步通知修改支付信息
	 * 返回类型：List<String>
	 * @param out_trade_no	商户订单号
	 * @return
	 * @throws Exception
	 */
	public List<ActiveSignUpPay> findAsuIdByAccountNum(String out_trade_no) throws Exception;
	
	/**
	 * 方法描述：根据活动ID查询报名列表
	 * 返回类型：List<SignUpList>
	 * @param activityId	活动ID
	 * @return
	 * @throws Exception
	 */
	public List<SignUpList> findSignUpByActivityId(Page<String> page) throws Exception;
	
	/**
	 * 我的订单
	 * @param page 分页
	 */
	public List<MySignUp> findMySignUpByUserId(Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 我的订单数量
	 * @param userId 用户ID
	 */
	public Map<String,String> countMySignUpByUserId(String userId) throws Exception;
	
	/**
	 * 用户取消订单
	 * @param userId 用户ID
	 * @param asu_id 订单ID
	 * @throws Exception
	 */
	public Map<String,String> updateCancelSignUpForUser(String userId,String uName,String asu_id,String aa_id,String aa_refund_area) throws Exception;
	
	/**
	 * 方法描述：领队查询所有活动
	 * 返回类型：List<LeaderActiveSignUpsDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<LeaderSignUp> leaderActiveManagerPage(Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 方法描述：领队活动数量
	 */
	public Map<String,String> leaderActiveManagerCount(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：领队查询该活动报名详情
	 * 返回类型：List<LeaderActiveSignUpsDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<MySignUp> leaderActiveSignupManagerPage(Page<MySignUp> page) throws Exception;
	
	/**
	 * 方法描述：领队确认用户报名
	 * 返回类型：void
	 * @param map
	 */
	public void updateConfirmSignUp(Map<String,Object> map)throws Exception;
	
	/**
	 * 方法描述：领队确认取消用户报名
	 * 返回类型：void
	 * @param map
	 */
	public void updateCancelSignUp(Map<String,Object> map)throws Exception;
	
	/**
	 * 用户同意/不同意退款
	 */
	public void updateRefundByUser(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：用户确认交易完成
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	public void updateComplete(Map<String,Object> map) throws Exception;
	
	/**
	 * 用户查看报名详情
	 */
	MySignUp selectDetails(Map<String, Object> map) throws Exception;
	
	/**
	 * 领队查看报名详情
	 */
	MySignUp selectDetailsForLeader(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询所有未付款订单
	 */
	List<ActiveSignup> findAllWaitPaySignup() throws Exception;
	
	/**
	 * 到时间未付款取消订单
	 */
	int payCloseForAsu_id(String id) throws Exception;
	
	/**
	 * 订单是否评论
	 */
	boolean isComment(Map<String,Object> map) throws Exception;
	
	
}

