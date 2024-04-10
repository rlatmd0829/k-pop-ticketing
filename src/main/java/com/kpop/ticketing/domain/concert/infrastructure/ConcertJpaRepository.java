package com.kpop.ticketing.domain.concert.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.concert.model.Concert;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
}
