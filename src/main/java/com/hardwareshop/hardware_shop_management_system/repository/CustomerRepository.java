package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
