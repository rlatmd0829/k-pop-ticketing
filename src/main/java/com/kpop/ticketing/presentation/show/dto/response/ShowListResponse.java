package com.kpop.ticketing.presentation.show.dto.response;

import java.time.LocalDateTime;

import com.kpop.ticketing.domain.show.model.Show;

import lombok.Getter;

@Getter
public class ShowListResponse {
	private Long id;
	private LocalDateTime showTime;

	private ShowListResponse(Long id, LocalDateTime showTime) {
		this.id = id;
		this.showTime = showTime;
	}

	public static ShowListResponse from(Show show) {
		return new ShowListResponse(show.getId(), show.getShowTime());
	}
}
