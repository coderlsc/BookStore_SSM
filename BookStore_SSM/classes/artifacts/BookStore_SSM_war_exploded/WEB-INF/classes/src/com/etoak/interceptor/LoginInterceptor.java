package com.etoak.interceptor;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.etoak.bean.Book_Admin;

/**
 * 	拦截器
 *	HandlerInterceptor 
 *  springmvc拦截器依赖servlet API
 */

public class LoginInterceptor implements HandlerInterceptor{

	/**
	 * 3.afterCompletion该方法在跳转视图成功之后触发
	 * */
	@Override
	public void afterCompletion(
			HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2, 
			Exception arg3)
			throws Exception {
		
	}

	/**
	 * 2.postHandle该方法在返回视图之前触发
	 * */
	@Override
	public void postHandle(
			HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2, 
			ModelAndView arg3)
			throws Exception {
		
	}

	/**
	 * 1.preHandle该方法在访问后台url请求地址之前触发
	 *  true表示验证通过，不被拦截   
	 *  false表示验证不通过，被拦截
	 * */
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object object) throws Exception {
		
		System.out.println("拦截url请求地址是：" 
		                     + request.getRequestURL());
		
		if(request.getRequestURI().contains("/login")){
			return true;
		}
		
		HttpSession session = request.getSession();
		Book_Admin admin = 
				(Book_Admin)session.getAttribute("admin");
		//判断用户是否登录 没有登录就跳转登录页面
		if(admin == null){
			response.sendRedirect(
					request.getContextPath() + "/welcome.jsp");
			System.out.println("没有用户session信息...");
			return false;
		}
		else
		return true;
	}

}
