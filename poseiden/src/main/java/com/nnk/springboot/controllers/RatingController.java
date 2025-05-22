package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

/**
 * Controller handling all Rating related operations.
 * Provides endpoints for CRUD operations on Rating entities.
 */
@Controller
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Displays the list of all ratings.
     *
     * @param model the Spring MVC model
     * @return the view name for displaying the ratings list
     */
    @GetMapping("/rating/list")
    public String showListForm(Model model) {
        model.addAttribute("ratings", ratingService.getAllRatings());
        return "rating/list";
    }

    /**
     * Displays the form for adding a new rating.
     *
     * @param rating the Rating object to be populated
     * @return the view name for the add form
     */
    @GetMapping("/rating/add")
    public String showAddForm(Rating rating) {

        return "rating/add";
    }

    /**
     * Displays the form for updating an existing rating.
     *
     * @param id    the ID of the rating to update
     * @param model the Spring MVC model
     * @return the view name for the update form
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/update";
    }

    /**
     * Processes the submission of a new rating.
     *
     * @param rating the Rating object from the form
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "rating/add";
        }
        ratingService.addRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Processes the update of an existing rating.
     *
     * @param id     the ID of the rating to update
     * @param rating the updated Rating object
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PutMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
            BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "rating/update";
        }
        ratingService.updateRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Deletes a rating.
     *
     * @param id    the ID of the rating to delete
     * @param model the Spring MVC model
     * @return redirect to the ratings list
     */
    @DeleteMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
