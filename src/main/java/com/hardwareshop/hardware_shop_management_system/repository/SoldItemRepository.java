package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface SoldItemRepository extends JpaRepository<SoldItem, UUID> {

    boolean existsBySalesOrder(SalesOrder salesOrder);

    @Query("SELECT d FROM SoldItem d WHERE d.salesOrder.customer.phone = :phone ORDER BY d.soldDate DESC")
    List<SoldItem> findByCustomerPhone(@Param("phone") String phone);
}
