package com.example.testtask.factory.impl;

import com.example.testtask.dto.UserDto;
import com.example.testtask.model.User;
import com.example.testtask.factory.UserFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryImpl implements UserFactory {

    @Override
    public User getUserFromDto(UserDto userDto) {
        return new User(userDto.getName(), Integer.parseInt(userDto.getAge()));
    }
}
