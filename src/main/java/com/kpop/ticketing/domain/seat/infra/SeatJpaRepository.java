package com.kpop.ticketing.domain.seat.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.seat.Seat;

public interface SeatJpaRepository extends JpaRepository<Seat, Long>, SeatJpaRepositoryCustom {
}
