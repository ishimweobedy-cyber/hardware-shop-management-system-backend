package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import com.hardwareshop.hardware_shop_management_system.service.SalespersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/salespersons")
public class SalespersonController {

    private final SalespersonService salespersonService;

    public SalespersonController(SalespersonService salespersonService) {
        this.salespersonService = salespersonService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Salesperson salesperson) {
        String message = salespersonService.save(salesperson);
        if (message.equals("That counter is taken by another salesperson"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Salesperson>> findAll() {
        return new ResponseEntity<>(salespersonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Salesperson> result = salespersonService.findById(id);
        if (result.isPresent()) return new ResponseEntity<>(result.get(), HttpStatus.OK);
        return new ResponseEntity<>("The salesperson with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Salesperson salesperson) {
        String message = salespersonService.update(id, salesperson);
        if (message.equals("The salesperson with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.equals("That counter is taken by another salesperson"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-product-group")
    public ResponseEntity<List<Salesperson>> findByProductGroup(@RequestParam String name) {
        return new ResponseEntity<>(salespersonService.findByProductGroupName(name), HttpStatus.OK);
    }

    @PutMapping("/{salespersonId}/product-groups/{productGroupId}")
    public ResponseEntity<String> addProductGroup(@PathVariable UUID salespersonId, @PathVariable UUID productGroupId) {
        String message = salespersonService.addProductGroup(salespersonId, productGroupId);
        if (message.equals("The salesperson with that id does not exist") || message.equals("That product group does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.equals("The salesperson already has that product group"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{salespersonId}/counter")
    public ResponseEntity<String> changeCounter(@PathVariable UUID salespersonId, @RequestParam int counterNumber) {
        String message = salespersonService.changeCounter(salespersonId, counterNumber);
        if (message.equals("The salesperson with that id does not exist") || message.equals("The counter with that counterNumber does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        if (message.equals("That counter is taken by another salesperson"))
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = salespersonService.delete(id);
        if (message.equals("The salesperson with that id does not exist"))
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
