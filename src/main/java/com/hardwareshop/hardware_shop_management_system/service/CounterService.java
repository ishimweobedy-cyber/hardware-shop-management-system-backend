package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.Counter;
import com.hardwareshop.hardware_shop_management_system.repository.CounterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CounterService {

    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public String save(Counter counter) {
        counterRepository.save(counter);
        return "Counter saved successfully";
    }

    public List<Counter> findAll() {
        return counterRepository.findAll();
    }

    public Optional<Counter> findById(UUID id) {
        return counterRepository.findById(id);
    }

    public String update(UUID id, Counter updated) {
        Optional<Counter> existing = counterRepository.findById(id);
        if (existing.isEmpty()) return "The counter with that id does not exist";
        Counter counter = existing.get();
        counter.setCounterNumber(updated.getCounterNumber());
        counter.setName(updated.getName());
        counterRepository.save(counter);
        return "Counter updated successfully";
    }

    public String delete(UUID id) {
        if (!counterRepository.existsById(id)) return "The counter with that id does not exist";
        counterRepository.deleteById(id);
        return "Counter deleted successfully";
    }
}
