package com.kpop.ticketing.presentation.user.usecase;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.presentation.user.dto.response.UserBalanceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserBalanceUseCase {
	private final UserReader userReader;

	public UserBalanceResponse execute(Long userId) {
		User user = userReader.getUser(userId);
		return UserBalanceResponse.from(user.getBalance());
	}
}
