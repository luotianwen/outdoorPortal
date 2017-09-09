package com.op.service.balance;

import java.util.List;

import com.op.dto.balance.CashRecordDTO;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
/** 
 * 账户资金明细表(cashRecord)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */  
public interface CashRecordService {

	/**
	 * 根据用户资金账户ID获取收支明细
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<CashRecordDTO> getCashRecordListPageByBalanceId(Page<Users> page)throws Exception;

	/**
	 * 用户支付成功新增发布者资金明细
	 * @param userId 用户ID
	 * @param this_money 可用金额
	 * @param frozen_money 冻结金额
	 * @param IDrelation_id 关联ID
	 * @throws Exception
	 */
    void saveCashRecordByActiveSignup(String userId,double this_money,double frozen_money,String IDrelation_id) throws Exception;

    /**
	 * 用户退款成功新增发布者负资金明细
	 * @param userId 用户ID
	 * @param this_money 可用金额
	 * @param frozen_money 冻结金额
	 * @param IDrelation_id 关联ID
	 * @throws Exception
	 */
    void saveRefundRecordByActiveSignup(String userId,double this_money,double frozen_money,String IDrelation_id) throws Exception;

    
	
}
