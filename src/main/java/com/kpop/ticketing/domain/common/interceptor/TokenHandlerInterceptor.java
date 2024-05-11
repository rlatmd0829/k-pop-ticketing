package com.kpop.ticketing.domain.common.interceptor;

import com.kpop.ticketing.domain.common.redis.RedisService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {
	private final RedisService redisService;

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//		log.info("[TokenHandlerInterceptor] - preHandle 시작");
//		String token = request.getHeader("token");
//		if (StringUtils.isBlank(token) || !redisService.isExistWaitToken(token)) {
//			// 토큰이 없거나 Redis에 해당 토큰이 없는 경우
//			log.error("토큰이 없거나 유효하지 않습니다.");
//			// 예외 처리 또는 적절한 응답 반환
//			return false;
//		}
//		// 토큰이 존재하는 경우
//		return true;
//	}
}
