package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import com.hardwareshop.hardware_shop_management_system.service.SalesOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SalesOrder salesOrder) {
        String message = salesOrderService.save(salesOrder);
        if (message.equals("Order date is required") || message.equals("Sales order date cannot be in the past"))
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        if (message.equals("Salesperson already serves an order on that date"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalesOrder>> findAll() {
        return new ResponseEntity<>(salesOrderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<SalesOrder> result = salesOrderService.findById(id);
        if (result.isPresent()) return new ResponseEntity<>(result.get(), HttpStatus.OK);
        return new ResponseEntity<>("The sales order with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody SalesOrder salesOrder) {
        String message = salesOrderService.update(id, salesOrder);
        if (message.equals("The sales order with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam SalesOrderStatus status) {
        String message = salesOrderService.updateStatus(id, status);
        if (message.equals("The sales order with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.startsWith("Cannot change status"))
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SalesOrder>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "orderDate,desc") String sort) {
        String[] parts = sort.split(",");
        Sort.Direction direction = parts.length > 1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));
        return new ResponseEntity<>(salesOrderService.getPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> statsByStatus() {
        return new ResponseEntity<>(salesOrderService.statsByStatus(), HttpStatus.OK);
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID salespersonId, @RequestParam String date) {
        String message = salesOrderService.cancelDay(salespersonId, LocalDate.parse(date));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<SalesOrder>> findByStatus(@RequestParam SalesOrderStatus status) {
        return new ResponseEntity<>(salesOrderService.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<SalesOrder>> findBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(salesOrderService.findBetweenDates(startDate, endDate), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = salesOrderService.delete(id);
        if (message.equals("The sales order with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.equals("That sales order has sold items, delete them first"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
