package com.kpop.ticketing.domain.user;

import java.util.Optional;

public interface UserRepository {
	Optional<User> getUser(Long userId);
}
