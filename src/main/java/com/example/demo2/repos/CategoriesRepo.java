package com.example.demo2.repos;

import com.example.demo2.pojos.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepo extends CrudRepository<Category, Long> {
    @Override
    Iterable<Category> findAll();
}
