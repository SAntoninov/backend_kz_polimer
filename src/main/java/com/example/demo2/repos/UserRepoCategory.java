package com.example.demo2.repos;

import com.example.demo2.pojos.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepoCategory extends CrudRepository<User, Long> {
    @Override
    Iterable<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String string);
}
