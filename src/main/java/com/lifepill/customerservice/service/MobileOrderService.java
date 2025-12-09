package com.lifepill.customerservice.service;

import com.lifepill.customerservice.client.BranchServiceClient;
import com.lifepill.customerservice.client.InventoryServiceClient;
import com.lifepill.customerservice.dto.MobileOrderRequest;
import com.lifepill.customerservice.dto.MobileOrderResponse;
import com.lifepill.customerservice.model.MobileOrder;
import com.lifepill.customerservice.model.OrderItem;
import com.lifepill.customerservice.repository.MobileOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MobileOrderService {

    private final MobileOrderRepository orderRepository;
    private final InventoryServiceClient inventoryClient;
    private final BranchServiceClient branchClient;
    private final EmailService emailService;

    @Transactional
    public MobileOrderResponse createOrder(MobileOrderRequest request, UUID authenticatedUserId) {
        // Validate user ID matches authenticated user
        if (!request.getUserId().equals(authenticatedUserId)) {
            throw new IllegalArgumentException("User ID mismatch");
        }

        log.info("Creating order for user: {}", authenticatedUserId);

        // Create order entity
        MobileOrder order = MobileOrder.builder()
                .userId(request.getUserId())
                .userEmail(request.getUserEmail())
                .totalAmount(request.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .orderStatus(MobileOrder.OrderStatus.PENDING)
                .build();

        // Create order items and reduce stock
        for (MobileOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            // Reduce stock in inventory service
            try {
                inventoryClient.reduceStock(
                        itemRequest.getItemId(),
                        itemRequest.getPharmacyId(),
                        itemRequest.getQuantity()
                );
            } catch (feign.FeignException e) {
                log.error("Failed to reduce stock for item: {}. Status: {}, Body: {}", 
                        itemRequest.getItemId(), e.status(), e.contentUTF8());
                String errorMessage = "Failed to reduce stock";
                // Try to extract message from JSON response if possible
                try {
                     if (e.contentUTF8() != null && e.contentUTF8().contains("message")) {
                         // Simple string extraction to avoid adding huge JSON libs if not present
                         // Assuming {"... "message":"Actual Error", ...}
                         String content = e.contentUTF8();
                         int msgStart = content.indexOf("\"message\":\"") + 11;
                         if (msgStart > 10) {
                             int msgEnd = content.indexOf("\"", msgStart);
                             if (msgEnd > msgStart) {
                                 errorMessage = content.substring(msgStart, msgEnd);
                             }
                         }
                     }
                } catch (Exception ex) {
                    // Ignore parsing error
                }
                throw new RuntimeException(errorMessage, e);
            } catch (Exception e) {
                log.error("Failed to reduce stock for item: {}", itemRequest.getItemId(), e);
                throw new RuntimeException("Failed to reduce stock for item " + itemRequest.getItemId(), e);
            }

            // Create order item
            OrderItem orderItem = OrderItem.builder()
                    .itemId(itemRequest.getItemId())
                    .pharmacyId(itemRequest.getPharmacyId())
                    .quantity(itemRequest.getQuantity())
                    .unit(itemRequest.getUnit())
                    .unitPrice(itemRequest.getUnitPrice())
                    .build();

            order.addItem(orderItem);
        }

        // Save order
        MobileOrder savedOrder = orderRepository.save(order);
        savedOrder.setOrderStatus(MobileOrder.OrderStatus.CONFIRMED);
        savedOrder = orderRepository.save(savedOrder);

        log.info("Order created successfully: {}", savedOrder.getId());

        // Build response
        MobileOrderResponse response = buildOrderResponse(savedOrder);

        // Send email confirmation (async, doesn't block)
        try {
            emailService.sendOrderConfirmation(savedOrder, response);
        } catch (Exception e) {
            log.error("Failed to send order confirmation email", e);
            // Don't throw - email failure shouldn't cancel order
        }

        return response;
    }

    public List<MobileOrderResponse> getOrderHistory(UUID userId) {
        log.info("Getting order history for user: {}", userId);
        
        List<MobileOrder> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
        
        return orders.stream()
                .map(this::buildOrderResponse)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private MobileOrderResponse buildOrderResponse(MobileOrder order) {
        List<MobileOrderResponse.OrderItemDTO> itemDTOs = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            // Fetch item name from inventory
            String itemName = "Unknown Item";
            try {
                Map<String, Object> itemResponse = inventoryClient.getItemById(item.getItemId());
                if (itemResponse != null && itemResponse.containsKey("data")) {
                    Map<String, Object> itemData = (Map<String, Object>) itemResponse.get("data");
                    itemName = (String) itemData.getOrDefault("itemName", "Unknown Item");
                }
            } catch (Exception e) {
                log.warn("Failed to fetch item name for ID: {}", item.getItemId());
            }

            // Fetch pharmacy name
            String pharmacyName = "Unknown Pharmacy";
            try {
                Map<String, Object> branchResponse = branchClient.getBranchById(item.getPharmacyId());
                if (branchResponse != null && branchResponse.containsKey("data")) {
                    Map<String, Object> branchData = (Map<String, Object>) branchResponse.get("data");
                    pharmacyName = (String) branchData.getOrDefault("branchName", "Unknown Pharmacy");
                }
            } catch (Exception e) {
                log.warn("Failed to fetch pharmacy name for ID: {}", item.getPharmacyId());
            }

            MobileOrderResponse.OrderItemDTO itemDTO = MobileOrderResponse.OrderItemDTO.builder()
                    .id(item.getId())
                    .itemId(item.getItemId())
                    .itemName(itemName)
                    .pharmacyId(item.getPharmacyId())
                    .pharmacyName(pharmacyName)
                    .quantity(item.getQuantity())
                    .unit(item.getUnit())
                    .unitPrice(item.getUnitPrice())
                    .subtotal(item.getSubtotal())
                    .build();

            itemDTOs.add(itemDTO);
        }

        return MobileOrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .userEmail(order.getUserEmail())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .orderStatus(order.getOrderStatus().name())
                .items(itemDTOs)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
