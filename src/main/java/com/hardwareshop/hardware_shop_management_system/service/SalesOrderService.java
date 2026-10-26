package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.repository.SalesOrderRepository;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    public String save(SalesOrder salesOrder) {
        salesOrderRepository.save(salesOrder);
        return "Sales order saved successfully";
    }

    public List<SalesOrder> findAll() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> findById(UUID id) {
        return salesOrderRepository.findById(id);
    }

    public String update(UUID id, SalesOrder updated) {
        Optional<SalesOrder> existing = salesOrderRepository.findById(id);
        if (existing.isEmpty()) return "The sales order with that id does not exist";
        SalesOrder order = existing.get();
        order.setOrderDate(updated.getOrderDate());
        order.setReference(updated.getReference());
        order.setTotalAmount(updated.getTotalAmount());
        order.setCustomer(updated.getCustomer());
        order.setSalesperson(updated.getSalesperson());
        salesOrderRepository.save(order);
        return "Sales order updated successfully";
    }

    public List<SalesOrder> findByStatus(SalesOrderStatus status) {
        return salesOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<SalesOrder> findBetweenDates(LocalDate start, LocalDate end) {
        return salesOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }

    public String delete(UUID id) {
        if (!salesOrderRepository.existsById(id)) return "The sales order with that id does not exist";
        salesOrderRepository.deleteById(id);
        return "Sales order deleted successfully";
    }
}
