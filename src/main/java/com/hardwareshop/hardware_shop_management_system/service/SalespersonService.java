package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import com.hardwareshop.hardware_shop_management_system.repository.SalespersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalespersonService {

    private final SalespersonRepository salespersonRepository;

    public SalespersonService(SalespersonRepository salespersonRepository) {
        this.salespersonRepository = salespersonRepository;
    }

    public String save(Salesperson salesperson) {
        salespersonRepository.save(salesperson);
        return "Salesperson saved successfully";
    }

    public List<Salesperson> findAll() {
        return salespersonRepository.findAll();
    }

    public Optional<Salesperson> findById(UUID id) {
        return salespersonRepository.findById(id);
    }

    public String update(UUID id, Salesperson updated) {
        Optional<Salesperson> existing = salespersonRepository.findById(id);
        if (existing.isEmpty()) return "The salesperson with that id does not exist";
        Salesperson sp = existing.get();
        sp.setFirstName(updated.getFirstName());
        sp.setLastName(updated.getLastName());
        sp.setPhone(updated.getPhone());
        sp.setCounter(updated.getCounter());
        sp.setProductGroups(updated.getProductGroups());
        salespersonRepository.save(sp);
        return "Salesperson updated successfully";
    }

    public String delete(UUID id) {
        if (!salespersonRepository.existsById(id)) return "The salesperson with that id does not exist";
        salespersonRepository.deleteById(id);
        return "Salesperson deleted successfully";
    }
}
