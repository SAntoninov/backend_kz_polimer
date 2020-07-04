package com.example.demo2.pojos;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fullname;
    private String manufacturer;
    private String characteristicproperties;
    private String applicationarea;
    private String category;
    private String supplier;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_physical_properties",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<PhysicalProperties> physicalProperties;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_fil",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Fil> fil;

    public Products() {
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCharacteristicproperties() {
        return characteristicproperties;
    }

    public void setCharacteristicproperties(String characteristicproperties) {
        this.characteristicproperties = characteristicproperties;
    }

    public String getApplicationarea() {
        return applicationarea;
    }

    public void setApplicationarea(String applicationarea) {
        this.applicationarea = applicationarea;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Set<PhysicalProperties> getPhysicalProperties() {
        return physicalProperties;
    }

    public void setPhysicalProperties(Set<PhysicalProperties> physicalProperties) {
        this.physicalProperties = physicalProperties;
    }

    public Set<Fil> getFil() {
        return fil;
    }

    public void setFil(Set<Fil> fil) {
        this.fil = fil;
    }
}
