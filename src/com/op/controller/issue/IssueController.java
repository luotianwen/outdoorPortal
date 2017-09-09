package com.op.controller.issue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.service.issue.IssueService;
import com.op.util.Const;

/** 
 * 意见建议(issue)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-01-08 13:46:46 
 */ 
@Controller
@RequestMapping(value="/issue")
public class IssueController extends BaseController {
	
	@Resource(name="issueServiceImpl")
	private IssueService issueServiceImpl;
	
	
	/**
	 * 意见建议(接口)
	 * @param issuetype 反馈类型
	 * @param content 反馈内容
	 * @param contact 反馈人联系方式
	 * @return
	 */
	@RequestMapping("insertIssue")
	@ResponseBody
	public Map<String, Object> insertIssue(int issuetype,String content,String contact){
		Map<String,Object> map=new HashMap<String,Object>();//返回操作信息
		map.put(Const.RESPONSE_STATE, "200");//默认执行此操作成功!
		map.put(Const.SUCCESS_INFO, "操作成功!");
		Map<String,Object> issueMap=new HashMap<String, Object>();//意见建议
		issueMap.put("issuetype", issuetype);
		issueMap.put("content", content);
		issueMap.put("contact", contact);
		issueMap.put("userid",this.getSessionUser()==null?"游客":this.getSessionUser().getuId());//如果为空,提交者就是游客
		try {
			issueServiceImpl.insertIssue(issueMap);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "操作执行失败!");
			return map;
		}
		return map;
	}
}