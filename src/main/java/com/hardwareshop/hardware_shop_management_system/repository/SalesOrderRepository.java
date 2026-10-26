package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, UUID> {

    List<SalesOrder> findByStatusOrderByOrderDateAsc(SalesOrderStatus status);

    List<SalesOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
}
