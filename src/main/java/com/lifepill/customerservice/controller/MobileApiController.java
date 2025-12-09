package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.client.BranchServiceClient;
import com.lifepill.customerservice.client.InventoryServiceClient;
import com.lifepill.customerservice.dto.MobileOrderRequest;
import com.lifepill.customerservice.dto.MobileOrderResponse;
import com.lifepill.customerservice.model.PrescriptionOrder;
import com.lifepill.customerservice.service.MobileOrderService;
import com.lifepill.customerservice.service.PrescriptionOrderService;
import com.lifepill.customerservice.util.StandardResponse;
import jakarta.validation.Valid;
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
    private final MobileOrderService mobileOrderService;

    public MobileApiController(InventoryServiceClient inventoryServiceClient,
                               BranchServiceClient branchServiceClient,
                               PrescriptionOrderService prescriptionOrderService,
                               MobileOrderService mobileOrderService) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.branchServiceClient = branchServiceClient;
        this.prescriptionOrderService = prescriptionOrderService;
        this.mobileOrderService = mobileOrderService;
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
            
            // Extract items list from inventory response
            List<Map<String, Object>> items = extractItemsList(inventoryResponse);
            
            // Extract branches list from branch response
            List<Map<String, Object>> branches = extractBranchesList(branchResponse);
            
            // Merge item details into branches
            List<Map<String, Object>> enrichedBranches = mergeBranchesWithItems(branches, items);
            
            // Build final response structure matching branches format
            Map<String, Object> branchesWrapper = new HashMap<>();
            branchesWrapper.put("code", 200);
            branchesWrapper.put("message", "Success");
            branchesWrapper.put("data", enrichedBranches);
            
            Map<String, Object> result = new HashMap<>();
            result.put("branches", branchesWrapper);
            
            return ResponseEntity.ok(new StandardResponse(200, "Search results", result));
        } catch (Exception e) {
            log.error("Error searching medicine: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(500, "Failed to search medicine: " + e.getMessage(), null));
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> extractItemsList(Map<String, Object> inventoryResponse) {
        if (inventoryResponse != null && inventoryResponse.containsKey("data")) {
            Object dataObj = inventoryResponse.get("data");
            
            // Handle case where data is directly a List
            if (dataObj instanceof List) {
                return (List<Map<String, Object>>) dataObj;
            }
            
            // Handle case where data is a Map containing another "data" key
            if (dataObj instanceof Map) {
                Map<String, Object> dataMap = (Map<String, Object>) dataObj;
                if (dataMap.containsKey("data")) {
                    Object innerData = dataMap.get("data");
                    if (innerData instanceof List) {
                        return (List<Map<String, Object>>) innerData;
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> extractBranchesList(Map<String, Object> branchResponse) {
        if (branchResponse != null && branchResponse.containsKey("data")) {
            Object data = branchResponse.get("data");
            if (data instanceof List) {
                return (List<Map<String, Object>>) data;
            }
        }
        return new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> mergeBranchesWithItems(
            List<Map<String, Object>> branches, 
            List<Map<String, Object>> items) {
        
        List<Map<String, Object>> enriched = new ArrayList<>();
        
        // For each item, find matching branches and add item details
        for (Map<String, Object> item : items) {
            Object itemBranchId = item.get("branchId");
            
            for (Map<String, Object> branch : branches) {
                Object branchId = branch.get("branchId");
                
                // If item is at this branch, create enriched branch object
                if (itemBranchId != null && itemBranchId.equals(branchId)) {
                    Map<String, Object> enrichedBranch = new HashMap<>(branch);
                    
                    // Add item-specific fields
                    enrichedBranch.put("itemId", item.get("itemId"));
                    enrichedBranch.put("itemBarCode", item.get("itemBarCode"));
                    enrichedBranch.put("measuringUnitType", item.get("measuringUnitType"));
                    enrichedBranch.put("isAvailable", item.get("stock"));
                    enrichedBranch.put("quantityAvailable", item.get("itemQuantity"));
                    enrichedBranch.put("unitPrice", item.get("sellingPrice"));
                    enrichedBranch.put("itemName", item.get("itemName"));
                    enrichedBranch.put("itemImage", item.get("itemImage"));
                    enrichedBranch.put("itemDescription", item.get("itemDescription"));
                    
                    enriched.add(enrichedBranch);
                }
            }
        }
        
        return enriched;
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
     * Create a new order.
     */
    @PostMapping("/orders")
    @Operation(summary = "Create order", description = "Create a new order with items from pharmacy")
    public ResponseEntity<StandardResponse> createOrder(
            @Valid @RequestBody MobileOrderRequest orderRequest,
            HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} creating new order", userId);
        
        try {
            MobileOrderResponse orderResponse = mobileOrderService.createOrder(orderRequest, userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse(201, "Order created successfully", orderResponse));
        } catch (Exception e) {
            log.error("Error creating order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(500, "Failed to create order: " + e.getMessage(), null));
        }
    }

    /**
     * Get user's order history.
     */
    @GetMapping("/orders/history")
    @Operation(summary = "Get order history", description = "Get authenticated user's order history")
    public ResponseEntity<StandardResponse> getOrderHistory(HttpServletRequest request) {
        
        UUID userId = (UUID) request.getAttribute("userId");
        log.info("User {} requesting order history", userId);
        
        try {
            List<MobileOrderResponse> orders = mobileOrderService.getOrderHistory(userId);
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
