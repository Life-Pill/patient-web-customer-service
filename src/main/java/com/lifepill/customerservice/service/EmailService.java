package com.lifepill.customerservice.service;

import com.lifepill.customerservice.dto.MobileOrderResponse;
import com.lifepill.customerservice.model.MobileOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.from:noreply@lifepill.lk}")
    private String fromEmail;

    public void sendOrderConfirmation(MobileOrder order, MobileOrderResponse orderResponse) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(order.getUserEmail());
            helper.setSubject("Order Confirmation - LifePill #" + order.getId().toString().substring(0, 8));

            Context context = new Context();
            context.setVariable("orderId", order.getId().toString().substring(0, 8));
            context.setVariable("userEmail", order.getUserEmail());
            context.setVariable("orderDate", order.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
            context.setVariable("totalAmount", order.getTotalAmount());
            context.setVariable("paymentMethod", order.getPaymentMethod());
            context.setVariable("items", orderResponse.getItems());

            String htmlContent = templateEngine.process("order-confirmation", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Order confirmation email sent to: {}", order.getUserEmail());
        } catch (MessagingException e) {
            log.error("Failed to send order confirmation email to: {}", order.getUserEmail(), e);
            // Don't throw exception - email failure shouldn't cancel order
        }
    }
}
