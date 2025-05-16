package com.nnk.springboot.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    private HomeController homeController;

    @BeforeEach
    void setUp() {
        homeController = new HomeController();
    }

    @Test
    void home_ShouldReturnHomeView() {
        Model model = mock(Model.class);
        String view = homeController.home(model);
        assertEquals("home", view);
    }

    @Test
    void adminHome_ShouldRedirectToBidList() {
        Model model = mock(Model.class);
        String view = homeController.adminHome(model);
        assertEquals("redirect:/bidList/list", view);
    }
}
