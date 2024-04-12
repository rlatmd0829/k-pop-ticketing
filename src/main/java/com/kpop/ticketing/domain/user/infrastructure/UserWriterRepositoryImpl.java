package com.kpop.ticketing.domain.user.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserWriterRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserWriterRepositoryImpl implements UserWriterRepository {
	private final UserJpaRepository userJpaRepository;

	@Override
	public void save(User user) {
		userJpaRepository.save(user);
	}
}
