package com.kpop.ticketing.domain.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.wait.components.WaitTokenReader;
import com.kpop.ticketing.domain.wait.model.WaitToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {
	private final WaitTokenReader waitTokenReader;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("TokenHandlerInterceptor.preHandle");
		String token = request.getHeader("token");
		// 토큰 검사
		WaitToken waitToken = waitTokenReader.getWaitTokenByToken(token);
		waitToken.validateToken();
		return true;
	}
}
