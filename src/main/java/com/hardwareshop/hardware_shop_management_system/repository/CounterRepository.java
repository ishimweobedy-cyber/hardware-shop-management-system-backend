package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CounterRepository extends JpaRepository<Counter, UUID> {
}
