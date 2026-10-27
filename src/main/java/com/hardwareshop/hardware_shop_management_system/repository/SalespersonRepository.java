package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface SalespersonRepository extends JpaRepository<Salesperson, UUID> {

    @Query("SELECT p FROM Salesperson p JOIN p.productGroups c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Salesperson> findByProductGroupName(@Param("name") String name);
}
