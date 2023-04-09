package com.example.testtask.repository;

import com.example.testtask.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByAgeGreaterThan(int age);

    Optional<User> findByName(String name);
}
