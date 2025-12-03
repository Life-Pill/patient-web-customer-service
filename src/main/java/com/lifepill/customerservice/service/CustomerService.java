package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.Customer;
import com.lifepill.customerservice.repo.CustomerRepository;
import com.lifepill.customerservice.util.MissingParameterException;
import com.lifepill.customerservice.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // get customer by id
    public Optional<Customer> getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        } else {
            return customer;
        }
    }

    // add new customer
    public Customer addNewCustomer(Customer customer) {
        // Empty full name error handling
        if (customer.getCustomerFullName().isEmpty()) {
            throw new MissingParameterException("Full Name cannot be Empty");
        }

        // Empty email error handling
        if (customer.getCustomerEmail().isEmpty()) {
            throw new MissingParameterException("Email cannot be Empty");
        }

        // Empty mobile number error handling
        if (customer.getCustomerMobileNumber().isEmpty()) {
            throw new MissingParameterException("Mobile Number cannot be Empty");
        }

        // Empty password error handling
        if (customer.getCustomerPassword().isEmpty()) {
            throw new MissingParameterException("Password cannot be Empty");
        }

        // Empty street name error handling
        if (customer.getCustomerAddressStreet().isEmpty()) {
            throw new MissingParameterException("Address Street cannot be Empty");
        }

        // Empty city name error handling
        if (customer.getCustomerAddressCity().isEmpty()) {
            throw new MissingParameterException("Address City cannot be Empty");
        }

        // Empty district error handling
        if (customer.getCustomerAddressDistrict().isEmpty()) {
            throw new MissingParameterException("Address District cannot be Empty");
        }

        // Empty NIC number error handling
        if (customer.getCustomerNIC().isEmpty()) {
            throw new MissingParameterException("NIC Number cannot be Empty");
        }

        customer.setCustomerPassword(hashPassword(customer.getCustomerPassword()));
        return customerRepository.save(customer);
    }

    // update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> customer = customerRepository.findById(id);

        // Customer Id not found error handling
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        }

        // Empty full name error handling
        if (updatedCustomer.getCustomerFullName().isEmpty()) {
            throw new MissingParameterException("Full Name cannot be Empty");
        }

        // Empty email error handling
        if (updatedCustomer.getCustomerEmail().isEmpty()) {
            throw new MissingParameterException("Email cannot be Empty");
        }

        // Empty mobile number error handling
        if (updatedCustomer.getCustomerMobileNumber().isEmpty()) {
            throw new MissingParameterException("Mobile Number cannot be Empty");
        }

        // Empty password error handling
        if (updatedCustomer.getCustomerPassword().isEmpty()) {
            throw new MissingParameterException("Password cannot be Empty");
        }

        // Empty street name error handling
        if (updatedCustomer.getCustomerAddressStreet().isEmpty()) {
            throw new MissingParameterException("Address Street cannot be Empty");
        }

        // Empty city name error handling
        if (updatedCustomer.getCustomerAddressCity().isEmpty()) {
            throw new MissingParameterException("Address City cannot be Empty");
        }

        // Empty district error handling
        if (updatedCustomer.getCustomerAddressDistrict().isEmpty()) {
            throw new MissingParameterException("Address District cannot be Empty");
        }

        Customer existingCustomer = customer.get();

        existingCustomer.setCustomerFullName(updatedCustomer.getCustomerFullName());
        existingCustomer.setCustomerEmail(updatedCustomer.getCustomerEmail());
        existingCustomer.setCustomerMobileNumber(updatedCustomer.getCustomerMobileNumber());
        existingCustomer.setCustomerAddressStreet(updatedCustomer.getCustomerAddressStreet());
        existingCustomer.setCustomerAddressCity(updatedCustomer.getCustomerAddressCity());
        existingCustomer.setCustomerAddressDistrict(updatedCustomer.getCustomerAddressDistrict());
        existingCustomer.setCustomerNIC(updatedCustomer.getCustomerNIC());

        return customerRepository.save(existingCustomer);
    }

    public String updateCustomerPassword(Long id, String newPassword) {
        Optional<Customer> customer = customerRepository.findById(id);

        // Customer Id not found error handling
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        }

        if (newPassword.isEmpty()) {
            throw new MissingParameterException("New Password cannot be Empty");
        }

        Customer existingCustomer = customer.get();

        existingCustomer.setCustomerPassword(hashPassword(newPassword));
        customerRepository.save(existingCustomer);
        return "Password Successfully Updated";
    }

    // delete customer
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        // Customer Id not found error handling
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        }

        customerRepository.deleteById(id);
    }

    // delete all customers
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    // hashing the password
    public String hashPassword(String password) {
        return password;
    }
}
