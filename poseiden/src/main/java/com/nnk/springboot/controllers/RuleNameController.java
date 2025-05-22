package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
 * Controller handling all RuleName related operations.
 * Provides endpoints for CRUD operations on RuleName entities.
 */
@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * Displays the list of all rule names.
     *
     * @param model the Spring MVC model
     * @return the view name for displaying the rule names list
     */
    @GetMapping("/ruleName/list")
    public String showListForm(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    /**
     * Displays the form for adding a new rule name.
     *
     * @param bid the RuleName object to be populated
     * @return the view name for the add form
     */
    @GetMapping("/ruleName/add")
    public String showAddForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Displays the form for updating an existing rule name.
     *
     * @param id    the ID of the rule name to update
     * @param model the Spring MVC model
     * @return the view name for the update form
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

    /**
     * Processes the submission of a new rule name.
     *
     * @param ruleName the RuleName object from the form
     * @param result   the binding result for validation
     * @param model    the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "ruleName/add";
        }
        ruleNameService.addRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Processes the update of an existing rule name.
     *
     * @param id       the ID of the rule name to update
     * @param ruleName the updated RuleName object
     * @param result   the binding result for validation
     * @param model    the Spring MVC model
     * @return redirect to list on success, or back to form on error
     */
    @PutMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Deletes a rule name.
     *
     * @param id    the ID of the rule name to delete
     * @param model the Spring MVC model
     * @return redirect to the rule names list
     */
    @DeleteMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
