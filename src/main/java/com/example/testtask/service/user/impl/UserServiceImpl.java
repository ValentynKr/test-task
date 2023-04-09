package com.example.testtask.service.user.impl;

import com.example.testtask.constant.Constants;
import com.example.testtask.model.User;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        Iterable<User> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format(Constants.USER_NOT_FOUND_BY_ID_MESSAGE, id)));
    }

    @Override
    public List<User> findAllByAgeGreaterThan(int age) {
        return userRepository.findAllByAgeGreaterThan(age);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException(String.format(Constants.USER_NOT_FOUND_BY_NAME_MESSAGE, name)));
    }
}