package com.kpop.ticketing.domain.user.repository;

import com.kpop.ticketing.domain.user.model.User;

public interface UserWriterRepository {
	void save(User user);
}
