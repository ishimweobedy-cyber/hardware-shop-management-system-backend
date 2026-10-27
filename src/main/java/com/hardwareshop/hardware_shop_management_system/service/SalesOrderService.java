package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import com.hardwareshop.hardware_shop_management_system.repository.SalesOrderRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SalespersonRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SoldItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final SalespersonRepository salespersonRepository;
    private final SoldItemRepository soldItemRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository, SalespersonRepository salespersonRepository, SoldItemRepository soldItemRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.salespersonRepository = salespersonRepository;
        this.soldItemRepository = soldItemRepository;
    }

    public String save(SalesOrder salesOrder) {
        if (salesOrder.getOrderDate().isBefore(LocalDate.now()))
            return "Sales order date cannot be in the past";
        if (salesOrder.getSalesperson() != null &&
                salesOrderRepository.existsBySalespersonAndOrderDateAndStatusNot(
                        salesOrder.getSalesperson(), salesOrder.getOrderDate(), SalesOrderStatus.REJECTED))
            return "Salesperson already serves an order on that date";
        salesOrder.setStatus(SalesOrderStatus.PLACED);
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

    public String updateStatus(UUID id, SalesOrderStatus newStatus) {
        Optional<SalesOrder> existing = salesOrderRepository.findById(id);
        if (existing.isEmpty()) return "The sales order with that id does not exist";
        SalesOrder order = existing.get();
        SalesOrderStatus current = order.getStatus();
        boolean allowed = (current == SalesOrderStatus.PLACED && (newStatus == SalesOrderStatus.PACKED || newStatus == SalesOrderStatus.REJECTED))
                || (current == SalesOrderStatus.PACKED && (newStatus == SalesOrderStatus.DELIVERED || newStatus == SalesOrderStatus.REJECTED));
        if (!allowed) return "Cannot change status from " + current + " to " + newStatus;
        order.setStatus(newStatus);
        salesOrderRepository.save(order);
        return "Sales order status updated successfully";
    }

    public List<SalesOrder> findByStatus(SalesOrderStatus status) {
        return salesOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<SalesOrder> findBetweenDates(LocalDate start, LocalDate end) {
        return salesOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }

    public Page<SalesOrder> getPage(Pageable pageable) {
        return salesOrderRepository.findAll(pageable);
    }

    public List<Object[]> statsByStatus() {
        return salesOrderRepository.countByStatus();
    }

    @Transactional
    public String cancelDay(UUID salespersonId, LocalDate date) {
        int count = salesOrderRepository.cancelBySalespersonAndDate(salespersonId, date);
        return count + " sales orders cancelled";
    }

    public String delete(UUID id) {
        Optional<SalesOrder> existing = salesOrderRepository.findById(id);
        if (existing.isEmpty()) return "The sales order with that id does not exist";
        if (soldItemRepository.existsBySalesOrder(existing.get()))
            return "That sales order has sold items, delete them first";
        salesOrderRepository.deleteById(id);
        return "Sales order deleted successfully";
    }
}
