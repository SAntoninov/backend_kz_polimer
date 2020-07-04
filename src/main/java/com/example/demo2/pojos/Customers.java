package com.example.demo2.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone; // возможно пока придётся хранить в строке массив контактов, через разделитель
    private String email;
    private String region;
    private String addressactual;
    private String addresslegal;
    private String lastinfo;
    private String additionalinfo;
    private Timestamp date;
    private String files;


////    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_contacts",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Contacts> contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_products_connect",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<CustomerProducts> customerProducts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comments_customers",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Comments> comments;

    public Customers() {
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddressactual() {
        return addressactual;
    }

    public void setAddressactual(String addressactual) {
        this.addressactual = addressactual;
    }

    public String getAddresslegal() {
        return addresslegal;
    }

    public void setAddresslegal(String addresslegal) {
        this.addresslegal = addresslegal;
    }

    public String getLastinfo() {
        return lastinfo;
    }

    public void setLastinfo(String lastinfo) {
        this.lastinfo = lastinfo;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Set<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Set<CustomerProducts> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(Set<CustomerProducts> customerProducts) {
        this.customerProducts = customerProducts;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }
}
