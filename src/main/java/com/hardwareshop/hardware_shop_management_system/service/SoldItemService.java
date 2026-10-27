package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import com.hardwareshop.hardware_shop_management_system.entity.SoldItem;
import com.hardwareshop.hardware_shop_management_system.repository.SalesOrderRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SoldItemRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SoldItemService {

    private final SoldItemRepository soldItemRepository;
    private final SalesOrderRepository salesOrderRepository;

    public SoldItemService(SoldItemRepository soldItemRepository, SalesOrderRepository salesOrderRepository) {
        this.soldItemRepository = soldItemRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    public String save(SoldItem soldItem, UUID salesOrderId) {
        Optional<SalesOrder> orderOpt = salesOrderRepository.findById(salesOrderId);
        if (orderOpt.isEmpty()) return "The sales order with that id does not exist";
        SalesOrder order = orderOpt.get();
        if (order.getStatus() != SalesOrderStatus.DELIVERED)
            return "Sold items are only for DELIVERED sales orders";
        soldItem.setSoldDate(LocalDate.now());
        soldItem.setSalesOrder(order);
        soldItemRepository.save(soldItem);
        return "Sold item saved successfully";
    }

    public List<SoldItem> findByCustomerPhone(String phone) {
        return soldItemRepository.findByCustomerPhone(phone);
    }

    public String delete(UUID id) {
        if (!soldItemRepository.existsById(id)) return "The sold item with that id does not exist";
        soldItemRepository.deleteById(id);
        return "Sold item deleted successfully";
    }
}
