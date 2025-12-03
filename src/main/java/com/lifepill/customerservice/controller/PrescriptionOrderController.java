package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.PrescriptionOrder;
import com.lifepill.customerservice.service.PrescriptionOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myOrders")
public class PrescriptionOrderController {
    @Autowired
    private PrescriptionOrderService prescriptionOrderService;

    // get all my prescription orders
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get all prescription orders related to a customer")
    @GetMapping("/{customerId}")
    public List<PrescriptionOrder> getAllPrescriptionOrders(@PathVariable Long customerId) {
        return prescriptionOrderService.getAllPrescriptionOrders(customerId);
    }

    // get a specific order
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get a specific prescription order")
    @GetMapping("/{customerId}/{prescriptionOrderId}")
    public Optional<PrescriptionOrder> getPrescriptionOrder(@PathVariable Long customerId,
            @PathVariable String prescriptionOrderId) {
        return prescriptionOrderService.getPrescriptionOrder(customerId, prescriptionOrderId);
    }

    // add new oder
    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Create a new prescription order")
    @PostMapping
    public PrescriptionOrder addNewPrescriptionOrder(@RequestBody PrescriptionOrder newPrescriptionOrder) {
        return prescriptionOrderService.addNewPrescriptionOrder(newPrescriptionOrder);
    }

    // update the selected pharmacy of the order
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Update an existing prescription order")
    @PutMapping("/{customerId}/{prescriptionOrderId}/{selectedPharmacyId}")
    public PrescriptionOrder updatePrescriptionOrderSelectedPharmacy(@PathVariable Long customerId,
            @PathVariable String prescriptionOrderId,
            @PathVariable Long selectedPharmacyId) {
        return prescriptionOrderService.updatePrescriptionOrderSelectedPharmacy(customerId, prescriptionOrderId,
                selectedPharmacyId);
    }

    // delete my prescription order
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete a specific prescription order related to a customer")
    @DeleteMapping("{customerId}/{prescriptionOrderId}")
    public String deletePrescriptionOrder(@PathVariable Long customerId, @PathVariable String prescriptionOrderId) {
        prescriptionOrderService.deletePrescriptionOrder(customerId, prescriptionOrderId);

        return "Prescription order deleted successfully";
    }
}
