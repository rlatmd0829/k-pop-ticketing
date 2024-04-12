package com.kpop.ticketing.domain.user.components;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.hibernate.validator.internal.util.Contracts.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.repository.UserReaderRepository;

@ExtendWith(MockitoExtension.class)
class UserReaderTest {
	@InjectMocks
	private UserReader userReader;

	@Mock
	private UserReaderRepository userReaderRepository;

	@Test
	@DisplayName("유저 조회 테스트")
	void getUserTest_success() {
		// given
		Long userId = 1L;

		// when
		when(userReaderRepository.getUser(anyLong())).thenReturn(Optional.of(new User()));

		// then
		assertNotNull(userReader.getUser(userId));
	}

	@Test
	@DisplayName("유저 조회 실패 테스트 - 유저가 없을 경우")
	void getUserTest_fail_whenUserNotExist() {
		// given
		Long userId = 999L;

		// when
		when(userReaderRepository.getUser(userId)).thenReturn(Optional.empty());

		// then
		assertThatThrownBy(() -> userReader.getUser(userId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.NOT_FOUND_USER.getMessage());
	}
}
