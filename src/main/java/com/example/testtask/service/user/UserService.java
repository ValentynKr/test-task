package com.example.testtask.service.user;

import com.example.testtask.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findById(Long id);

    List<User> findAllByAgeGreaterThan(int age);

    User findByName(String name);

}