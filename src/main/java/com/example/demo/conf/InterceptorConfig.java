package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器注册类
 * @author virtualspider
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private SecurityInterceptor securityInterceptor;
	
	/**
	 * 重写addInterceptors方法实现注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor);
	}
}
