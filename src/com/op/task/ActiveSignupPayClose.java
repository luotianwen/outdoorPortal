package com.op.task;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;



import com.op.entity.usercenter.ActiveSignup;
import com.op.service.usercenter.ActiveApplicantLogService;
import com.op.service.usercenter.ActiveApplicantService;
import com.op.service.usercenter.ActiveSignupService;
import com.op.util.DateUtil;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * 
 * ClassName: ActiveSignupPayClose 
 * @Description: 定时关闭未支付订单
 * @date 2016年5月19日11:21:48
 */
public class ActiveSignupPayClose {

	// 报名订单
	@Resource(name="activeSignupServiceImpl")
	private ActiveSignupService activeSignupServiceImpl;
	
	//订单报名人
	@Resource(name="activeApplicantServiceImpl")
	private ActiveApplicantService activeApplicantServiceImpl;
	
	//操作日志
	@Resource(name="activeApplicantLogServiceImpl")
	private ActiveApplicantLogService activeApplicantLogServiceImpl;

	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 定时关闭未支付订单
	 */
	public void UpdateActiveSignup() {


		Iterator<byte[]> iter=RedisUtil.hkeys("waitPay".getBytes()).iterator();
        while (iter.hasNext()){
        	byte[] key = iter.next();
        	
        	ActiveSignup activeSignup = (ActiveSignup) SerializationUtil.deserialize(RedisUtil.hmget("waitPay".getBytes(),key).get(0));
        	if(activeSignup!=null){
        		if("2".equals(activeSignup.getAsu_pay_state())){
					RedisUtil.hdel("waitPay".getBytes(),key);
        		}else{
        			if(DateUtil.Sdifference(activeSignup.getAsu_create_time(), new Date())>3600){
        				try {
	        				//修改数据库
	        				int count = activeSignupServiceImpl.payCloseForAsu_id(activeSignup.getAsu_id());
        				
        					if(count>0){
            					activeApplicantServiceImpl.payCloseForAsu_id(activeSignup.getAsu_id());
            				}
        					log.info(activeSignup.getAsu_id()+"订单关闭");
						} catch (Exception e) {
							log.error("定时修改未付款订单异常！！！！！！！！！", e);
						}
        				
        				//清楚缓存数据
						RedisUtil.hdel("waitPay".getBytes(),key);
        			}
        		}
        	}
        }
		
	}
	
}
