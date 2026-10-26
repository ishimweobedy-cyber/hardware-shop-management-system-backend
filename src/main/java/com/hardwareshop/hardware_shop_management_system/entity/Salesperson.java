package com.hardwareshop.hardware_shop_management_system.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "salesperson")
public class Salesperson {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "counter_id", nullable = true)
    private Counter counter;

    @ManyToMany
    @JoinTable(
        name = "salesperson_product_group",
        joinColumns = @JoinColumn(name = "salespersons_id"),
        inverseJoinColumns = @JoinColumn(name = "product_groups_id")
    )
    private List<ProductGroup> productGroups;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Counter getCounter() { return counter; }
    public void setCounter(Counter counter) { this.counter = counter; }

    public List<ProductGroup> getProductGroups() { return productGroups; }
    public void setProductGroups(List<ProductGroup> productGroups) { this.productGroups = productGroups; }
}
