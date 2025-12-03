package com.lifepill.customerservice.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lifepill.customerservice.model.Prescription;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
    List<Prescription> findByCustomerId(Long customerId);
}
