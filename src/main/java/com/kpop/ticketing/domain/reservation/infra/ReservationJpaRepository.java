package com.kpop.ticketing.domain.reservation.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.reservation.Reservation;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long>, ReservationJpaRepositoryCustom {
}
