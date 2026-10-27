package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, UUID> {

    List<SalesOrder> findByStatusOrderByOrderDateAsc(SalesOrderStatus status);

    List<SalesOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);

    boolean existsBySalespersonAndOrderDateAndStatusNot(Salesperson salesperson, LocalDate orderDate, SalesOrderStatus status);

    long countByOrderDate(LocalDate orderDate);

    long countByStatusEquals(SalesOrderStatus status);

    @Query("SELECT o.status, COUNT(o) FROM SalesOrder o GROUP BY o.status")
    List<Object[]> countByStatus();

    @Modifying
    @Query("UPDATE SalesOrder o SET o.status = com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus.REJECTED WHERE o.salesperson.id = :salespersonId AND o.orderDate = :date AND o.status <> com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus.DELIVERED")
    int cancelBySalespersonAndDate(@Param("salespersonId") UUID salespersonId, @Param("date") LocalDate date);
}
