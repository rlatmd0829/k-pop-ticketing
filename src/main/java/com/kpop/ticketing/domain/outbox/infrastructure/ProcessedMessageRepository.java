package com.kpop.ticketing.domain.outbox.infrastructure;

import com.kpop.ticketing.domain.outbox.model.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedMessageRepository extends JpaRepository<ProcessedMessage, Long> {
    boolean existsByMessageId(String messageId);
}
