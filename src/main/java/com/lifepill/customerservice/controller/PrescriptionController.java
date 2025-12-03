package com.lifepill.customerservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifepill.customerservice.model.Prescription;
import com.lifepill.customerservice.service.PrescriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    // get all orders
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get all the orders")
    @GetMapping("/{customerId}")
    public List<Prescription> getAllPrescriptions(@PathVariable Long customerId) {
        return prescriptionService.getAllPrescriptions(customerId);
    }

    // get a specific order
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get a specific order")
    @GetMapping("/{customerId}/{prescriptionId}")
    public Optional<Prescription> getPrescription(@PathVariable Long customerId, @PathVariable String prescriptionId) {
        return prescriptionService.getPrescription(customerId, prescriptionId);
    }

    // update the order status
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Update an existing order")
    @PutMapping("/{customerId}/{prescriptionId}")
    public Prescription updatePrescription(@PathVariable Long customerId, @PathVariable String prescriptionId,
            @RequestBody Prescription updatedPrescription) {
        return prescriptionService.updatePrescription(customerId, prescriptionId, updatedPrescription);
    }

    // delete an order
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete a specific order")
    @DeleteMapping("/{customerId}/{prescriptionId}")
    public String deletePrescription(@PathVariable Long customerId, @PathVariable String prescriptionId) {
        prescriptionService.deletePrescription(customerId, prescriptionId);

        return "Order deleted successfully";
    }
}
