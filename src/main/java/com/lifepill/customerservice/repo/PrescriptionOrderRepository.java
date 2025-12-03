package com.lifepill.customerservice.repo;

import com.lifepill.customerservice.model.PrescriptionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrescriptionOrderRepository extends MongoRepository<PrescriptionOrder, String> {
    List<PrescriptionOrder> findByCustomerId(Long customerId);
}
