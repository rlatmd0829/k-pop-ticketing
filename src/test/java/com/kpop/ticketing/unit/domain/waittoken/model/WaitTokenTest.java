package com.kpop.ticketing.unit.domain.waittoken.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.domain.waittoken.model.WaitingStatus;

@ActiveProfiles("test")
class WaitTokenTest {

	@Test
	@DisplayName("대기 토큰 생성 테스트 - 현재 활동 중인 대기 토큰이 max waiting number 미만일 경우")
	void createTest_whenNumberOfOngoingTokensIsLessThanMax() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);

		assertEquals(0, waitToken.getWaitingNumber(10, 10));
		assertEquals(WaitingStatus.ONGOING, waitToken.getStatus());
	}

	@Test
	@DisplayName("대기 토큰 생성 테스트 - 현재 활동 중인 대기 토큰이 max waiting number 이상일 경우")
	void createTest_whenNumberOfOngoingTokensIsOverMax() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 100, user);

		assertEquals(1, waitToken.getWaitingNumber(100, 100));
		assertEquals(WaitingStatus.WAITING, waitToken.getStatus());
	}

	@Test
	@DisplayName("대기 토큰 검증 테스트 - 토큰 만료시간이 지났을 경우")
	void validateTokenTest_whenTokenIsExpired() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);
		waitToken.setExpiredAt(waitToken.getExpiredAt().minusMinutes(15));

		assertThatThrownBy(waitToken::validateToken)
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_EXPIRED_TOKEN.getMessage());
	}

	@Test
	@DisplayName("대기 토큰 검증 테스트 - 토큰 상태가 활동 중이 아닐 경우")
	void validateTokenTest_whenTokenIsNotOnGoing() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);
		waitToken.setStatus(WaitingStatus.WAITING);

		assertThatThrownBy(waitToken::validateToken)
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_STATUS_TOKEN.getMessage());
	}

	@Test
	@DisplayName("대기 토큰 만료 처리 테스트 - 토큰 만료시간이 지났을 경우")
	void setStatusIfExpiredTest_whenTokenExpired() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);
		waitToken.setExpiredAt(waitToken.getExpiredAt().minusMinutes(15));

		waitToken.setStatusIfTokenExpired();

		assertEquals(WaitingStatus.EXPIRED, waitToken.getStatus());
	}

	@Test
	@DisplayName("대기 토큰 만료 처리 테스트 - 토큰 만료시간이 지나지 않았을 경우")
	void setStatusIfExpiredTest_whenTokenNotExpired() {
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);
		waitToken.setExpiredAt(waitToken.getExpiredAt().plusMinutes(15));

		waitToken.setStatusIfTokenExpired();

		assertEquals(WaitingStatus.ONGOING, waitToken.getStatus());
	}

	@Test
	void refreshExpiredAtIfExpiredSoonTest() {
		// given
		User user = mock(User.class);
		WaitToken waitToken = WaitToken.create("token", 10, user);
		waitToken.setExpiredAt(waitToken.getExpiredAt().minusMinutes(9));

		// when
		LocalDateTime now = LocalDateTime.now();
		waitToken.refreshExpiredAtIfExpiredSoon();

		// then
		// refresh 되서 5분뒤 만료인지 확인
		LocalDateTime expectedExpiration = now.plusMinutes(5).truncatedTo(ChronoUnit.MINUTES);
		assertThat(waitToken.getExpiredAt().truncatedTo(ChronoUnit.MINUTES)).isEqualTo(expectedExpiration);
	}
}
