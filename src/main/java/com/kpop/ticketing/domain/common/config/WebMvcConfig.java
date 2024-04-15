package com.kpop.ticketing.domain.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kpop.ticketing.domain.common.interceptor.TokenHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final TokenHandlerInterceptor tokenHandlerInterceptor;

	public WebMvcConfig(TokenHandlerInterceptor tokenHandlerInterceptor) {
		this.tokenHandlerInterceptor = tokenHandlerInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenHandlerInterceptor)
			.addPathPatterns("/api/v1/concerts/**")
			.addPathPatterns("/api/v1/shows/**")
			.addPathPatterns("/api/v1/seats/**");
	}
}
