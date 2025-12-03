package com.lifepill.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.lifepill.customerservice.model.Prescription;
import com.lifepill.customerservice.repo.PrescriptionRepository;
import com.lifepill.customerservice.util.ResourceNotFoundException;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private GridFsTemplate template;

    // get all prescriptions
    public List<Prescription> getAllPrescriptions(Long customerId) {
        return prescriptionRepository.findByCustomerId(customerId);
    }

    // get a prescription
    public Optional<Prescription> getPrescription(Long customerId, String prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);

        if (prescription.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        if (!(prescription.get().getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        return prescription;
    }

    // add new new prescription
    // this will be called inside the PrescriptionImageService class
    public void addNewPrescription(Prescription newPrescription) {
        prescriptionRepository.save(newPrescription);
    }

    // update prescription
    public Prescription updatePrescription(Long customerId, String prescriptionId, Prescription updatedPrescription) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);

        if (prescription.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        Prescription existingPrescription = prescription.get();

        if (!(existingPrescription.getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        existingPrescription.setDescription(updatedPrescription.getDescription());

        prescriptionRepository.save(existingPrescription);

        return existingPrescription;
    }

    // delete prescription
    public void deletePrescription(Long customerId, String prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);

        if (prescription.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        Prescription existingPrescription = prescription.get();

        if (!(existingPrescription.getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionId + " not found.");
        }

        prescriptionRepository.deleteById(prescriptionId);

        template.delete(new Query(Criteria.where("_id").is(existingPrescription.getPrescriptionImageId())));
    }
}
