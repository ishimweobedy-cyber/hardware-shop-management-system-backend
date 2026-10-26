package com.hardwareshop.hardware_shop_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_group")
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "productGroups")
    @JsonIgnore
    private List<Salesperson> salespersons;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Salesperson> getSalespersons() { return salespersons; }
    public void setSalespersons(List<Salesperson> salespersons) { this.salespersons = salespersons; }
}
