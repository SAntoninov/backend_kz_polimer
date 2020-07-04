package com.example.demo2.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortname;
    private String fullname;

    @JsonBackReference(value = "users")
    @ManyToMany(mappedBy = "categories")
    private Set<User> user; // в общем поменял с users на user здесь и в таблице тоже и все ок стало
    public Category() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Math.toIntExact(id);
        hash = 31 * hash + (fullname == null ? 0 : fullname.hashCode());
        hash = 31 * hash + (shortname == null ? 0 : shortname.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Category category = (Category) o;

        return id == category.id
                && (fullname.equals(category.fullname)
                && shortname.equals(category.shortname));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortname;
    }

    public void setShortName(String shortName) {
        this.shortname = shortName;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
