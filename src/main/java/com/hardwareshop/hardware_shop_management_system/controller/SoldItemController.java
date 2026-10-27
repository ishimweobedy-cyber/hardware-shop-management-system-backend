package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.SoldItem;
import com.hardwareshop.hardware_shop_management_system.service.SoldItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sold-items")
public class SoldItemController {

    private final SoldItemService soldItemService;

    public SoldItemController(SoldItemService soldItemService) {
        this.soldItemService = soldItemService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SoldItem soldItem, @RequestParam UUID salesOrderId) {
        String message = soldItemService.save(soldItem, salesOrderId);
        if (message.equals("The sales order with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.equals("Sold items are only for DELIVERED sales orders"))
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<SoldItem>> findByCustomerPhone(@PathVariable String phone) {
        return new ResponseEntity<>(soldItemService.findByCustomerPhone(phone), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = soldItemService.delete(id);
        if (message.equals("The sold item with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
