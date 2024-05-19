package com.kpop.ticketing.domain.outbox.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "processed_message")
@Getter
public class ProcessedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageId;
    private LocalDateTime processedAt;

    public ProcessedMessage(String messageId) {
        this.messageId = messageId;
        this.processedAt = LocalDateTime.now();
    }
}
