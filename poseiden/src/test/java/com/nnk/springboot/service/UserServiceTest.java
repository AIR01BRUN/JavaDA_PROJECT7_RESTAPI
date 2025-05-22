package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class
 */
class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    /**
     * Test that addUser saves the user with an encoded password
     */
    @Test
    void AddUser() {

        User user = new User();
        user.setPassword("password123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);

        verify(userRepository).save(any(User.class));
        assertNotEquals("password123", savedUser.getPassword());
    }

    /**
     * Test that getAllUsers returns all users from repository
     */
    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    /**
     * Test that getUserById returns the user when found
     */
    @Test
    void getUserById() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertEquals(user, result);
        verify(userRepository).findById(1);
    }

    /**
     * Test that getUserById returns null when user is not found
     */
    @Test
    void getUserById_Not() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getUserById(1);

        assertNull(result);
        verify(userRepository).findById(1);
    }

    /**
     * Test that updateUser saves the user with an encoded password
     */
    @Test
    void updateUser() {
        User user = new User();
        user.setPassword("newPassword");

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setPassword("encodedPassword");

        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(user);

        verify(userRepository).save(any(User.class));
        assertNotEquals("newPassword", result.getPassword());
        assertEquals(updatedUser, result);
    }

    /**
     * Test that deleteUser deletes the user by ID
     */
    @Test
    void deleteUser() {
        userService.deleteUser(1);
        verify(userRepository).deleteById(1);
    }
}