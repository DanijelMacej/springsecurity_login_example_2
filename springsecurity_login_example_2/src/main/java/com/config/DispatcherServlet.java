package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class configClass[] = {ConfigClassForDispatcher.class};
		return configClass;
	}

	@Override
	protected String[] getServletMappings() {
		String mapping[] = {"/"};
		return mapping;
	}

}
