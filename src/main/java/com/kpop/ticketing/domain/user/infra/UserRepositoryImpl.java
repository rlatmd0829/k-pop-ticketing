package com.kpop.ticketing.domain.user.infra;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.user.User;
import com.kpop.ticketing.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> getUser(Long userId) {
		return userJpaRepository.findById(userId);
	}

}
