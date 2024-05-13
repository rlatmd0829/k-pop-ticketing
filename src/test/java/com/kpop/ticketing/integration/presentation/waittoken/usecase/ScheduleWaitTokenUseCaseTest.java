package com.kpop.ticketing.integration.presentation.waittoken.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.kpop.ticketing.domain.common.redis.RedisService;
import com.kpop.ticketing.integration.presentation.config.TestContainerConfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.user.components.UserStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.presentation.waittoken.usecase.ScheduleWaitTokenUseCase;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@ExtendWith(TestContainerConfig.class)
class ScheduleWaitTokenUseCaseTest {

	@Autowired
	private ScheduleWaitTokenUseCase scheduleWaitTokenUseCase;

	@Autowired
	private UserStore userStore;

	@Autowired
	private RedisService redisService;

	@Test
	@DisplayName("토큰 만료 스케줄러 테스트")
	void setScheduleWaitTokenUseCase() {
		for (int i = 0; i < 20; i++) {
			User user = User.create("user", 1000);
			userStore.save(user);
			redisService.addToWaitingQueue(user.getId(), System.currentTimeMillis());
		}

		 // 스케줄러가 실행되면서 만료된 토큰은 삭제되어야 함
		scheduleWaitTokenUseCase.execute();
		Long waitNumber = redisService.getWaitNumber(Long.valueOf(11));

		assertThat(waitNumber).isEqualTo(1);
	}
}
