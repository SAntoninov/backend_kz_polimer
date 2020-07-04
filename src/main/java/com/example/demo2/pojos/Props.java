package com.example.demo2.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "props")
public class Props {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String valuedoc;
    private String valuefact;

    @JsonBackReference(value = "physicalProperties")
    @ManyToMany(mappedBy = "props")
    private Set<PhysicalProperties> physicalProperties;


    public Props() {
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

    public String getValuedoc() {
        return valuedoc;
    }

    public void setValuedoc(String valuedoc) {
        this.valuedoc = valuedoc;
    }

    public String getValuefact() {
        return valuefact;
    }

    public void setValuefact(String valuefact) {
        this.valuefact = valuefact;
    }

    public Set<PhysicalProperties> getPhysicalProperties() {
        return physicalProperties;
    }

    public void setPhysicalProperties(Set<PhysicalProperties> physicalProperties) {
        this.physicalProperties = physicalProperties;
    }
}
