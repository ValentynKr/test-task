package com.example.testtask.factory;

import com.example.testtask.dto.UserDto;
import com.example.testtask.model.User;

public interface UserFactory {

    User getUserFromDto(UserDto userDto);

}
