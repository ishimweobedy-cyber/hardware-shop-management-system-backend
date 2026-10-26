package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.entity.Counter;
import com.hardwareshop.hardware_shop_management_system.service.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/counters")
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Counter counter) {
        String message = counterService.save(counter);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Counter>> findAll() {
        return new ResponseEntity<>(counterService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Counter> result = counterService.findById(id);
        if (result.isPresent()) return new ResponseEntity<>(result.get(), HttpStatus.OK);
        return new ResponseEntity<>("The counter with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Counter counter) {
        String message = counterService.update(id, counter);
        if (message.equals("The counter with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = counterService.delete(id);
        if (message.equals("The counter with that id does not exist")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
