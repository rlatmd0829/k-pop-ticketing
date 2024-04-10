package com.kpop.ticketing.presentation.show.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.domain.show.component.ShowReader;
import com.kpop.ticketing.presentation.show.dto.response.ShowListResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class GetShowsUseCase {
	private final ShowReader showReader;

	public List<ShowListResponse> execute(Long concertId) {
		List<Show> seats = showReader.getShows(concertId);
		return seats.stream().map(ShowListResponse::from).toList();
	}
}
