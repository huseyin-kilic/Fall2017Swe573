package com.ttevent;

import com.ttevent.service.InitializerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class TTEvent extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(TTEvent.class, args);
		context.getBean(InitializerService.class).fillReferenceData();
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Register Spring Social filter so that we can disconnect from providers
		FilterRegistration.Dynamic hiddenHttpMethodFilter =
						servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
		hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
	}

}