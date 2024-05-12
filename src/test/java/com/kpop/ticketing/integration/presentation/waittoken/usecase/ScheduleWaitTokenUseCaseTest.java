package com.kpop.ticketing.integration.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.user.components.UserStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenStore;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;
import com.kpop.ticketing.presentation.waittoken.usecase.ScheduleWaitTokenUseCase;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class ScheduleWaitTokenUseCaseTest {

	@Autowired
	private ScheduleWaitTokenUseCase scheduleWaitTokenUseCase;

	@Autowired
	private WaitTokenReader waitTokenReader;

	@Autowired
	private WaitTokenStore waitTokenStore;

	@Autowired
	private UserStore userStore;

//	@Test
//	@DisplayName("토큰 만료 스케줄러 테스트")
//	void setScheduleWaitTokenUseCaseTest() {
//		User user = User.create("user", 1000);
//		userStore.save(user);
//
//		List<WaitToken> waitTokens = new ArrayList<>();
//
//		int ongoingCount = 0;
//		for (int i = 0; i < 200; i++) {
//			WaitToken waitToken = WaitToken.create("token" + i, ongoingCount, user);
//			// ongoing은 100개만 생성
//			if (i <= 100) {
//				ongoingCount++;
//				// 10개만 만료시간 지나게 설정
//				if (i < 10) {
//					waitToken.setExpiredAt(waitToken.getExpiredAt().minusMinutes(15));
//				}
//			}
//			waitTokens.add(waitToken);
//			waitTokenStore.save(waitToken);
//		}
//
//		// 스케줄러가 실행되면서 만료된 토큰은 삭제되어야 함
//		scheduleWaitTokenUseCase.execute();
//		List<WaitToken> unexpiredWaitTokens = waitTokenReader.getUnexpiredWaitTokens();
//
//		assertThat(unexpiredWaitTokens.size()).isEqualTo(190);
//	}
}
