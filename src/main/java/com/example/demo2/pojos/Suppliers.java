package com.example.demo2.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String region;
    private String addressactual;
    private String addresslegal;
    private String lastinfo;
    private String additionalinfo;
    private Timestamp date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "suppliers_fil",
        joinColumns = {@JoinColumn(referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Fil> fil;

//    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "suppliers_contacts",
        joinColumns = {@JoinColumn(referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Contacts> contacts;

    public Suppliers() {
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


    public Set<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Set<Fil> getFil() {
        return fil;
    }

    public void setFil(Set<Fil> fil) {
        this.fil = fil;
    }

}
