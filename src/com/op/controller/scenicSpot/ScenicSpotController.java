package com.op.controller.scenicSpot;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.controller.BaseController;
import com.op.service.mmd.ScenicSpotService;

/** 
 * 景点(scenicSpot)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:55 
 */ 
@Controller
@RequestMapping(value="/scenicSpot")
public class ScenicSpotController extends BaseController {
	
	@Resource(name="scenicSpotServiceImpl")
	private ScenicSpotService scenicSpotServiceImpl;

	 
 
}
