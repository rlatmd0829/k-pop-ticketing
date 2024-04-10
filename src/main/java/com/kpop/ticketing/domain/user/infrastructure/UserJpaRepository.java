package com.kpop.ticketing.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.user.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
