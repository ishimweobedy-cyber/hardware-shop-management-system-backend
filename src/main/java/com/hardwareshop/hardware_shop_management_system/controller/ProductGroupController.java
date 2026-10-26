package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.ProductGroup;
import com.hardwareshop.hardware_shop_management_system.service.ProductGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-groups")
public class ProductGroupController {

    private final ProductGroupService productGroupService;

    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ProductGroup productGroup) {
        String message = productGroupService.save(productGroup);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductGroup>> findAll() {
        return new ResponseEntity<>(productGroupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<ProductGroup> result = productGroupService.findById(id);
        if (result.isPresent()) return new ResponseEntity<>(result.get(), HttpStatus.OK);
        return new ResponseEntity<>("The product group with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ProductGroup productGroup) {
        String message = productGroupService.update(id, productGroup);
        if (message.equals("The product group with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = productGroupService.delete(id);
        if (message.equals("The product group with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
