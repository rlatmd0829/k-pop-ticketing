package com.kpop.ticketing.domain.payment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.payment.model.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
