package com.kpop.ticketing.domain.reservation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.reservation.model.Reservation;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long>, ReservationJpaRepositoryCustom {
}
