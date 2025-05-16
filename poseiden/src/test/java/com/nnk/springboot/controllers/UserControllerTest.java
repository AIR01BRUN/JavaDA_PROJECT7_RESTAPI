package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserRepository userRepository;
    private UserController userController;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
        user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
    }

    @Test
    void home_ShouldReturnListViewWithUsers() {
        List<User> users = Arrays.asList(user);
        Model model = mock(Model.class);
        when(userRepository.findAll()).thenReturn(users);

        String view = userController.home(model);

        assertEquals("user/list", view);
        verify(model).addAttribute("users", users);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = userController.showAddForm(new User());
        assertEquals("user/add", view);
    }

    @Test
    void showUpdateForm_ShouldReturnUpdateViewWithUser() {
        Model model = mock(Model.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        String view = userController.showUpdateForm(1, model);

        assertEquals("user/update", view);
        verify(model).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void deleteUser_ShouldRedirectToList() {
        Model model = mock(Model.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        String view = userController.deleteUser(1, model);

        assertEquals("redirect:/user/list", view);
        verify(userRepository).delete(user);
        verify(model, atLeastOnce()).addAttribute(eq("users"), any());
    }

    @Test
    void validate_WithValidUser_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);

        String view = userController.validate(user, result, model);

        assertEquals("redirect:/user/list", view);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void validate_WithInvalidUser_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = userController.validate(user, result, model);

        assertEquals("redirect:/user/add", view);
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUser_WithValidUser_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);

        String view = userController.updateUser(1, user, result, model);

        assertEquals("redirect:/user/list", view);
        verify(userRepository).save(any(User.class));
        verify(model, atLeastOnce()).addAttribute(eq("users"), any());
    }

    @Test
    void updateUser_WithInvalidUser_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = userController.updateUser(1, user, result, model);

        assertEquals("user/update", view);
        verify(userRepository, never()).save(any());
    }
}
