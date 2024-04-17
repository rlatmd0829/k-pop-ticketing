package com.kpop.ticketing.domain.concert.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.concert.model.Concert;
import com.kpop.ticketing.domain.concert.repository.ConcertStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConcertStoreRepositoryImpl implements ConcertStoreRepository {
	private final ConcertJpaRepository concertJpaRepository;

	@Override
	public Concert save(Concert concert) {
		return concertJpaRepository.save(concert);
	}
}
