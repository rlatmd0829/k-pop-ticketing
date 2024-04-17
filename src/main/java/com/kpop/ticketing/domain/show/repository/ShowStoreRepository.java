package com.kpop.ticketing.domain.show.repository;

import com.kpop.ticketing.domain.show.model.Show;

public interface ShowStoreRepository {
	Show save(Show show);
}
