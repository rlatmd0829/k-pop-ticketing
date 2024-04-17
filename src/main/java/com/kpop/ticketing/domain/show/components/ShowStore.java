package com.kpop.ticketing.domain.show.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.domain.show.repository.ShowStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowStore {
	private final ShowStoreRepository showStoreRepository;

	public void save(Show show) {
		showStoreRepository.save(show);
	}
}
