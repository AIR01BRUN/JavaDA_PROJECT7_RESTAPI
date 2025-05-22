package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller handling all User related operations.
 * Provides endpoints for CRUD operations on User entities.
 */
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the list of all users.
     *
     * @param model the Spring MVC model
     * @return the view name for displaying the users list
     */
    @GetMapping("/user/list")
    public String home(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * Displays the form for adding a new user.
     *
     * @param user the User object to be populated
     * @return the view name for the add form
     */
    @GetMapping("/user/add")
    public String showAddForm(User user) {
        return "user/add";
    }

    /**
     * Displays the form for updating an existing user.
     *
     * @param id    the ID of the user to update
     * @param model the Spring MVC model
     * @return the view name for the update form
     * @throws IllegalArgumentException if user is not found
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Deletes a user.
     *
     * @param id    the ID of the user to delete
     * @param model the Spring MVC model
     * @return redirect to the users list
     */
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }

    /**
     * Processes the submission of a new user.
     *
     * @param user   the User object from the form
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.addUser(user);
        return "redirect:/user/list";
    }

    /**
     * Processes the update of an existing user.
     *
     * @param id     the ID of the user to update
     * @param user   the updated User object
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PutMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }
}
