package com.kpop.ticketing.domain.concert.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.concert.Concert;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
}
