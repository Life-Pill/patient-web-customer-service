package com.lifepill.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Feign client for calling Branch Service.
 * Used for pharmacy locations and branch info.
 */
@FeignClient(name = "BRANCH-SERVICE", path = "/lifepill/v1")
public interface BranchServiceClient {

    /**
     * Get all branches (pharmacy locations).
     */
    @GetMapping("/branch/get-all-branches")
    Map<String, Object> getAllBranches();

    /**
     * Get branch by ID using path variable.
     */
    @GetMapping("/branch/get-branch/{branchId}")
    Map<String, Object> getBranchById(@PathVariable("branchId") Long branchId);

    /**
     * Get branch with item availability and pricing.
     */
    @GetMapping("/branch/summary/all")
    Map<String, Object> getAllBranchSummaries();
}
