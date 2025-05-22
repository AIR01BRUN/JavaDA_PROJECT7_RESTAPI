package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the BidListService class
 */
class BidListServiceTest {

    private BidListRepository bidListRepository;
    private BidListService bidListService;

    @BeforeEach
    void setUp() {
        bidListRepository = mock(BidListRepository.class);
        bidListService = new BidListService(bidListRepository);
    }

    /**
     * Test that getAllBids returns all bids from repository
     */
    @Test
    void getAllBids_returnsAllBids() {
        BidList bid1 = new BidList();
        BidList bid2 = new BidList();
        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bid1, bid2));

        List<BidList> result = bidListService.getAllBids();

        assertEquals(2, result.size());
        verify(bidListRepository).findAll();
    }

    /**
     * Test that getBidById returns the bid when found
     */
    @Test
    void getBidById_found_returnsBid() {
        BidList bid = new BidList();
        bid.setBid(1d);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        BidList result = bidListService.getBidById(1);

        assertNotNull(result);
        assertEquals(1, result.getBid());
        verify(bidListRepository).findById(1);
    }

    /**
     * Test that getBidById returns null when bid is not found
     */
    @Test
    void getBidById_notFound_returnsNull() {
        when(bidListRepository.findById(99)).thenReturn(Optional.empty());

        BidList result = bidListService.getBidById(99);

        assertNull(result);
        verify(bidListRepository).findById(99);
    }

    /**
     * Test that addBid saves and returns the bid
     */
    @Test
    void addBid_savesAndReturnsBid() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);

        BidList result = bidListService.addBid(bid);

        assertEquals(bid, result);
        verify(bidListRepository).save(bid);
    }

    /**
     * Test that updateBid saves and returns the updated bid
     */
    @Test
    void updateBid_savesAndReturnsBid() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);

        BidList result = bidListService.updateBid(bid);

        assertEquals(bid, result);
        verify(bidListRepository).save(bid);
    }

    /**
     * Test that deleteBid deletes the bid by ID
     */
    @Test
    void deleteBid_deletesById() {
        bidListService.deleteBid(5);

        verify(bidListRepository).deleteById(5);
    }
}