package com.example.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author 
 *
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfiguration extends WebMvcConfigurerAdapter {

	//@Bean
	//@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping  handlerMapping = new RequestMappingHandlerMapping();
		handlerMapping.setUseSuffixPatternMatch(false);
		return handlerMapping;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		try {
			UrlPathHelper uph = UrlPathHelper.class.newInstance();
			uph.setUrlDecode(true);
			configurer.setUrlPathHelper(uph );
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
