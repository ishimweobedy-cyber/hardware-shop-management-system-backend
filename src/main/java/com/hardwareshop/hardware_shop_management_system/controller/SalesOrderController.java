package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrder;
import com.hardwareshop.hardware_shop_management_system.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if (message.equals("The sales order with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = salesOrderService.delete(id);
        if (message.equals("The sales order with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
