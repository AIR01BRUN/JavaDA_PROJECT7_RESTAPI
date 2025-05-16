package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BidListControllerTest {

    private BidListService bidListService;
    private BidListController bidListController;
    private BidList bidList;

    @BeforeEach
    void setUp() {
        bidListService = mock(BidListService.class);
        bidListController = new BidListController(bidListService);
        bidList = new BidList();
        bidList.setBid(1d);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(10.0);
    }

    @Test
    void showListForm_ShouldReturnListViewWithBids() {
        List<BidList> bids = Arrays.asList(bidList);
        Model model = mock(Model.class);
        when(bidListService.getAllBids()).thenReturn(bids);

        String view = bidListController.showListForm(model);

        assertEquals("bidList/list", view);
        verify(model).addAttribute("bidLists", bids);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = bidListController.showAddForm(new BidList());
        assertEquals("bidList/add", view);
    }

    @Test
    void validate_WithValidBid_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(bidListService.addBid(any(BidList.class))).thenReturn(bidList);
        Model model = mock(Model.class);

        String view = bidListController.validate(bidList, result, model);

        assertEquals("redirect:/bidList/list", view);
        verify(bidListService).addBid(bidList);
    }

    @Test
    void validate_WithInvalidBid_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = bidListController.validate(bidList, result, model);

        assertEquals("bidList/add", view);
        verify(bidListService, never()).addBid(any());
    }

    @Test
    void updateBid_WithValidBid_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(bidListService.updateBid(any(BidList.class))).thenReturn(bidList);
        Model model = mock(Model.class);

        String view = bidListController.updateBid(1, bidList, result, model);

        assertEquals("redirect:/bidList/list", view);
        verify(bidListService).updateBid(bidList);
    }

    @Test
    void updateBid_WithInvalidBid_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = bidListController.updateBid(1, bidList, result, model);

        assertEquals("bidList/update", view);
        verify(bidListService, never()).updateBid(any());
    }

    @Test
    void deleteBid_ShouldRedirectToList() {
        Model model = mock(Model.class);

        String view = bidListController.deleteBid(1, model);

        assertEquals("redirect:/bidList/list", view);
        verify(bidListService).deleteBid(1);
    }
}