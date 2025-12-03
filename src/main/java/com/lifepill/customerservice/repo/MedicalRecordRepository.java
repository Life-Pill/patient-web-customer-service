package com.lifepill.customerservice.repo;

import com.lifepill.customerservice.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
}
