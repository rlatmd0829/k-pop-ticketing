package com.kpop.ticketing.domain.seat.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.seat.model.Seat;

public interface SeatJpaRepository extends JpaRepository<Seat, Long>, SeatJpaRepositoryCustom {
}
