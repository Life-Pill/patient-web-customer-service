package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.Customer;
import com.lifepill.customerservice.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // get all customers
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get all the customers")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // get customer by id
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get a specific customer")
    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    // add new customer
    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Create a new customer")
    @PostMapping
    public Customer addNewCustomer(@RequestBody Customer newCustomer) {
        return customerService.addNewCustomer(newCustomer);
    }

    // update customer
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Update an existing customer")
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    // update customer password
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Change the password of an existing customer")
    @PutMapping("changePassword/{id}")
    public String updateCustomerPassword(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomerPassword(id, updatedCustomer.getCustomerPassword());
    }

    // delete customer
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete a specific customer")
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }

    // delete all customers
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete all the customers")
    @DeleteMapping
    public String deleteAllCustomers() {
        customerService.deleteAllCustomers();

        return "All the customers deleted successfully";
    }
}
