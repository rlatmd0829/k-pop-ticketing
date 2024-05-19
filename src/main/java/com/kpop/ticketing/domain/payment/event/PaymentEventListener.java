package com.kpop.ticketing.domain.payment.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpop.ticketing.domain.outbox.infrastructure.OutboxRepository;
import com.kpop.ticketing.domain.outbox.infrastructure.ProcessedMessageRepository;
import com.kpop.ticketing.domain.outbox.model.Outbox;
import com.kpop.ticketing.domain.outbox.model.OutboxType;
import com.kpop.ticketing.domain.outbox.model.ProcessedMessage;
import com.kpop.ticketing.domain.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final ProcessedMessageRepository processedMessageRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handlePaymentCompletedBeforeCommit(PaymentCompletedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            Outbox outboxMessage = new Outbox(OutboxType.PAYMENT_COMPLETED, payload);
            outboxRepository.save(outboxMessage);
        } catch (JsonProcessingException e) {
            // 예외 처리 로직
            e.printStackTrace();
        }
    }

    @Async("taskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        try {
            String messageId = event.getPayment().getId().toString();

            // 메시지가 이미 처리되었는지 확인
            if (processedMessageRepository.existsByMessageId(messageId)) {
                return; // 이미 처리된 메시지라면 중복 처리하지 않음
            }

            String payload = objectMapper.writeValueAsString(event);
            // Kafka에 이벤트 전송
            kafkaTemplate.send("payment-topic", payload);

            // 메시지 처리 상태 저장
            ProcessedMessage processedMessage = new ProcessedMessage(messageId);
            processedMessageRepository.save(processedMessage);
        } catch (JsonProcessingException e) {
            // 예외 처리 로직
            e.printStackTrace();
        }
    }
}
