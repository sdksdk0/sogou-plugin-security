package cn.sogoucloud.plugin.security;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

public class SogouSecurityPlugin implements ServletContainerInitializer{

	@Override
	public void onStartup(Set<Class<?>> handlesTypes, ServletContext servletContext) throws ServletException {
		//设置初始化参数
		servletContext.setInitParameter("shiroConfigLocations", "classpath:sogou-security.ini");
		//注册Listener
		servletContext.addListener(EnvironmentLoaderListener.class);
		//注册Filter
		FilterRegistration.Dynamic smDynamic=servletContext.addFilter("SogouSecurityFilter", SogouSecurityFilter.class);
		smDynamic.addMappingForUrlPatterns(null, false, "/*");
		
	}
	
	
	

}
