package com.kpop.ticketing.domain.user.repository;

import java.util.Optional;

import com.kpop.ticketing.domain.user.model.User;

public interface UserReaderRepository {
	Optional<User> getUser(Long userId);
}
