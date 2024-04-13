package com.kpop.ticketing.domain.user.repository;

import com.kpop.ticketing.domain.user.model.User;

public interface UserStoreRepository {
	User save(User user);
}
