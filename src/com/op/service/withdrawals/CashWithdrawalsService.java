package com.op.service.withdrawals;

import java.util.List;
import java.util.Map;

import com.op.dto.usercenter.withdrawals.CashWithdrawalsDTO;
import com.op.dto.usercenter.withdrawals.WithdrawalsApplyDTO;
import com.op.entity.withdrawals.CashWithdrawals;
import com.op.plugin.page.Page;
/** 
 * 用户资金提现申请表(cashWithdrawals)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 09:36:36 
 */  
public interface CashWithdrawalsService {

	/**
	 * 获取提现申请列表
	 * @param page
	 * @return
	 */
	List<CashWithdrawals> getListPage(Page<CashWithdrawalsDTO> page)throws Exception;

	/**
	 * 根据Id获取申请详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CashWithdrawals findCashWithdrawalsById(Map<String,Object> map)throws Exception;

	/**
	 * 保存提现申请
	 * @param cashWithdrawals
	 * @throws Exception
	 */
	void saveCashWithdrawals(CashWithdrawals cashWithdrawals)throws Exception;

	/**
	 * 查询当天已经申请的提现总数
	 * @param cashWithdrawals
	 * @throws Exception
	 */
	int findCashWithdrawalsDayFrequency(WithdrawalsApplyDTO withdrawalsApplyDTO)throws Exception;
	 
	
}
