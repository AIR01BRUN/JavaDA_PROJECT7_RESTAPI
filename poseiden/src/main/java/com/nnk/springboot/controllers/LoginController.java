package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling user authentication and authorization related operations.
 */
@Controller
public class LoginController {

    private final UserRepository userRepository;

    /**
     * Constructs a new LoginController with the specified UserRepository.
     *
     * @param userRepository repository for user data access
     */
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the login page request and displays appropriate messages for login
     * errors or logout.
     *
     * @param error  error parameter indicating login failure
     * @param logout logout parameter indicating successful logout
     * @return ModelAndView containing login page with appropriate messages
     */
    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        System.out.println("LoginController.login");
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "Invalid username or password");
            System.out.println("error");
        }
        if (logout != null) {
            mav.addObject("message", "You have been logged out successfully");
            System.out.println("logout");
        }
        mav.setViewName("login");
        return mav;
    }

    /**
     * Retrieves and displays all user articles.
     *
     * @return ModelAndView containing the list of all users
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Handles unauthorized access attempts.
     *
     * @return ModelAndView containing the 403 error page
     */
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
