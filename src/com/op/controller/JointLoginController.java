package com.op.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.op.entity.jointLogin.JointLogin;
import com.op.entity.users.Users;
import com.op.service.jointLogin.JointLoginService;
import com.op.util.Common;
import com.op.util.Const;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
/**
 * 
 * @author WinZhong
 *
 */
@Controller
@RequestMapping(value = "/jointLogin")
public class JointLoginController extends BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 第三方平台账号联合登录
	 */
	@Resource(name="jointLoginServiceImpl")
	private JointLoginService jointLoginServiceImpl;
	
	
	/**
	 * 前往QQ登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/qq/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

	/**
	 * QQ登录后的回调处理
	 * @param mv
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/qq/afterLogin")
	public ModelAndView afterLogin(ModelAndView mv,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
	       response.setContentType("text/html; charset=utf-8");
	        //PrintWriter out = response.getWriter();
	        try {
	            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
	            String accessToken   = null,
	                   openID        = null;
	            long tokenExpireIn = 0L;

	            if (accessTokenObj.getAccessToken().equals("")) {
					//	                我们的网站被CSRF攻击了或者用户取消了授权
					//	                做一些数据统计工作
	            	mv.addObject("msg", "没有获取到响应参数");
	            	mv.setViewName("loginErr");
	            	logger.error("QQ登录用户出错：没有获取到响应参数");
	            } else {
	                accessToken = accessTokenObj.getAccessToken();
	                tokenExpireIn = accessTokenObj.getExpireIn();

	               /* session.setAttribute("demo_access_token", accessToken);
	                session.setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));*/
	                
	                // 利用获取到的accessToken 去获取当前用的openid -------- start
	                OpenID openIDObj =  new OpenID(accessToken);
	                openID = openIDObj.getUserOpenID();
	                
	                Users user = null;
	                JointLogin jointLogin = new JointLogin();
	        		jointLogin.setLogin_type(Const.LOGIN_TYPE_QQ);
	        		jointLogin.setOpenid(openID);
	        		jointLogin.setAccessToken(accessToken);
	        		jointLogin.setTokenExpireIn(tokenExpireIn);
	                try {
	                	user = jointLoginServiceImpl.findUserByOpenId(jointLogin);
	                	
	                	if(user != null){
		                	
		                	try {
								jointLoginServiceImpl.updateUserJointLoginInfo(jointLogin);
								// 更新shiro登录
			                	shiroLogin(user, request,jointLogin);
			            		mv.setViewName("redirect:/index.html");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.error("更新QQ登录用户授权信息出错：", e);
				            	mv.addObject("QQ登录出错");
				            	mv.setViewName("loginErr");
							}
		                	
		                	
		                }else{
		                	
		                	//Start 处理QQ登录未绑定网站用户的逻辑
		                	UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
		                	UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
		                	if (userInfoBean.getRet() == 0) {
		                		jointLogin.setNickname(userInfoBean.getNickname());//设置昵称
		                		jointLogin.setGender(userInfoBean.getGender());//设置性别
		                		try {
									user = jointLoginServiceImpl.insertJointLoginInfo(jointLogin);
									if(user != null){
										// 更新shiro登录
					                	shiroLogin(user, request,jointLogin);
						            	//跳转个人中心
					                	mv.setViewName("redirect:/index.html");
									}else{
				                		mv.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
				                		mv.setViewName("loginErr");
									}
								} catch (Exception e) {
									logger.error("QQ登录:", e);
					            	mv.addObject("登录出错");
					            	mv.setViewName("loginErr");
								}
				                

		                	}else{
		                		mv.addObject("msg", "很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
		                		mv.setViewName("loginErr");
		                	}
		                }	
	                	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("查询QQ登录用户绑定信息出错：", e);
					}
	                
	                	
	                	
	                	//end 处理QQ登录未绑定网站用户的逻辑
	                
	               /* out.println("欢迎你，代号为 " + openID + " 的用户!");
	                session.setAttribute("demo_openid", openID);
	                out.println("<a href=" + "/shuoshuoDemo.html" +  " target=\"_blank\">去看看发表说说的demo吧</a>");
	                // 利用获取到的accessToken 去获取当前用户的openid --------- end


	                out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
	                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
	                
	                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
	                out.println("<br/>");
	                if (userInfoBean.getRet() == 0) {
	                    out.println(userInfoBean.getNickname() + "<br/>");
	                    out.println(userInfoBean.getGender() + "<br/>");
	                    out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
	                    out.println("会员 : " + userInfoBean.isVip() + "<br/>");
	                    out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
	                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
	                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
	                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
	                } else {
	                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
	                }
	                out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");



	                out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
	                PageFans pageFansObj = new PageFans(accessToken, openID);
	                PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
	                if (pageFansBean.getRet() == 0) {
	                    out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是")  + "QQ空间97700000官方认证空间的粉丝</p>");
	                } else {
	                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
	                }
	                out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");



	                out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
	                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
	                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
	                if (weiboUserInfoBean.getRet() == 0) {
	                    //获取用户的微博头像----------------------start
	                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
	                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
	                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
	                    //获取用户的微博头像 ---------------------end

	                    //获取用户的生日信息 --------------------start
	                    out.println("<p>尊敬的用户，你的生日是： " + weiboUserInfoBean.getBirthday().getYear()
	                                +  "年" + weiboUserInfoBean.getBirthday().getMonth() + "月" +
	                                weiboUserInfoBean.getBirthday().getDay() + "日");
	                    //获取用户的生日信息 --------------------end

	                    StringBuffer sb = new StringBuffer();
	                    sb.append("<p>所在地:" + weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
	                             + weiboUserInfoBean.getLocation());

	                    //获取用户的公司信息---------------------------start
	                    ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
	                    if (companies.size() > 0) {
	                        //有公司信息
	                        for (int i=0, j=companies.size(); i<j; i++) {
	                            sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" +
	                            companies.get(i).getCompanyName() + " 部门名称-" + companies.get(i).getDepartmentName() + " 开始工作年-" +
	                            companies.get(i).getBeginYear() + " 结束工作年-" + companies.get(i).getEndYear());
	                        }
	                    } else {
	                        //没有公司信息
	                    }
	                    //获取用户的公司信息---------------------------end

	                    out.println(sb.toString());

	                } else {
	                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + weiboUserInfoBean.getMsg());
	                }

	                out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");

*/

	            }
	        } catch (QQConnectException e) {
	        	logger.error("获取QQ登录用户授权信息出错");
            	mv.addObject("msg", "获取QQ登录用户授权信息出错");
            	mv.setViewName("loginErr");
	        }
	        return mv;
    }
	
	
	void shiroLogin(Users user, HttpServletRequest request,JointLogin jointLogin){
		Subject currentUser = SecurityUtils.getSubject();
		Session ssession = currentUser.getSession();
		ssession.setAttribute(Const.JOIN_LOGIN, user);
		
		UsernamePasswordToken token = new UsernamePasswordToken(jointLogin.getLogin_type()+jointLogin.getOpenid(),jointLogin.getOpenid());
		token.setRememberMe(true);
		
		// 获取登录IP
		currentUser.getSession().setAttribute(Const.LOGIN_IP, Common.getRemoteAddrIp(request));
		// 在调用了login方法后,SecurityManager会收到AuthenticationToken（验证token）,并将其发送给已配置的Realm执行必须的认证检查
		currentUser.login(token);
	}
}
