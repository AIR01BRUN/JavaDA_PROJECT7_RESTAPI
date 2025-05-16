package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuleNameControllerTest {

    private RuleNameService ruleNameService;
    private RuleNameController ruleNameController;
    private RuleName ruleName;

    @BeforeEach
    void setUp() {
        ruleNameService = mock(RuleNameService.class);
        ruleNameController = new RuleNameController(ruleNameService);
        ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule");
    }

    @Test
    void showListForm_ShouldReturnListViewWithRuleNames() {
        List<RuleName> ruleNames = Arrays.asList(ruleName);
        Model model = mock(Model.class);
        when(ruleNameService.getAllRuleNames()).thenReturn(ruleNames);

        String view = ruleNameController.showListForm(model);

        assertEquals("ruleName/list", view);
        verify(model).addAttribute("ruleNames", ruleNames);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = ruleNameController.showAddForm(new RuleName());
        assertEquals("ruleName/add", view);
    }

    @Test
    void showUpdateForm_ShouldReturnUpdateViewWithRuleName() {
        Model model = mock(Model.class);
        when(ruleNameService.getRuleNameById(1)).thenReturn(ruleName);

        String view = ruleNameController.showUpdateForm(1, model);

        assertEquals("ruleName/update", view);
        verify(model).addAttribute("ruleName", ruleName);
    }

    @Test
    void validate_WithValidRuleName_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ruleNameService.addRuleName(any(RuleName.class))).thenReturn(ruleName);
        Model model = mock(Model.class);

        String view = ruleNameController.validate(ruleName, result, model);

        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService).addRuleName(ruleName);
    }

    @Test
    void validate_WithInvalidRuleName_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = ruleNameController.validate(ruleName, result, model);

        assertEquals("ruleName/add", view);
        verify(ruleNameService, never()).addRuleName(any());
    }

    @Test
    void updateRuleName_WithValidRuleName_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ruleNameService.updateRuleName(any(RuleName.class))).thenReturn(ruleName);
        Model model = mock(Model.class);

        String view = ruleNameController.updateRuleName(1, ruleName, result, model);

        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService).updateRuleName(ruleName);
    }

    @Test
    void updateRuleName_WithInvalidRuleName_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = ruleNameController.updateRuleName(1, ruleName, result, model);

        assertEquals("ruleName/update", view);
        verify(ruleNameService, never()).updateRuleName(any());
    }

    @Test
    void deleteRuleName_ShouldRedirectToList() {
        Model model = mock(Model.class);

        String view = ruleNameController.deleteRuleName(1, model);

        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService).deleteRuleName(1);
    }
}
