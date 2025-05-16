package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RuleNameServiceTest {

    private RuleNameRepository ruleNameRepository;
    private RuleNameService ruleNameService;

    @BeforeEach
    void setUp() {
        ruleNameRepository = mock(RuleNameRepository.class);
        ruleNameService = new RuleNameService(ruleNameRepository);
    }

    @Test
    void testGetAllRuleNames() {
        RuleName rule1 = new RuleName();
        RuleName rule2 = new RuleName();
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(rule1, rule2));

        List<RuleName> result = ruleNameService.getAllRuleNames();

        assertEquals(2, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void testGetRuleNameById_Found() {
        RuleName rule = new RuleName();
        rule.setId(1);
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        RuleName result = ruleNameService.getRuleNameById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void testGetRuleNameById_NotFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        RuleName result = ruleNameService.getRuleNameById(1);

        assertNull(result);
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void testAddRuleName() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        RuleName result = ruleNameService.addRuleName(rule);

        assertEquals(rule, result);
        verify(ruleNameRepository, times(1)).save(rule);
    }

    @Test
    void testUpdateRuleName() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        RuleName result = ruleNameService.updateRuleName(rule);

        assertEquals(rule, result);
        verify(ruleNameRepository, times(1)).save(rule);
    }

    @Test
    void testDeleteRuleName() {
        Integer id = 1;

        ruleNameService.deleteRuleName(id);

        verify(ruleNameRepository, times(1)).deleteById(id);
    }
}