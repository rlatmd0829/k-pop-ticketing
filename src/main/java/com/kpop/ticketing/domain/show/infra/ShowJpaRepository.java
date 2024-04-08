package com.kpop.ticketing.domain.show.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kpop.ticketing.domain.show.Show;

public interface ShowJpaRepository extends JpaRepository<Show, Long>, ShowJpaRepositoryCustom {
}
