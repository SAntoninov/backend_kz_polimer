package com.example.demo2.repos;

import com.example.demo2.pojos.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProdRepo extends CrudRepository<Products, Long> {

//    @Override
//    <S extends Products> S save(S s);
}
