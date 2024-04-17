package com.kpop.ticketing.domain.show.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.domain.show.repository.ShowStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShowStoreRepositoryImpl implements ShowStoreRepository {
	private final ShowJpaRepository showJpaRepository;

	public Show save(Show show) {
		return showJpaRepository.save(show);
	}
}
