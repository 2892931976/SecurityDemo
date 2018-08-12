package com.example.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Controller
public class SecurityTestController {
	@Autowired
	private UserDao userDao;
	
//	访问主页（/），任何人（包括匿名用户）都可以访问  
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "首页，每个人都可以进入";
	}
	
	
//	访问提示登录页面(/login),任何人（包括匿名用户）都可以访问  
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
//	访问登录页面(/login),任何人（包括匿名用户）都可以访问  
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String loginP(HttpServletRequest request,HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findUserByUserNameAndPassword(username,password);
		if(user!=null) {
			session.setAttribute("user", user);//将user加入到session中
			return "登录成功";
		}else {
			return "登录失败";
		}
	}
	
	
//	访问注销页面(/logout)，需要user权限才可以访问  ，用户登录默认都赋予了user权限
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	@ResponseBody
	public String logout(HttpSession session) {
		session.invalidate();
		return "注销成功";
	}
	
//	访问权限不足页面(/permissionDenied),任何人（包括匿名用户）都可以访问  
	@RequestMapping("/permissionDenied")
	@ResponseBody
	public String permissionDenied() {
		return "权限不足页，任何人（包括匿名用户）都可以访问";
	}
//	访问用户欢迎页面(/user)，需要user权限才可以访问  ，用户登录默认都赋予了user权限
	@RequestMapping("/user")
	@ResponseBody
	public String user() {
		return "用户欢迎页，需要user权限才可以访问  ";
	}
//	访问vip爸爸页面(/vip)，需要vip权限才可以访问  
	@RequestMapping("/vip")
	@ResponseBody
	public String vip() {
		return "vip欢迎页，需要vip权限才可以访问  ";
	}
//	访问admin管理员页面(/admin)，需要admin权限才可以访问  
	@RequestMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin管理员页，需要admin权限才可以访问  ";
	}
}
