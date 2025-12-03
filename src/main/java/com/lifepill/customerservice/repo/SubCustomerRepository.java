package com.lifepill.customerservice.repo;

import com.lifepill.customerservice.model.SubCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCustomerRepository extends JpaRepository<SubCustomer, Long> {
    List<SubCustomer> findByParentId(Long parentId);
}
