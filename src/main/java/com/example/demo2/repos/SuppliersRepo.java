package com.example.demo2.repos;

import com.example.demo2.pojos.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SuppliersRepo extends CrudRepository<Suppliers, Long>{

}
