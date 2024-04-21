package com.kpop.ticketing.domain.waittoken.model;

import java.time.LocalDateTime;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
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

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private WaitingStatus status;

	@Column(name = "expired_at", nullable = false)
	private LocalDateTime expiredAt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private WaitToken(String token, WaitingStatus status, LocalDateTime expiredAt, User user) {
		this.token = token;
		this.status = status;
		this.expiredAt = expiredAt;
		this.user = user;
	}

	public static WaitToken create(String token, long ongoingCount, User user) {
		if (ongoingCount >= MAX_WAITING_NUMBER) {
			return new WaitToken(
				token,
				WaitingStatus.WAITING,
				LocalDateTime.now().plusDays(1000),
				user
			);
		} else {
			return new WaitToken(
				token,
				WaitingStatus.ONGOING,
				LocalDateTime.now().plusMinutes(10),
				user
			);
		}
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public void setStatus(WaitingStatus status) {
		this.status = status;
	}

	public boolean isExpired() {
		return expiredAt.isBefore(LocalDateTime.now());
	}

	public boolean isExpiredSoon() {
		return expiredAt.isBefore(LocalDateTime.now().plusMinutes(1));
	}

	public boolean isOngoing() {
		return status == WaitingStatus.ONGOING;
	}

	public boolean isWaiting() {
		return status == WaitingStatus.WAITING;
	}

	public void expire() {
		setStatus(WaitingStatus.EXPIRED);
	}

	public void onGoing() {
		setStatus(WaitingStatus.ONGOING);
	}

	public void validateToken() {
		if (isExpired()) {
			throw new CustomException(ErrorCode.INVALID_EXPIRED_TOKEN);
		}
		if (!isOngoing()) {
			throw new CustomException(ErrorCode.INVALID_STATUS_TOKEN);
		}
	}

	public void setStatusIfTokenExpired() {
		if (isExpired()) {
			setStatus(WaitingStatus.EXPIRED);
		}
	}

	public void refreshExpiredAtIfExpiredSoon() {
		if (isExpiredSoon()) {
			setExpiredAt(LocalDateTime.now().plusMinutes(5));
		}
	}

	public long getWaitingNumber(int unexpiredWaitTokenSize, long ongoingCount) {
		// 대기열이 있는 경우 대기번호를 부여
		if (isWaiting()) {
			return unexpiredWaitTokenSize - ongoingCount + 1;
		}
		return 0;
	}
}
