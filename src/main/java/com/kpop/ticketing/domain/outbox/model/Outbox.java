package com.kpop.ticketing.domain.outbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "outbox")
@NoArgsConstructor
@Getter
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OutboxType type;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private String payload;

    private LocalDateTime createdAt;

    public Outbox(OutboxType outboxType, String payload) {
        this.type = outboxType;
        this.status = OutboxStatus.PENDING;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();
    }

    public void complete() {
        this.status = OutboxStatus.COMPLETED;
    }

    public void fail() {
        this.status = OutboxStatus.FAILED;
    }
}
