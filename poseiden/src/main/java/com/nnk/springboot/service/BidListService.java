package com.nnk.springboot.service;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Retrieves all BidList entities from the repository.
     *
     * @return a list of all BidList objects stored in the database
     */
    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    /**
     * Retrieves a BidList entity by its unique identifier.
     *
     * @param id the unique identifier of the BidList to retrieve
     * @return the BidList entity if found, or {@code null} if no entity with the
     *         given id exists
     */
    public BidList getBidById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new BidList entry to the repository.
     *
     * @param bid the BidList entity to be added
     * @return the saved BidList entity
     */
    public BidList addBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    /**
     * Updates an existing BidList entry in the repository.
     * If the BidList does not exist, it will be created.
     *
     * @param bid the BidList entity to update
     * @return the updated BidList entity
     */
    public BidList updateBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    /**
     * Deletes a BidList entry from the repository.
     *
     * @param id the unique identifier of the BidList to delete
     */
    public void deleteBid(Integer id) {
        bidListRepository.deleteById(id);
    }

}
