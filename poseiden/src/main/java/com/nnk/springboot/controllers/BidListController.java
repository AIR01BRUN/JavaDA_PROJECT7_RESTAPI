package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;

import com.nnk.springboot.service.BidListService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
public class BidListController {

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @GetMapping("/bidList/list")
    public String showListForm(Model model) {
        List<BidList> bidLists = bidListService.getAllBids();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";

    }

    @GetMapping("/bidList/add")
    public String showAddForm(BidList bid) {
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.getBidById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "bidList/add";
        }
        bidListService.addBid(bid);
        return "redirect:/bidList/list";
    }

    @PutMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
            BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "bidList/update";
        }
        bidListService.updateBid(bidList);
        return "redirect:/bidList/list";
    }

    @DeleteMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBid(id);
        return "redirect:/bidList/list";
    }
}
