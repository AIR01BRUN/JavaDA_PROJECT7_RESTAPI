package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RatingService class
 */
class RatingServiceTest {

    private RatingRepository ratingRepository;
    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingRepository = mock(RatingRepository.class);
        ratingService = new RatingService(ratingRepository);
    }

    /**
     * Test that getAllRatings returns all ratings from repository
     */
    @Test
    void getAllRatings_shouldReturnAllRatings() {
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<Rating> ratings = ratingService.getAllRatings();

        assertEquals(2, ratings.size());
        verify(ratingRepository, times(1)).findAll();
    }

    /**
     * Test that getRatingById returns the rating when found
     */
    @Test
    void getRatingById_shouldReturnRating_whenFound() {
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.getRatingById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(ratingRepository, times(1)).findById(1);
    }

    /**
     * Test that getRatingById returns null when rating is not found
     */
    @Test
    void getRatingById_shouldReturnNull_whenNotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        Rating result = ratingService.getRatingById(1);

        assertNull(result);
        verify(ratingRepository, times(1)).findById(1);
    }

    /**
     * Test that addRating saves and returns the rating
     */
    @Test
    void addRating_shouldSaveAndReturnRating() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.addRating(rating);

        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }

    /**
     * Test that updateRating saves and returns the updated rating
     */
    @Test
    void updateRating_shouldSaveAndReturnRating() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.updateRating(rating);

        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }

    /**
     * Test that deleteRating deletes the rating by ID
     */
    @Test
    void deleteRating_shouldCallDeleteById() {
        ratingService.deleteRating(1);

        verify(ratingRepository, times(1)).deleteById(1);
    }
}