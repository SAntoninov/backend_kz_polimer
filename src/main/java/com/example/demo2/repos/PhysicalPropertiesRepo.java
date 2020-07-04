package com.example.demo2.repos;

import com.example.demo2.pojos.PhysicalProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PhysicalPropertiesRepo extends CrudRepository<PhysicalProperties, Long> {
}
