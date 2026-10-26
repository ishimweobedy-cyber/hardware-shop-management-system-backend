package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.Customer;
import com.hardwareshop.hardware_shop_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String save(Customer customer) {
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }

    public String update(UUID id, Customer updated) {
        Optional<Customer> existing = customerRepository.findById(id);
        if (existing.isEmpty()) return "The customer with that id does not exist";
        Customer customer = existing.get();
        customer.setFirstName(updated.getFirstName());
        customer.setLastName(updated.getLastName());
        customer.setPhone(updated.getPhone());
        customer.setEmail(updated.getEmail());
        customerRepository.save(customer);
        return "Customer updated successfully";
    }

    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public String delete(UUID id) {
        if (!customerRepository.existsById(id)) return "The customer with that id does not exist";
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}
