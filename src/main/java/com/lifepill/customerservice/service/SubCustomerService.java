package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.SubCustomer;
import com.lifepill.customerservice.repo.SubCustomerRepository;
import com.lifepill.customerservice.util.MissingParameterException;
import com.lifepill.customerservice.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCustomerService {
    @Autowired
    private SubCustomerRepository subCustomerRepository;

    // get all sub customers for a particular parent
    public List<SubCustomer> getAllSubCustomers(Long id) {
        return subCustomerRepository.findByParentId(id);
    }

    // get a specific sub customer for a particular parent
    public SubCustomer getSubCustomer(Long parentId, Long childId) {
        Optional<SubCustomer> subCustomer = subCustomerRepository.findById(childId);

        if (subCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        SubCustomer existingSubCustomer = subCustomer.get();

        if (!(existingSubCustomer.getParentId().equals(parentId))) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        return existingSubCustomer;
    }

    // add a new sub customer
    public SubCustomer addNewSubCustomer(SubCustomer subCustomer) {
        // Empty full name error handling
        if (subCustomer.getSubCustomerFullName().isEmpty()) {
            throw new MissingParameterException("Full Name cannot be Empty");
        }

        // Empty NIC number error handling
        if (subCustomer.getSubCustomerNIC().isEmpty()) {
            throw new MissingParameterException("NIC Number cannot be Empty");
        }

        // Empty Parent Id error handling
        if (subCustomer.getParentId() == null) {
            throw new MissingParameterException("Parent Id cannot be Empty");
        }

        return subCustomerRepository.save(subCustomer);
    }

    // update a sub customer
    public SubCustomer updateSubCustomer(Long parentId, Long childId, SubCustomer updatedSubCustomer) {
        Optional<SubCustomer> subCustomer = subCustomerRepository.findById(childId);

        if (subCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        SubCustomer existingSubCustomer = subCustomer.get();

        if (!(existingSubCustomer.getParentId().equals(parentId))) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        // Empty full name error handling
        if (updatedSubCustomer.getSubCustomerFullName().isEmpty()) {
            throw new MissingParameterException("Full Name cannot be Empty");
        }

        // Empty NIC number error handling
        if (updatedSubCustomer.getSubCustomerNIC().isEmpty()) {
            throw new MissingParameterException("NIC Number cannot be Empty");
        }

        existingSubCustomer.setSubCustomerFullName(updatedSubCustomer.getSubCustomerFullName());
        existingSubCustomer.setSubCustomerNIC(updatedSubCustomer.getSubCustomerNIC());

        return existingSubCustomer;
    }

    // delete a sub customer
    public void deleteSubCustomer(Long parentId, Long childId) {
        Optional<SubCustomer> subCustomer = subCustomerRepository.findById(childId);

        if (subCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        if (!(subCustomer.get().getParentId().equals(parentId))) {
            throw new ResourceNotFoundException("Sub Customer with ID " + childId + " not found.");
        }

        subCustomerRepository.deleteById(childId);
    }
}
