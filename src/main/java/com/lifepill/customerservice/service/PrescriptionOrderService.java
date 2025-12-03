package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.Prescription;
import com.lifepill.customerservice.model.PrescriptionOrder;
import com.lifepill.customerservice.repo.PrescriptionOrderRepository;
import com.lifepill.customerservice.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionOrderService {

    @Autowired
    private PrescriptionOrderRepository prescriptionOrderRepository;

    // get all my prescription orders
    public List<PrescriptionOrder> getAllPrescriptionOrders(Long customerId) {
        return prescriptionOrderRepository.findByCustomerId(customerId);
    }

    // get a specific order
    public Optional<PrescriptionOrder> getPrescriptionOrder(Long customerId, String prescriptionOrderId) {
        Optional<PrescriptionOrder> prescriptionOrder = prescriptionOrderRepository.findById(prescriptionOrderId);

        if (prescriptionOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();

        if (!(existingPrescriptionOrder.getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        return prescriptionOrder;
    }

    // add new oder and link the related prescription in the database
    public PrescriptionOrder addNewPrescriptionOrder(PrescriptionOrder newPrescriptionOrder) {
        PrescriptionService prescriptionService = new PrescriptionService();

        Optional<Prescription> prescription = prescriptionService.getPrescription(newPrescriptionOrder.getCustomerId(),
                newPrescriptionOrder.getPrescriptionId());

        String prescriptionImageId = prescription.get().getPrescriptionImageId();

        newPrescriptionOrder.setPrescriptionImageId(prescriptionImageId);
        return prescriptionOrderRepository.save(newPrescriptionOrder);
    }

    // update the selected pharmacy of the order
    public PrescriptionOrder updatePrescriptionOrderSelectedPharmacy(Long customerId, String prescriptionOrderId,
            Long selectedPharmacyId) {
        Optional<PrescriptionOrder> prescriptionOrder = prescriptionOrderRepository.findById(prescriptionOrderId);

        if (prescriptionOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();

        if (!(existingPrescriptionOrder.getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        existingPrescriptionOrder.setSelectedPharmacyId(selectedPharmacyId);

        prescriptionOrderRepository.save(existingPrescriptionOrder);

        return existingPrescriptionOrder;
    }

    // delete my prescription order
    public void deletePrescriptionOrder(Long customerId, String prescriptionOrderId) {
        Optional<PrescriptionOrder> prescriptionOrder = prescriptionOrderRepository.findById(prescriptionOrderId);

        if (prescriptionOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();

        if (!(existingPrescriptionOrder.getCustomerId().equals(customerId))) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        }

        prescriptionOrderRepository.deleteById(prescriptionOrderId);
    }

    // the following endpoints will be implmented under a new micro service related
    // to handling the pharmacy side

    /*
     * // endpoints for the admin side use
     * 
     * // get all prescription orders available
     * public List<PrescriptionOrder> getAllPrescriptionOrders(Long customerId) {
     * return prescriptionOrderRepository.findByCustomerId(customerId);
     * }
     * 
     * // get all prescription orders related to a specific pharmacy
     * 
     * // get a specific prescription order
     * public Optional<PrescriptionOrder> getPrescriptionOrder(String
     * prescriptionOrderId) {
     * Optional<PrescriptionOrder> prescriptionOrder =
     * prescriptionOrderRepository.findById(prescriptionOrderId);
     * 
     * if (prescriptionOrder.isEmpty()) {
     * throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId +
     * " not found.");
     * } else {
     * return prescriptionOrder;
     * }
     * }
     * 
     * // update the status of the order
     * public PrescriptionOrder updatePrescriptionOrderStatus(String
     * prescriptionOrderId,
     * PrescriptionOrder updatedPrescriptionOrder) {
     * Optional<PrescriptionOrder> prescriptionOrder =
     * prescriptionOrderRepository.findById(prescriptionOrderId);
     * 
     * if (prescriptionOrder.isEmpty()) {
     * throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId +
     * " not found.");
     * }
     * 
     * PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();
     * 
     * existingPrescriptionOrder.setOrderStatus(updatedPrescriptionOrder.
     * isOrderStatus());
     * 
     * prescriptionOrderRepository.save(existingPrescriptionOrder);
     * 
     * return existingPrescriptionOrder;
     * }
     * 
     * // update the status of the available pharmacies list
     * public PrescriptionOrder updatePrescriptionOrderAvailablility(String
     * prescriptionOrderId,
     * PrescriptionOrder updatedPrescriptionOrder) {
     * Optional<PrescriptionOrder> prescriptionOrder =
     * prescriptionOrderRepository.findById(prescriptionOrderId);
     * 
     * if (prescriptionOrder.isEmpty()) {
     * throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId +
     * " not found.");
     * }
     * 
     * PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();
     * 
     * existingPrescriptionOrder.setOrderStatus(updatedPrescriptionOrder.
     * isOrderStatus());
     * 
     * prescriptionOrderRepository.save(existingPrescriptionOrder);
     * 
     * return existingPrescriptionOrder;
     * }
     */
}
