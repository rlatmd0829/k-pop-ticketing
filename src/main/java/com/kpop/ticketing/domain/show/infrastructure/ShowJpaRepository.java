package com.kpop.ticketing.domain.show.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.show.model.Show;

public interface ShowJpaRepository extends JpaRepository<Show, Long>, ShowJpaRepositoryCustom {
}
