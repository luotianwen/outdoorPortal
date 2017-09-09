package com.op.controller.activity.cost;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.controller.BaseController;
import com.op.service.activity.ActiveCorrelationCostService;

/** 
 * 活动费用表(activeCorrelationCost)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-17 13:51:04 
 */ 
@Controller
@RequestMapping(value="/activeCorrelationCost")
public class ActiveCorrelationCostController extends BaseController {
	
	@Resource(name="activeCorrelationCostServiceImpl")
	private ActiveCorrelationCostService activeCorrelationCostServiceImpl;
 
}
