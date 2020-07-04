package com.example.demo2.repos;

import com.example.demo2.pojos.ApplicationArea;
import com.example.demo2.pojos.Category;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationAreasRepo extends CrudRepository<ApplicationArea, Long> {
    @Override
    Iterable<ApplicationArea> findAll();
}
