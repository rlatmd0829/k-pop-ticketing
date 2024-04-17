package com.kpop.ticketing.domain.waittoken.infrastucture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.waittoken.model.WaitToken;

public interface WaitTokenJpaRepository extends JpaRepository<WaitToken, Long>, WaitTokenJpaRepositoryCustom {
}
