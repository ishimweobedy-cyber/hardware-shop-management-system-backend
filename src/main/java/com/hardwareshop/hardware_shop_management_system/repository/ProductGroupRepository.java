package com.hardwareshop.hardware_shop_management_system.repository;

import com.hardwareshop.hardware_shop_management_system.entity.Customer;
import com.hardwareshop.hardware_shop_management_system.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, UUID> {

    @Query("SELECT c FROM ProductGroup c WHERE c.salespersons IS EMPTY")
    List<ProductGroup> findUnusedProductGroups();
}
