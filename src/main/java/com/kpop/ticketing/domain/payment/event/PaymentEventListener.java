package com.kpop.ticketing.domain.payment.event;

import com.kpop.ticketing.domain.payment.model.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentEventListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentEventListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        Payment payment = event.getPayment();
        // Kafka에 이벤트 전송
        kafkaTemplate.send("payment-topic", payment.getId().toString());
    }
}
