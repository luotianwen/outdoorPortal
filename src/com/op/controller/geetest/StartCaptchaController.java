package com.op.controller.geetest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.util.geetest.GeetestConfig;
import com.op.util.geetest.GeetestLib;

/**
 * 类描述：极验验证码
 * 使用Get的方式返回：challenge和capthca_id 此方式以实现前后端完全分离的开发模式 专门实现failback
 * 
 * @author zheng
 *
 */
@Controller
@RequestMapping(value="/captchaController")
public class StartCaptchaController extends BaseController {

	@RequestMapping(value="/startCaptcha")
	@ResponseBody
	public String startCaptcha(HttpServletRequest request){

		// Conifg the parameter of the geetest object
		GeetestLib gtSdk = new GeetestLib();
		gtSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
		gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());

		gtSdk.setGtSession(request);//如果是同一会话多实例，可以使用此函数的另一重载实现式，设置不同的key即可

		String resStr = "{}";

		if (gtSdk.preProcess() == 1) {
			// gt server is in use
			resStr = gtSdk.getSuccessPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 1);

		} else {
			// gt server is down
			resStr = gtSdk.getFailPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 0);
		}

		return resStr;
	}

}