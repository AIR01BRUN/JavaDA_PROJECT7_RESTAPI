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

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Trade updateTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
