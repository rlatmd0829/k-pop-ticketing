package com.kpop.ticketing.domain.concert.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.concert.model.Concert;
import com.kpop.ticketing.domain.concert.repository.ConcertStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertStore {
	private final ConcertStoreRepository concertStoreRepository;

	public void save(Concert concert) {
		concertStoreRepository.save(concert);
	}
}
