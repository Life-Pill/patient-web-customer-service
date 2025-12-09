package com.lifepill.customerservice.repository;

import com.lifepill.customerservice.model.MobileOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MobileOrderRepository extends JpaRepository<MobileOrder, UUID> {
    
    List<MobileOrder> findByUserIdOrderByCreatedAtDesc(UUID userId);
    
    List<MobileOrder> findByUserEmailOrderByCreatedAtDesc(String userEmail);
}
