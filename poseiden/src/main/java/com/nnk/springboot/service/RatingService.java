package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Retrieves all Rating entities from the repository.
     *
     * @return a list of all Rating objects stored in the database
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Retrieves a Rating entity by its unique identifier.
     *
     * @param id the unique identifier of the Rating to retrieve
     * @return the Rating entity if found, or {@code null} if no entity with the
     *         given id exists
     */
    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new Rating entry to the repository.
     *
     * @param rating the Rating entity to be added
     * @return the saved Rating entity
     */
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Updates an existing Rating entry in the repository.
     * If the Rating does not exist, it will be created.
     *
     * @param rating the Rating entity to update
     * @return the updated Rating entity
     */
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Deletes a Rating entry from the repository.
     *
     * @param id the unique identifier of the Rating to delete
     */
    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
