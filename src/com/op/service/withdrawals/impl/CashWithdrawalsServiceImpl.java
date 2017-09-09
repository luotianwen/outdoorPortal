package com.op.service.withdrawals.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.usercenter.withdrawals.CashWithdrawalsDTO;
import com.op.dto.usercenter.withdrawals.WithdrawalsApplyDTO;
import com.op.entity.withdrawals.CashWithdrawals;
import com.op.plugin.page.Page;
import com.op.service.withdrawals.CashWithdrawalsService;

/** 
 * 用户资金提现申请表(cashWithdrawals)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 09:36:36 
 */  
@Service("cashWithdrawalsServiceImpl")
public class CashWithdrawalsServiceImpl implements CashWithdrawalsService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	/**
	 * 获取提现申请列表
	 * @param page
	 * @return
	 */
	@Override
	public List<CashWithdrawals> getListPage(Page<CashWithdrawalsDTO> page)throws Exception{
		return (List<CashWithdrawals>)dao.findForList("cashWithdrawalsMapper.getListPage", page);
	}
	
	/**
	 * 根据Id获取申请详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public CashWithdrawals findCashWithdrawalsById(Map<String,Object> map)throws Exception{
		return (CashWithdrawals)dao.findForObject("cashWithdrawalsMapper.findCashWithdrawalsById", map);
	}
	
	/**
	 * 保存提现申请
	 * @param cashWithdrawals
	 * @throws Exception
	 */
	@Override
	public void saveCashWithdrawals(CashWithdrawals cashWithdrawals)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",cashWithdrawals.getId());
		map.put("user_id",cashWithdrawals.getUser_id());
		map.put("user_name","客户");
		
		//提现状态（1：提交申请；2：财务处理；3：提现完成；4：提现失败；）
		map.put("cw_state",1);
		//保存提现申请信息
		dao.save("cashWithdrawalsMapper.insertCashWithdrawals", cashWithdrawals);
		map.put("log","您提交了提现申请，请等待财务处理，预计三个工作日内完成");
		//插入提现申请操作日志
		dao.save("cashWithdrawalsLogMapper.insertLog", map);
		map.put("cw_cash",cashWithdrawals.getCw_cash());
		map.put("fee_cash",cashWithdrawals.getFee_cash());
		map.put("behavior_type",2);
		map.put("account_type",1);
		map.put("remarks","申请提现订单"+cashWithdrawals.getId()+"，申请成功，扣除可用金额￥"+cashWithdrawals.getCw_cash().add(cashWithdrawals.getFee_cash())
				+"（提现金额：￥"+cashWithdrawals.getCw_cash().setScale(2,   BigDecimal.ROUND_HALF_UP)+",手续费：￥"+cashWithdrawals.getFee_cash()+"）");
		//插入账户可用金额变动日志
		dao.save("cashRecordMapper.insertCashRecord", map);
		//变动账户可用金额
		dao.update("balanceMapper.updateAvailable_money_TX", cashWithdrawals);
	} 
	


	/**
	 * 查询当天已经申请的提现总数
	 * @param cashWithdrawals
	 * @throws Exception
	 */
	@Override
	public int findCashWithdrawalsDayFrequency(WithdrawalsApplyDTO withdrawalsApplyDTO)throws Exception{
		return (int)dao.findForObject("cashWithdrawalsMapper.findCashWithdrawalsDayFrequency", withdrawalsApplyDTO);
	}
	 
}
