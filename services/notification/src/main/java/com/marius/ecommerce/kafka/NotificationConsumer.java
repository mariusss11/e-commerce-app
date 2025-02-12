package com.marius.ecommerce.kafka;

import com.marius.ecommerce.email.EmailService;
import com.marius.ecommerce.kafka.order.OrderConfirmation;
import com.marius.ecommerce.kafka.payment.PaymentConfirmation;
import com.marius.ecommerce.notification.Notification;
import com.marius.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.marius.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.marius.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic:: {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        // todo send email
         var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
         emailService.sendPaymentSuccessEmail(
                 paymentConfirmation.customerEmail(),
                 customerName,
                 paymentConfirmation.amount(),
                 paymentConfirmation.orderReference()

         );

    }


    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic:: {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        // todo send email
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()

        );
    }

}
