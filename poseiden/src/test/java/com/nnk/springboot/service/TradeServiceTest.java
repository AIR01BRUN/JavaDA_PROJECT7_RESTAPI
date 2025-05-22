package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the TradeService class
 */
class TradeServiceTest {

    private TradeRepository tradeRepository;
    private TradeService tradeService;

    @BeforeEach
    void setUp() {
        tradeRepository = mock(TradeRepository.class);
        tradeService = new TradeService(tradeRepository);
    }

    /**
     * Test that getAllTrades returns all trades from repository
     */
    @Test
    void getAllTrades() {
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade1, trade2));

        List<Trade> trades = tradeService.getAllTrades();

        assertEquals(2, trades.size());
        verify(tradeRepository).findAll();
    }

    /**
     * Test that getTradeById returns the trade when found
     */
    @Test
    void getTradeById() {
        Trade trade = new Trade();
        trade.setId(1);
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.getTradeById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(tradeRepository).findById(1);
    }

    /**
     * Test that getTradeById returns null when trade is not found
     */
    @Test
    void getTradeById_NotFound() {
        when(tradeRepository.findById(99)).thenReturn(Optional.empty());

        Trade result = tradeService.getTradeById(99);

        assertNull(result);
        verify(tradeRepository).findById(99);
    }

    /**
     * Test that addTrade saves and returns the trade
     */
    @Test
    void addTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.addTrade(trade);

        assertEquals(trade, result);
        verify(tradeRepository).save(trade);
    }

    /**
     * Test that updateTrade saves and returns the updated trade
     */
    @Test
    void updateTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.updateTrade(trade);

        assertEquals(trade, result);
        verify(tradeRepository).save(trade);
    }

    /**
     * Test that deleteTrade deletes the trade by ID
     */
    @Test
    void deleteTrade() {
        tradeService.deleteTrade(5);

        verify(tradeRepository).deleteById(5);
    }
}