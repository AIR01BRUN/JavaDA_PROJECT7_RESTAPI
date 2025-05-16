package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingControllerTest {

    private RatingService ratingService;
    private RatingController ratingController;
    private Rating rating;

    @BeforeEach
    void setUp() {
        ratingService = mock(RatingService.class);
        ratingController = new RatingController(ratingService);
        rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Aaa");
    }

    @Test
    void showListForm_ShouldReturnListViewWithRatings() {
        List<Rating> ratings = Arrays.asList(rating);
        Model model = mock(Model.class);
        when(ratingService.getAllRatings()).thenReturn(ratings);

        String view = ratingController.showListForm(model);

        assertEquals("rating/list", view);
        verify(model).addAttribute("ratings", ratings);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = ratingController.showAddForm(new Rating());
        assertEquals("rating/add", view);
    }

    @Test
    void showUpdateForm_ShouldReturnUpdateViewWithRating() {
        Model model = mock(Model.class);
        when(ratingService.getRatingById(1)).thenReturn(rating);

        String view = ratingController.showUpdateForm(1, model);

        assertEquals("rating/update", view);
        verify(model).addAttribute("rating", rating);
    }

    @Test
    void validate_WithValidRating_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ratingService.addRating(any(Rating.class))).thenReturn(rating);
        Model model = mock(Model.class);

        String view = ratingController.validate(rating, result, model);

        assertEquals("redirect:/rating/list", view);
        verify(ratingService).addRating(rating);
    }

    @Test
    void validate_WithInvalidRating_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = ratingController.validate(rating, result, model);

        assertEquals("rating/add", view);
        verify(ratingService, never()).addRating(any());
    }

    @Test
    void updateRating_WithValidRating_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ratingService.updateRating(any(Rating.class))).thenReturn(rating);
        Model model = mock(Model.class);

        String view = ratingController.updateRating(1, rating, result, model);

        assertEquals("redirect:/rating/list", view);
        verify(ratingService).updateRating(rating);
    }

    @Test
    void updateRating_WithInvalidRating_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = ratingController.updateRating(1, rating, result, model);

        assertEquals("rating/update", view);
        verify(ratingService, never()).updateRating(any());
    }

    @Test
    void deleteRating_ShouldRedirectToList() {
        Model model = mock(Model.class);

        String view = ratingController.deleteRating(1, model);

        assertEquals("redirect:/rating/list", view);
        verify(ratingService).deleteRating(1);
    }
}
