package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.SubCustomer;
import com.lifepill.customerservice.service.SubCustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCustomers")
public class SubCustomerController {
    @Autowired
    private SubCustomerService subCustomerService;

    // get all the sub customers for a particular parent
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get all the sub customers related to a parent account")
    @GetMapping("/{parentId}")
    public List<SubCustomer> getAllSubCustomers(@PathVariable Long parentId) {
        return subCustomerService.getAllSubCustomers(parentId);
    }

    // get a specific sub customer for a particular parent
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get a specific sub customers related to a parent account")
    @GetMapping("/{parentId}/{childId}")
    public SubCustomer getSubCustomer(@PathVariable Long parentId, @PathVariable Long childId) {
        return subCustomerService.getSubCustomer(parentId, childId);
    }

    // add new sub customer
    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Create a new sub customer")
    @PostMapping
    public SubCustomer addNewSubCustomer(@RequestBody SubCustomer newSubCustomer) {
        return subCustomerService.addNewSubCustomer(newSubCustomer);
    }

    // update a sub customer
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Update an existing sub customer")
    @PutMapping("/{parentId}/{childId}")
    public SubCustomer updateSubCustomer(@PathVariable Long parentId, @PathVariable Long childId,
            @RequestBody SubCustomer updatedSubCustomer) {
        return subCustomerService.updateSubCustomer(parentId, childId, updatedSubCustomer);
    }

    // delete a sub customer
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete a specific sub customers related to a parent account")
    @DeleteMapping("/{parentId}/{childId}")
    public String deleteSubCustomer(@PathVariable Long parentId, @PathVariable Long childId) {
        subCustomerService.deleteSubCustomer(parentId, childId);

        return "Sub Customer Deleted Successfully";
    }
}
