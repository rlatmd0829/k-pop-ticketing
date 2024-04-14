package com.kpop.ticketing.domain.wait.infrastucture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.wait.model.WaitToken;

public interface WaitTokenJpaRepository extends JpaRepository<WaitToken, Long>, WaitTokenJpaRepositoryCustom {
}
