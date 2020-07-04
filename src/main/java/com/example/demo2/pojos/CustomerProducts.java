package com.example.demo2.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer_products")
public class CustomerProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String valuebeg;
    private String valueend;

    @JsonBackReference(value = "customers")
    @ManyToMany(mappedBy = "customerProducts")
    private Set<Customers> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValuebeg() {
        return valuebeg;
    }

    public void setValuebeg(String valuebeg) {
        this.valuebeg = valuebeg;
    }

    public String getValueend() {
        return valueend;
    }

    public void setValueend(String valueend) {
        this.valueend = valueend;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }
}
