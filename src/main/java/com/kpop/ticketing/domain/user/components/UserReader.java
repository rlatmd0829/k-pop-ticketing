package com.kpop.ticketing.domain.user.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.repository.UserRepository;
import com.kpop.ticketing.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserReader {
	private final UserRepository userRepository;

	public User getUser(Long userId) {
		return userRepository.getUser(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
	}
}
