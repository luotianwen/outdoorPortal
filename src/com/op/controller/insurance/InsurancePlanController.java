package com.op.controller.insurance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.dto.insurance.InsurancePlanDTO;
import com.op.service.insurance.InsurancePlanService;
/** 
 * 保险计划详情(InsurancePlan)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 13:51:34 
 */ 
@Controller
@RequestMapping(value="/insurancePlan")
public class InsurancePlanController extends BaseController {
	
	@Resource(name="insurancePlanServiceImpl")
	private InsurancePlanService insurancePlanServiceImpl;

    /**
     * 获取保险列表
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{productId}")
    @ResponseBody
    public Object list(@PathVariable String productId) throws Exception{
    	
    	List<InsurancePlanDTO> insurancePlanList = insurancePlanServiceImpl.getInsurancePlanList(productId);
    	
    	/*for(InsurancePlanDTO i:insurancePlanList){
    		System.out.println(i.getPlanName());
    	}*/
    	
    	return insurancePlanList;
    }

    /**
     * 根据保险计划id获取保险详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/details/{id}")
    @ResponseBody
    public Object details(@PathVariable String id) throws Exception{
    	
    	//Insurances insurancesList = insurancePlanServiceImpl.findById(id);
    	 
    	return "";
    }
 
}
