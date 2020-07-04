package com.example.demo2.repos;

import com.example.demo2.pojos.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepo extends JpaRepository<Tasks, Long> {

}
