package com.kpop.ticketing.presentation.show.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.show.dto.response.ShowListResponse;
import com.kpop.ticketing.presentation.show.usecase.GetShowsUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ShowController {

	private final GetShowsUseCase getShowsUseCase;

	@GetMapping("/{concertId}/shows")
	public List<ShowListResponse> getConcerts(
		@PathVariable Long concertId
	) {
		return getShowsUseCase.execute(concertId);
	}
}
