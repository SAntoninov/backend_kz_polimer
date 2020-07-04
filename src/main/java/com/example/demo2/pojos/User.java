package com.example.demo2.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private Timestamp birthdate;
    private String email;
    private String phone;
    private String role;

//    @JsonIgnore
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_categories",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Category> categories;

    public User() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Math.toIntExact(id);
        hash = 31 * hash + (fullname == null ? 0 : fullname.hashCode());
        hash = 31 * hash + (email == null ? 0 : email.hashCode());
        hash = 31 * hash + (phone == null ? 0 : phone.hashCode());
        hash = 31 * hash + (password == null ? 0 : password.hashCode());
        hash = 31 * hash + (birthdate == null ? 0 : birthdate.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        User user = (User) o;

        return id == user.id
                && (fullname.equals(user.fullname)
                && phone.equals(user.role)
                && password.equals(user.password)
                && birthdate.equals(user.birthdate)
                && email.equals(user.email));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthdate = birthDate;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}
