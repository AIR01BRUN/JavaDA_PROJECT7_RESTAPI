package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

/**
 * Controller handling all Trade related operations.
 * Provides endpoints for CRUD operations on Trade entities.
 */
@Controller
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * Displays the list of all trades.
     *
     * @param model the Spring MVC model
     * @return the view name for displaying the trades list
     */
    @GetMapping("/trade/list")
    public String showListForm(Model model) {
        model.addAttribute("trades", tradeService.getAllTrades());
        return "trade/list";
    }

    /**
     * Displays the form for adding a new trade.
     *
     * @param trade the Trade object to be populated
     * @return the view name for the add form
     */
    @GetMapping("/trade/add")
    public String showAddForm(Trade trade) {
        return "trade/add";
    }

    /**
     * Displays the form for updating an existing trade.
     *
     * @param id    the ID of the trade to update
     * @param model the Spring MVC model
     * @return the view name for the update form
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade", tradeService.getTradeById(id));
        return "trade/update";
    }

    /**
     * Processes the submission of a new trade.
     *
     * @param trade  the Trade object from the form
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "trade/add";
        }
        tradeService.addTrade(trade);
        return "redirect:/trade/list";
    }

    /**
     * Processes the update of an existing trade.
     *
     * @param id     the ID of the trade to update
     * @param trade  the updated Trade object
     * @param result the binding result for validation
     * @param model  the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PutMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
            BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "trade/update";
        }
        tradeService.updateTrade(trade);
        return "redirect:/trade/list";
    }

    /**
     * Deletes a trade.
     *
     * @param id    the ID of the trade to delete
     * @param model the Spring MVC model
     * @return redirect to the trades list
     */
    @DeleteMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
