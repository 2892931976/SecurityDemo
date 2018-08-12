package com.example.demo.conf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UrlDao;
import com.example.demo.dao.UrlRoleDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.Url;
import com.example.demo.entity.User;

@Configuration
public class SecurityInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("---------------视图渲染之后的操作-------------------------"); 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
	}

	@Autowired
	private UrlDao urlDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UrlRoleDao urlRoleDao;
	
	/**
	 * 首先从数据库中查出资源url需要的权限
	 * 		不需要权限，直接放行
	 * 		需要权限，判断是否登录
	 * 			未登录，进入登录页面
	 * 				登录失败，给出提示信息
	 * 				登录成功，进行已登录判断
	 * 			已登录，判断是否有需要的权限
	 * 				有权限，直接通过
	 * 				没有权限，跳转到权限不通过页面
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String urlName = request.getServletPath();
		Url url = urlDao.findUrlByName(urlName);
		if(url==null) {//未被数据库管理的页面，任何人都可以访问，除非管理员手动设置需要权限
			return true;
		}
		List<String> needRoles = urlRoleDao.findRolesByUrlId(url.getId());//数据库中有设置但是没有分配权限的url，会返回[null]，需要去掉
		if(needRoles.contains(null)) {
			needRoles.remove(null);
		}
		if(needRoles.size()==0) {//不需要权限，直接放行
			return true;
		}else {//需要权限，判断是否登录
			User user = (User) request.getSession().getAttribute("user");
			if(user==null) {//未登录，进入登录页面
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}else {//已登录，判断是否有需要的权限
				List<String> userRoles = userRoleDao.getRolesByUserId(user.getId());
				for(String roleString:needRoles) {//有权限，直接通过
					if(userRoles.contains(roleString)) {
						return true;
					}
				}
				response.sendRedirect(request.getContextPath()+"/permissionDenied");
				return false;//没有权限，跳转到权限不通过页面
			}
		}
	}

}
