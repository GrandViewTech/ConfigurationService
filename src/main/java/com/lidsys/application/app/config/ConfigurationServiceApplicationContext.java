package com.lidsys.application.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages =
	{ "com.lidsys.application" })
@EnableAsync
@EnableScheduling
@PropertySources(value =
	{ @PropertySource("file:properties/application.properties") })
public class ConfigurationServiceApplicationContext extends WebMvcConfigurerAdapter
	{
		private final static org.slf4j.Logger	LOGGER	= org.slf4j.LoggerFactory.getLogger(ConfigurationServiceApplicationContext.class);
		
		@Value("${server.port}")
		private Integer							port	= 8080;
		
		@Bean
		public EmbeddedServletContainerFactory servletContainer()
			{
				TomcatEmbeddedServletContainerFactory embeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
				embeddedServletContainerFactory.setPort(port);
				embeddedServletContainerFactory.setContextPath("/config");
				LOGGER.info("Context Path : " + embeddedServletContainerFactory.getContextPath());
				return embeddedServletContainerFactory;
			}
			
		@Bean
		final public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException
			{
				PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
				return propertySourcesPlaceholderConfigurer;
			}
			
		@Bean
		public CommonsMultipartResolver multipartResolver()
			{
				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
				commonsMultipartResolver.setDefaultEncoding("utf-8");
				return commonsMultipartResolver;
			}
			
		@Override
		public void addCorsMappings(CorsRegistry registry)
			{
				LOGGER.info("Adding Cross Origin For PUT , OPTION , GET , POST , DELETE ");
				registry.addMapping("api/*").allowedMethods("PUT", "OPTION", "GET", "POST", "DELETE");
			}
			
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer servletHandlerConfigurer)
			{
				servletHandlerConfigurer.enable();
			}
			
		public static void main(String[] args) throws Exception
			{
				SpringApplication.run(ConfigurationServiceApplicationContext.class, args);
			}
			
		private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
			{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/js", "classpath:/static/css" };
			
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry)
			{
				registry.addResourceHandler("/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
				super.addResourceHandlers(registry);
			}
	}
