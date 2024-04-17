package com.kpop.ticketing.presentation.user.usecase;

import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.components.UserStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.presentation.user.dto.request.UserBalanceRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class ChargeUserBalanceUseCase {
	private final UserReader userReader;
	private final UserStore userStore;

	public void execute(Long userId, UserBalanceRequest userBalanceRequest) {
		User user = userReader.getUser(userId);
		user.chargeBalance(userBalanceRequest.getChargeAmount());
		userStore.save(user);
	}
}