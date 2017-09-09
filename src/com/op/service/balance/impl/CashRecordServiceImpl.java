package com.op.service.balance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.balance.CashRecordDTO;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.balance.CashRecordService;

/** 
 * 账户资金明细表(cashRecord)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-02-25 13:42:49 
 */  
@Service("cashRecordServiceImpl")
public class CashRecordServiceImpl implements CashRecordService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据用户资金账户ID获取收支明细
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CashRecordDTO> getCashRecordListPageByBalanceId(Page<Users> page)throws Exception{
		return (List<CashRecordDTO>)dao.findForList("cashRecordMapper.getCashRecordListPageByBalanceId", page);
	}
	
	/**
	 * 用户支付成功新增发布者资金明细
	 * @param userId 用户ID
	 * @param this_money 可用金额
	 * @param frozen_money 冻结金额
	 * @param IDrelation_id 关联ID
	 * @throws Exception
	 */
    @Override
	public void saveCashRecordByActiveSignup(String userId,double this_money,double frozen_money,String IDrelation_id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	//发布者ID
    	map.put("userId",userId);
    	//新增所有金额
    	map.put("allprice",this_money+frozen_money);
    	//可用
    	map.put("this_money",this_money);
    	//冻结金额
    	map.put("frozen_money",frozen_money);
    	//收入/支出
    	map.put("behavior_type",1);
    	//冻结金额收入/支出
    	map.put("frozen_type",1);
    	//账目类型
    	map.put("account_type",2);
    	//账目关联
    	map.put("IDrelation_id",IDrelation_id);
    	//备注
    	String remarks = "活动订单编号"+IDrelation_id+",客户支付成功";
    	
    	if(this_money>0){
    		remarks += "，存入可用金额￥"+this_money+"元";
    	}
    	if(frozen_money>0){
    		remarks += "，存入冻结金额￥"+this_money+"元";
    	}
    	
    	map.put("remarks",remarks);
    	
    	dao.save("cashRecordMapper.saveCashRecordByActiveSignup", map);
    }
	
    /**
	 * 用户退款成功新增发布者负资金明细
	 * @param userId 用户ID
	 * @param this_money 可用金额
	 * @param frozen_money 冻结金额
	 * @param IDrelation_id 关联ID
	 * @throws Exception
	 */
    public void saveRefundRecordByActiveSignup(String userId,double this_money,double frozen_money,String IDrelation_id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	//发布者ID
    	map.put("userId",userId);
    	//新增所有金额
    	map.put("allprice",-this_money-frozen_money);
    	//可用
    	map.put("this_money",this_money);
    	//冻结金额
    	map.put("frozen_money",frozen_money);
    	//收入/支出
    	map.put("behavior_type",2);
    	//冻结金额收入/支出
    	map.put("frozen_type",2);
    	//账目类型
    	map.put("account_type",2);
    	//账目关联
    	map.put("IDrelation_id",IDrelation_id);
    	//备注
    	String remarks = "活动订单编号"+IDrelation_id+",用户退款成功";
    	
    	if((this_money+frozen_money)>0){
    		remarks += "，退款金额￥"+(this_money+frozen_money)+"元";
    	}
    	if(this_money>0){
    		remarks += "，可用金额减少￥"+this_money+"元";
    	}
    	if(frozen_money>0){
    		remarks += "，冻结金额减少￥"+frozen_money+"元";
    	}
    	
    	map.put("remarks",remarks);
    	
    	dao.save("cashRecordMapper.saveCashRecordByActiveSignup", map);
    }
    
}
