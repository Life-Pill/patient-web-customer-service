package com.lifepill.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Feign client for calling Inventory Service.
 * Used for medicine search functionality.
 */
@FeignClient(name = "INVENTORY-SERVICE", path = "/lifepill/v1")
public interface InventoryServiceClient {

    /**
     * Search for items by name.
     * Returns items matching the search term.
     */
    @GetMapping("/item/search")
    Map<String, Object> searchItems(@RequestParam("name") String name);

    /**
     * Get all items.
     */
    @GetMapping("/item/get-all-items")
    Map<String, Object> getAllItems();

    /**
     * Get item by ID.
     */
    @GetMapping("/item/get-by-id")
    Map<String, Object> getItemById(@RequestParam("id") Long id);
}
