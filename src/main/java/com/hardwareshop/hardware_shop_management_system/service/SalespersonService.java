package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.Counter;
import com.hardwareshop.hardware_shop_management_system.entity.ProductGroup;
import com.hardwareshop.hardware_shop_management_system.entity.Salesperson;
import com.hardwareshop.hardware_shop_management_system.repository.CounterRepository;
import com.hardwareshop.hardware_shop_management_system.repository.ProductGroupRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SalespersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalespersonService {

    private final SalespersonRepository salespersonRepository;
    private final ProductGroupRepository productGroupRepository;
    private final CounterRepository counterRepository;

    public SalespersonService(SalespersonRepository salespersonRepository,
                              ProductGroupRepository productGroupRepository,
                              CounterRepository counterRepository) {
        this.salespersonRepository = salespersonRepository;
        this.productGroupRepository = productGroupRepository;
        this.counterRepository = counterRepository;
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

    public List<Salesperson> findByProductGroupName(String name) {
        return salespersonRepository.findByProductGroupName(name);
    }

    public String addProductGroup(UUID salespersonId, UUID productGroupId) {
        Optional<Salesperson> spOpt = salespersonRepository.findById(salespersonId);
        if (spOpt.isEmpty()) return "The salesperson with that id does not exist";
        Optional<ProductGroup> pgOpt = productGroupRepository.findById(productGroupId);
        if (pgOpt.isEmpty()) return "That product group does not exist";
        Salesperson sp = spOpt.get();
        ProductGroup pg = pgOpt.get();
        if (sp.getProductGroups().contains(pg)) return "The salesperson already has that product group";
        sp.getProductGroups().add(pg);
        salespersonRepository.save(sp);
        return "Product group added to the salesperson";
    }

    public String changeCounter(UUID salespersonId, int counterNumber) {
        Optional<Salesperson> spOpt = salespersonRepository.findById(salespersonId);
        if (spOpt.isEmpty()) return "The salesperson with that id does not exist";
        Optional<Counter> counterOpt = counterRepository.findByCounterNumber(counterNumber);
        if (counterOpt.isEmpty()) return "The counter with that counterNumber does not exist";
        Counter counter = counterOpt.get();
        Salesperson sp = spOpt.get();
        if (salespersonRepository.existsByCounter(counter) && !counter.equals(sp.getCounter()))
            return "That counter is taken by another salesperson";
        sp.setCounter(counter);
        salespersonRepository.save(sp);
        return "Counter changed successfully";
    }

    public String delete(UUID id) {
        if (!salespersonRepository.existsById(id)) return "The salesperson with that id does not exist";
        salespersonRepository.deleteById(id);
        return "Salesperson deleted successfully";
    }
}
