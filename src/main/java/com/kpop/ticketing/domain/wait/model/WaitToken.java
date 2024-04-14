package com.kpop.ticketing.domain.wait.model;

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
@Table(name = "wait_tokens")
@Getter
@NoArgsConstructor
public class WaitToken {
	private static final int MAX_WAITING_NUMBER = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "token_uuid", nullable = false)
	private String tokenUUID;

	@Column(name = "number", nullable = false)
	private Integer number;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private WaitingStatus status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public boolean isOngoing() {
		return status == WaitingStatus.ONGOING;
	}

	private WaitToken(String tokenUUID, Integer number, WaitingStatus status, User user) {
		this.tokenUUID = tokenUUID;
		this.number = number;
		this.status = status;
		this.user = user;
	}

	public static WaitToken create(String tokenUUID, long ongoingCount, Integer totalCount, User user) {
		if (ongoingCount >= MAX_WAITING_NUMBER) {
			return new WaitToken(
				tokenUUID,
				totalCount - MAX_WAITING_NUMBER + 1,
				WaitingStatus.WAITING,
				user
			);
		} else {
			return new WaitToken(
				tokenUUID,
				0,
				WaitingStatus.ONGOING,
				user
			);
		}
	}

	public void updateStatus(WaitingStatus status) {
		this.status = status;
	}

	public void updateNumber(Integer number) {
		this.number = number;
	}
}
