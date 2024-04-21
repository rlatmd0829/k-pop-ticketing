package com.kpop.ticketing.domain.payment.infrastructure;

import static com.kpop.ticketing.domain.payment.model.QPayment.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.payment.model.Payment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentJpaRepositoryCustomImpl implements PaymentJpaRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Payment> getPayment(Long paymentId) {
		return Optional.ofNullable(
			jpaQueryFactory.selectFrom(payment)
			.where(payment.id.eq(paymentId))
			.fetchOne());
	}
}
