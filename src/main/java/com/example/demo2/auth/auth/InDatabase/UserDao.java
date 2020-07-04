package com.example.demo2.auth.auth.InDatabase;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<DAOUser, Long> {
    DAOUser findByUsername(String username);
    Optional<DAOUser> findById(Long id);
}
