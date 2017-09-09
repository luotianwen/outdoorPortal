package com.op.controller.insurance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.dto.insurance.BaoXianren;
import com.op.dto.insurance.InsuranceInquiryParameterDto;
import com.op.dto.insurance.InsurancePlanDTO;
import com.op.dto.insurance.InsurancePriceDTO;
import com.op.dto.insurance.InsurancesDTO;
import com.op.dto.insurance.InsurantDateLimitDTO;
import com.op.plugin.insurance.InsurancesUtil;
import com.op.service.insurance.InsurancePlanService;
import com.op.service.insurance.InsurancePriceService;
import com.op.service.insurance.InsurancesService;
import com.op.service.insurance.InsurantDateLimitService;
/** 
 * 保险信息(Insurances)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 13:51:34 
 */ 
@Controller
@RequestMapping(value="/insurances")
public class InsurancesController extends BaseController {
	
	@Resource(name="insurancesServiceImpl")
	private InsurancesService insurancesServiceImpl;
    
	@Resource(name="insurancePlanServiceImpl")
	private InsurancePlanService insurancePlanServiceImpl;	
	
	@Resource(name="insurancePriceServiceImpl")
	private InsurancePriceService insurancePriceServiceImpl;
	
	@Resource(name="insurantDateLimitServiceImpl")
	private InsurantDateLimitService insurantDateLimitServiceImpl;

	@Resource(name = "insurancesUtil")
	private InsurancesUtil insurancesUtil;

    /**
     * 获取保险列表
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list() throws Exception{
    	
    	List<InsurancesDTO> insurancesList = insurancesServiceImpl.getInsurancesList();
    	
    	/*for(Insurances i:insurancesList){
    		System.out.println(i.getProductName());
    	}*/
    	return insurancesList;
    }
   
    /**
     * 根据保险产品id获取保险计划列表
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping("plan/{productId}")
    @ResponseBody
    public Object list(@PathVariable String productId) throws Exception{
    	
    	List<InsurancePlanDTO> insurancePlanList = insurancePlanServiceImpl.getInsurancePlanList(productId);
    	
/*    	for(InsurancePlan i:insurancePlanList){
    		System.out.println(i.getPlanName());
    	}*/
    	
    	return insurancePlanList;
    }

    /**
     * 根据保险、计划、期限 id获取保险详情
     * @param productId
     * @param planId
     * @param deadlineId
     * @return
     * @throws Exception
     */
    @RequestMapping("/details")
    @ResponseBody
    public Object details(InsuranceInquiryParameterDto insuranceInquiryParameter) throws Exception{
    	/*Map<String,String> map = new HashMap<String,String>();
    	map.put("productId", productId);
    	map.put("planId", planId);
    	map.put("deadlineId", deadlineId);*/
    	Map<String,Object> map = insurancePriceServiceImpl.details(insuranceInquiryParameter);
    	 
    	return map;
    }

    /**
     * 根据保险id获取保险期限
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping("deadline/{productId}")
    @ResponseBody
    public Object deadline(@PathVariable String productId) throws Exception{
    	
    	List<InsurantDateLimitDTO> insurantDateLimitList = insurantDateLimitServiceImpl.findByProductId(productId);
    	 
    	return insurantDateLimitList;
    }
    
    
    
    @RequestMapping("test/{lpId}")
    @ResponseBody
    public Object test(@PathVariable String lpId) throws Exception{
    	List<BaoXianren> baoXianrenList = new ArrayList<BaoXianren>();
    	BaoXianren t1 = new BaoXianren();
    	 //活动订单号
    	t1.setAsu_id("12546854521158");
    	 //参加人员姓名
     	 t1.setAa_user_name("闫鑫鑫");
     	 //投保人邮箱
      	 t1.setEmail("2355566253@qq.com");
    	 //参加人员性别（1：男；2：女）
      	t1.setAa_user_sex(2);
    	 //参加人员手机号码
     	 t1.setAa_user_phone("13264256329");
    	 //参加人员证件类型
     	t1.setAa_user_certificates_type(1);
    	 //参加人员证件号码
     	 t1.setAa_user_certificates_num("411525198807173868");
     	 //是否投保人（1：是；2：不是）
        t1.setAa_signup_linkman(1);
        baoXianrenList.add(t1);

    	BaoXianren t2 = new BaoXianren();
    	 //活动订单号
    	t2.setAsu_id("12546854521158");
    	 //参加人员姓名
     	 t2.setAa_user_name("刘弘孚");
     	 //投保人邮箱
      	 t2.setEmail("2355566252@qq.com");
    	 //参加人员性别（1：男；2：女）
      	t2.setAa_user_sex(2);
    	 //参加人员手机号码
     	 t2.setAa_user_phone("13264256329");
    	 //参加人员证件类型
     	t2.setAa_user_certificates_type(1);
    	 //参加人员证件号码
     	 t2.setAa_user_certificates_num("411525198306153148");
     	 //是否投保人（1：是；2：不是）
        t2.setAa_signup_linkman(2);
        baoXianrenList.add(t2);
        


    	BaoXianren t3 = new BaoXianren();
    	 //活动订单号
    	t3.setAsu_id("12546854521158");
    	 //参加人员姓名
     	 t3.setAa_user_name("刘一森");
     	 //投保人邮箱
      	 t3.setEmail("2355566251@qq.com");
    	 //参加人员性别（1：男；2：女）
      	t3.setAa_user_sex(1);
    	 //参加人员手机号码
     	 t3.setAa_user_phone("13264256329");
    	 //参加人员证件类型
     	t3.setAa_user_certificates_type(1);
    	 //参加人员证件号码
     	 t3.setAa_user_certificates_num("330109198402285594");
     	 //是否投保人（1：是；2：不是）
        t3.setAa_signup_linkman(2);
        baoXianrenList.add(t3);
        
    	insurancesServiceImpl.buy(102,lpId,"1", "2016-06-16", "2016-06-17", baoXianrenList);
    	System.out.println("************");
    	return baoXianrenList;
    }
    
    
}
