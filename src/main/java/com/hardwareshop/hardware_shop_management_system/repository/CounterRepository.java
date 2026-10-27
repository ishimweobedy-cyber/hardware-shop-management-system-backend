package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CounterRepository extends JpaRepository<Counter, UUID> {

    @Query("SELECT l FROM Counter l WHERE NOT EXISTS (SELECT p FROM Salesperson p WHERE p.counter = l) ORDER BY l.counterNumber")
    List<Counter> findFreeCounters();

    Optional<Counter> findByCounterNumber(int counterNumber);
}
