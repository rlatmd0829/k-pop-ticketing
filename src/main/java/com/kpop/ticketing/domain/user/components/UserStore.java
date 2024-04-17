package com.kpop.ticketing.domain.user.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserStore {
	private final UserStoreRepository userStoreRepository;

	public void save(User user) {
		userStoreRepository.save(user);
	}
}
