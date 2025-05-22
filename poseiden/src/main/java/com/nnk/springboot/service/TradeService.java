package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Retrieves all Trade entities from the repository.
     *
     * @return a list of all Trade objects stored in the database
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Retrieves a Trade entity by its unique identifier.
     *
     * @param id the unique identifier of the Trade to retrieve
     * @return the Trade entity if found, or {@code null} if no entity with the
     *         given id exists
     */
    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new Trade entry to the repository.
     *
     * @param trade the Trade entity to be added
     * @return the saved Trade entity
     */
    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Updates an existing Trade entry in the repository.
     * If the Trade does not exist, it will be created.
     *
     * @param trade the Trade entity to update
     * @return the updated Trade entity
     */
    public Trade updateTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Deletes a Trade entry from the repository.
     *
     * @param id the unique identifier of the Trade to delete
     */
    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
