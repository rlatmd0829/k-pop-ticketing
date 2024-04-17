package com.kpop.ticketing.domain.concert.repository;

import com.kpop.ticketing.domain.concert.model.Concert;

public interface ConcertStoreRepository {
	Concert save(Concert concert);
}
