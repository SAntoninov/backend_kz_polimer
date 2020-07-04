package com.example.demo2.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp time;
    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @JsonBackReference(value = "customers")
    @ManyToMany(mappedBy = "comments")
    private Set<Customers> customers;

    public Comments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

    //    public Suppliers getSuppliers() {
//        return suppliers;
//    }
//
//    public void setSuppliers(Suppliers suppliers) {
//        this.suppliers = suppliers;
//    }
//
//    public Customers getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(Customers customers) {
//        this.customers = customers;
//    }

//    @Override
//    public String toString() {
//        return "Comments{" +
//                "id=" + id +
//                ", time=" + time +
//                ", message='" + message + '\'' +
//                ", user=" + user +
//                ", suppliers=" + suppliers +
//                ", customers=" + customers +
//                '}';
//    }
}
