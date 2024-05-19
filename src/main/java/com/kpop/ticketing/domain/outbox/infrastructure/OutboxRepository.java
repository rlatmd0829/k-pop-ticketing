package com.kpop.ticketing.domain.outbox.infrastructure;

import com.kpop.ticketing.domain.outbox.model.Outbox;
import com.kpop.ticketing.domain.outbox.model.OutboxStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    List<Outbox> findAllByStatus(OutboxStatus status);
}
