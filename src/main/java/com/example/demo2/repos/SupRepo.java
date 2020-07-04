package com.example.demo2.repos;

import com.example.demo2.pojos.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupRepo extends JpaRepository<Suppliers, Long> {
}
