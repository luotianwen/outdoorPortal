package com.op.controller.log.order;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.op.controller.BaseController;
import com.op.service.log.order.ActiveOrderLogService;
/**
 * 项目名：outdoorPortal
 * 类描述：订单日志表Controller
 * 创建人：Yan
 * 创建时间： 2016-1-7 下午5:41:45
 * 最后修改时间：2016-1-7下午5:41:45
 */
@Controller
@RequestMapping(value="/orderLog")
public class ActiveOrderLogController extends BaseController {
	
	@Resource(name="orderLogServiceImpl")
	private ActiveOrderLogService orderLogServiceImpl;


}
