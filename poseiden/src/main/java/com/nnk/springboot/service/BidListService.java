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

    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    public BidList getBidById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList addBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    public BidList updateBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    public void deleteBid(Integer id) {
        bidListRepository.deleteById(id);
    }

}
