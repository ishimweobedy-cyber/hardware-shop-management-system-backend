package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SalespersonRepository extends JpaRepository<Salesperson, UUID> {
}
