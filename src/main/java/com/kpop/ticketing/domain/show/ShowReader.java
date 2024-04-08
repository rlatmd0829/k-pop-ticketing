package com.kpop.ticketing.domain.show;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowReader {
	private final ShowReaderRepository showReaderRepository;

	public Show getShow(Long showId) {
		return showReaderRepository.getShow(showId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOW));
	}

	public List<Show> getShows(Long concertId) {
		LocalDateTime now = LocalDateTime.now();
		return showReaderRepository.getShows(concertId, now);
	}
}
