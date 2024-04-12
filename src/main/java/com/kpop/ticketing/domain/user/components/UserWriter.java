package com.kpop.ticketing.domain.user.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserWriterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWriter {
	private final UserWriterRepository userWriterRepository;

	public void chargeBalance(User user, Integer chargeAmount) {
		user.chargeBalance(chargeAmount);
		userWriterRepository.save(user);
	}
}
