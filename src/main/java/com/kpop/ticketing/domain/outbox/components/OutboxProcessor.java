package com.kpop.ticketing.domain.outbox.components;

import com.kpop.ticketing.domain.outbox.infrastructure.OutboxRepository;
import com.kpop.ticketing.domain.outbox.infrastructure.ProcessedMessageRepository;
import com.kpop.ticketing.domain.outbox.model.Outbox;
import com.kpop.ticketing.domain.outbox.model.OutboxStatus;
import com.kpop.ticketing.domain.outbox.model.ProcessedMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ProcessedMessageRepository processedMessageRepository;

    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void processOutboxMessages() {
        log.info("Processing outbox messages");
        List<Outbox> outboxMessages = outboxRepository.findAllByStatus(OutboxStatus.PENDING);

        for (Outbox outboxMessage : outboxMessages) {
            String messageId = outboxMessage.getId().toString();

            // 메시지가 이미 처리되었는지 확인
            if (processedMessageRepository.existsByMessageId(messageId)) {
                continue; // 이미 처리된 메시지라면 중복 처리하지 않음
            }

            try {
                // Kafka로 메시지 전송
                kafkaTemplate.send("payment-topic", outboxMessage.getPayload());

                // 메시지 상태 업데이트
                outboxMessage.complete();
                outboxRepository.save(outboxMessage);

                // 메시지 처리 상태 저장
                ProcessedMessage processedMessage = new ProcessedMessage(messageId);
                processedMessageRepository.save(processedMessage);
            } catch (Exception e) {
                // 메시지 상태를 FAILED로 업데이트
                outboxMessage.fail();
                outboxRepository.save(outboxMessage);
                // 예외 처리 로직
                e.printStackTrace();
            }
        }
    }
}
