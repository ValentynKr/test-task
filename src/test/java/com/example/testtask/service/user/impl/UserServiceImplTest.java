package com.example.testtask.service.user.impl;

import com.example.testtask.model.User;
import com.example.testtask.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String EXAMPLE_USER_NAME = "User1";
    private static final int EXAMPLE_AGE = 18;
    private static final long EXAMPLE_ID = 1L;
    private static final String EXCEPTION_MESSAGE = "User with id=1 not found in database";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldFindUserByIdCorrectly() {
        User expectedUser = new User(EXAMPLE_USER_NAME, EXAMPLE_AGE);
        when(userRepository.findById(EXAMPLE_ID)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findById(EXAMPLE_ID);

        assertEquals(actualUser, expectedUser);
    }

    @Test
    void shouldThrowNotFoundEntityException() {
        when(userRepository.findById(EXAMPLE_ID)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(EXAMPLE_ID);
        });

        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }
}