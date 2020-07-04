package com.example.demo2.repos;

import com.example.demo2.pojos.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepo extends JpaRepository<Customers, Long> {
}
