package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeControllerTest {

    private TradeService tradeService;
    private TradeController tradeController;
    private Trade trade;

    @BeforeEach
    void setUp() {
        tradeService = mock(TradeService.class);
        tradeController = new TradeController(tradeService);
        trade = new Trade();
        trade.setId(1);
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(10.0);
    }

    @Test
    void showListForm_ShouldReturnListViewWithTrades() {
        List<Trade> trades = Arrays.asList(trade);
        Model model = mock(Model.class);
        when(tradeService.getAllTrades()).thenReturn(trades);

        String view = tradeController.showListForm(model);

        assertEquals("trade/list", view);
        verify(model).addAttribute("trades", trades);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = tradeController.showAddForm(new Trade());
        assertEquals("trade/add", view);
    }

    @Test
    void validate_WithValidTrade_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(tradeService.addTrade(any(Trade.class))).thenReturn(trade);
        Model model = mock(Model.class);

        String view = tradeController.validate(trade, result, model);

        assertEquals("redirect:/trade/list", view);
        verify(tradeService).addTrade(trade);
    }

    @Test
    void validate_WithInvalidTrade_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = tradeController.validate(trade, result, model);

        assertEquals("trade/add", view);
        verify(tradeService, never()).addTrade(any());
    }

    @Test
    void updateTrade_WithValidTrade_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(tradeService.updateTrade(any(Trade.class))).thenReturn(trade);
        Model model = mock(Model.class);

        String view = tradeController.updateTrade(1, trade, result, model);

        assertEquals("redirect:/trade/list", view);
        verify(tradeService).updateTrade(trade);
    }

    @Test
    void updateTrade_WithInvalidTrade_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = tradeController.updateTrade(1, trade, result, model);

        assertEquals("trade/update", view);
        verify(tradeService, never()).updateTrade(any());
    }

    @Test
    void deleteTrade_ShouldRedirectToList() {
        Model model = mock(Model.class);

        String view = tradeController.deleteTrade(1, model);

        assertEquals("redirect:/trade/list", view);
        verify(tradeService).deleteTrade(1);
    }
}
