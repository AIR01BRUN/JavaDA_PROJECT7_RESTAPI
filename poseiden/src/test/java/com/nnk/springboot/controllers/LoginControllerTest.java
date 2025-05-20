package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private UserRepository userRepository;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        loginController = new LoginController(userRepository);
    }

    @Test
    void login_ShouldReturnLoginView() {
        ModelAndView mav = loginController.login(null, null);
        assertEquals("login", mav.getViewName());
    }

    @Test
    void getAllUserArticles_ShouldReturnUserListViewWithUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());
        ModelAndView mav = loginController.getAllUserArticles();
        assertEquals("user/list", mav.getViewName());
        assertTrue(mav.getModel().containsKey("users"));
    }

    @Test
    void error_ShouldReturn403ViewWithErrorMsg() {
        ModelAndView mav = loginController.error();
        assertEquals("403", mav.getViewName());
        assertTrue(mav.getModel().containsKey("errorMsg"));
    }
}
