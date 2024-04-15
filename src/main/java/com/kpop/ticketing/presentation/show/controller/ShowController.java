package com.kpop.ticketing.presentation.show.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.show.dto.response.ShowListResponse;
import com.kpop.ticketing.presentation.show.usecase.GetShowsUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
@Tag(name = "Show", description = "콘서트에 공연 API")
public class ShowController {

	private final GetShowsUseCase getShowsUseCase;

	@Operation(summary = "Get Available Shows", description = "예약 가능한 공연 리스트 조회", tags = "Show")
	@GetMapping("/{concertId}/shows")
	public List<ShowListResponse> getConcerts(
		@RequestHeader String token,
		@PathVariable Long concertId
	) {
		return getShowsUseCase.execute(concertId);
	}
}
