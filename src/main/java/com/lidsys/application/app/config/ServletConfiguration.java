package com.lidsys.application.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
public class ServletConfiguration implements WebApplicationInitializer
	{
		
		@Override
		public void onStartup(ServletContext servletContext) throws ServletException
			{
				WebApplicationContext context = getContext();
				servletContext.addListener(new ContextLoaderListener(context));
				DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
				ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", dispatcherServlet);
				dispatcher.setLoadOnStartup(1);
				dispatcher.addMapping("/");
				
			}
			
		private AnnotationConfigWebApplicationContext getContext()
			{
				AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
				// context.setConfigLocation("com.lidsys.application.config");
				return context;
			}
			
		@Bean
		public ViewResolver getViewResolver()
			{
				InternalResourceViewResolver resolver = new InternalResourceViewResolver();
				resolver.setPrefix("/templates/");
				resolver.setSuffix(".html");
				return resolver;
			}
	}
