package com.example.demo2.repos;

import com.example.demo2.pojos.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentsRepo extends CrudRepository<Comments, Long> {
    @Override
    Optional<Comments> findById(Long aLong);

    @Override
    Iterable<Comments> findAll();
}
