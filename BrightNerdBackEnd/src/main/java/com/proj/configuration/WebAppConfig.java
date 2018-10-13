package com.proj.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc //<mvc:annotation-driven> tag in dispatcher-servlet.xml file
@ComponentScan(basePackages="com.proj.*") //<context:component-scan ..>
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		System.out.println("Multipart Resolver bean created");
		return new CommonsMultipartResolver();
	}
}
