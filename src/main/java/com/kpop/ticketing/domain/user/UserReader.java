package com.kpop.ticketing.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReader {
	private final UserRepository userRepository;

	public User getUser(Long userId) {
		return userRepository.getUser(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
	}
}
