package com.kpop.ticketing.domain.payment.model;

import com.kpop.ticketing.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@Getter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Payment(Integer amount, PaymentStatus status, User user) {
		this.amount = amount;
		this.status = status;
		this.user = user;
	}

	public static Payment create(Integer amount, User user) {
		return new Payment(amount, PaymentStatus.COMPLETED, user);
	}
}
