package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.Customer;
import com.hardwareshop.hardware_shop_management_system.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {
        String message = customerService.save(customer);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Customer> result = customerService.findById(id);
        if (result.isPresent()) return new ResponseEntity<>(result.get(), HttpStatus.OK);
        return new ResponseEntity<>("The customer with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Customer customer) {
        String message = customerService.update(id, customer);
        if (message.equals("The customer with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Customer>> findByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(customerService.findByLastName(lastName), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = customerService.delete(id);
        if (message.equals("The customer with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
