package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.ProductGroup;
import com.hardwareshop.hardware_shop_management_system.repository.ProductGroupRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductGroupService {

    private final ProductGroupRepository productGroupRepository;

    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    public String save(ProductGroup productGroup) {
        productGroupRepository.save(productGroup);
        return "Product group saved successfully";
    }

    public List<ProductGroup> findAll() {
        return productGroupRepository.findAll();
    }

    public Optional<ProductGroup> findById(UUID id) {
        return productGroupRepository.findById(id);
    }

    public String update(UUID id, ProductGroup updated) {
        Optional<ProductGroup> existing = productGroupRepository.findById(id);
        if (existing.isEmpty()) return "The product group with that id does not exist";
        ProductGroup pg = existing.get();
        pg.setName(updated.getName());
        productGroupRepository.save(pg);
        return "Product group updated successfully";
    }

    public String delete(UUID id) {
        if (!productGroupRepository.existsById(id)) return "The product group with that id does not exist";
        productGroupRepository.deleteById(id);
        return "Product group deleted successfully";
    }
}
