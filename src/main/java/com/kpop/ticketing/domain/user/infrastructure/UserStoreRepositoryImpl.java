package com.kpop.ticketing.domain.user.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserStoreRepositoryImpl implements UserStoreRepository {
	private final UserJpaRepository userJpaRepository;

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}
