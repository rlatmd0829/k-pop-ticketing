package com.kpop.ticketing.domain.user.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.user.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
