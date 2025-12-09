package com.lifepill.customerservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileOrderResponse {

    private UUID orderId;
    private UUID userId;
    private String userEmail;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String orderStatus;
    private List<OrderItemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItemDTO {
        private UUID id;
        private Long itemId;
        private String itemName;
        private Long pharmacyId;
        private String pharmacyName;
        private Integer quantity;
        private String unit;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;
    }
}
