package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.client.BranchServiceClient;
import com.lifepill.customerservice.client.InventoryServiceClient;
import com.lifepill.customerservice.model.PrescriptionOrder;
import com.lifepill.customerservice.service.PrescriptionOrderService;
import com.lifepill.customerservice.util.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Mobile API Controller for mobile app users (authenticated via user-auth).
 * Provides endpoints for medicine search, pharmacy locations, and order history.
 */
@RestController
@RequestMapping("/lifepill/v1/mobile")
@Tag(name = "Mobile API", description = "APIs for mobile app users (medicine search, pharmacies, orders)")
@SecurityRequirement(name = "bearerAuth")
public class MobileApiController {

    private static final Logger log = LoggerFactory.getLogger(MobileApiController.class);

    private final InventoryServiceClient inventoryServiceClient;
    private final BranchServiceClient branchServiceClient;
    private final PrescriptionOrderService prescriptionOrderService;

    public MobileApiController(InventoryServiceClient inventoryServiceClient,
                               BranchServiceClient branchServiceClient,
                               PrescriptionOrderService prescriptionOrderService) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.branchServiceClient = branchServiceClient;
        this.prescriptionOrderService = prescriptionOrderService;
    }

    /**
     * Search for medicine by name.
     * Returns pharmacies that have the medicine with pricing.
     */
    @GetMapping("/medicine/search")
    @Operation(summary = "Search medicine", description = "Search for medicine by name and get pharmacy availability")
    public ResponseEntity<StandardResponse> searchMedicine(
            @RequestParam("itemName") String itemName,
            HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} searching for medicine: {}", userId, itemName);
        
        try {
            // Get items from inventory service
            Map<String, Object> inventoryResponse = inventoryServiceClient.searchItems(itemName);
            
            // Get all branches for availability info
            Map<String, Object> branchResponse = branchServiceClient.getAllBranches();
            
            // Combine results - this is a simplified version
            // In production, you'd want to join inventory with branch data
            Map<String, Object> result = new HashMap<>();
            result.put("items", inventoryResponse);
            result.put("branches", branchResponse);
            
            return ResponseEntity.ok(new StandardResponse(200, "Search results", result));
        } catch (Exception e) {
            log.error("Error searching medicine: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(500, "Failed to search medicine: " + e.getMessage(), null));
        }
    }

    /**
     * Get all pharmacy locations.
     */
    @GetMapping("/pharmacies")
    @Operation(summary = "Get all pharmacies", description = "Get all pharmacy branch locations with details")
    public ResponseEntity<StandardResponse> getAllPharmacies(HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} requesting all pharmacy locations", userId);
        
        try {
            Map<String, Object> branches = branchServiceClient.getAllBranches();
            return ResponseEntity.ok(new StandardResponse(200, "Pharmacy locations retrieved", branches));
        } catch (Exception e) {
            log.error("Error getting pharmacies: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(500, "Failed to get pharmacies: " + e.getMessage(), null));
        }
    }

    /**
     * Get pharmacy by ID.
     */
    @GetMapping("/pharmacies/{branchId}")
    @Operation(summary = "Get pharmacy by ID", description = "Get pharmacy details by branch ID")
    public ResponseEntity<StandardResponse> getPharmacyById(
            @PathVariable Long branchId,
            HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} requesting pharmacy: {}", userId, branchId);
        
        try {
            Map<String, Object> branch = branchServiceClient.getBranchById(branchId);
            return ResponseEntity.ok(new StandardResponse(200, "Pharmacy retrieved", branch));
        } catch (Exception e) {
            log.error("Error getting pharmacy: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponse(404, "Pharmacy not found", null));
        }
    }

    /**
     * Get user's order history (prescription orders).
     */
    @GetMapping("/orders/history")
    @Operation(summary = "Get order history", description = "Get authenticated user's prescription order history")
    public ResponseEntity<StandardResponse> getOrderHistory(HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} requesting order history", userId);
        
        try {
            // Get prescription orders for user
            // Note: In production, you'd filter by the user's customer ID
            // For now, return empty list since we need to map user ID to customer ID
            List<PrescriptionOrder> orders = new ArrayList<>();
            
            return ResponseEntity.ok(new StandardResponse(200, "Order history retrieved", orders));
        } catch (Exception e) {
            log.error("Error getting order history: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(500, "Failed to get order history: " + e.getMessage(), null));
        }
    }

    /**
     * Get user profile info.
     */
    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Get authenticated user's profile info")
    public ResponseEntity<StandardResponse> getUserProfile(HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        String email = (String) request.getAttribute("userEmail");
        
        log.info("User {} requesting profile", userId);
        
        Map<String, Object> profile = new HashMap<>();
        profile.put("userId", userId.toString());
        profile.put("email", email);
        
        return ResponseEntity.ok(new StandardResponse(200, "Profile retrieved", profile));
    }
}
