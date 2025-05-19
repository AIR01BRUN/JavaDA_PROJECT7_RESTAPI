package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private User user;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
    }

    @Test
    void home_ShouldReturnListViewWithUsers() {
        List<User> users = Arrays.asList(user);
        Model model = mock(Model.class);
        when(userService.getAllUsers()).thenReturn(users);

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
        when(userService.getUserById(1)).thenReturn(user);

        String view = userController.showUpdateForm(1, model);

        assertEquals("user/update", view);
        verify(model).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void deleteUser_ShouldRedirectToList() {
        Model model = mock(Model.class);
        when(userService.getUserById(1)).thenReturn(user);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        String view = userController.deleteUser(1, model);

        assertEquals("redirect:/user/list", view);
        verify(userService).deleteUser(user.getId());
        verify(model, atLeastOnce()).addAttribute(eq("users"), any());
    }

    @Test
    void validate_WithValidUser_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);

        String view = userController.validate(user, result, model);

        assertEquals("redirect:/user/list", view);
        verify(userService).addUser(user);
    }

    @Test
    void validate_WithInvalidUser_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = userController.validate(user, result, model);

        assertEquals("user/add", view);
        verify(userService, never()).addUser(any());
    }

    @Test
    void updateUser_WithValidUser_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        Model model = mock(Model.class);

        String view = userController.updateUser(1, user, result, model);

        assertEquals("redirect:/user/list", view);
        verify(userService).updateUser(user);
        verify(model, atLeastOnce()).addAttribute(eq("users"), any());
    }

    @Test
    void updateUser_WithInvalidUser_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = userController.updateUser(1, user, result, model);

        assertEquals("user/update", view);
        verify(userService, never()).updateUser(any());
    }
}
