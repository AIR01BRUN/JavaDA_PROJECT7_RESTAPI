package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public RuleName getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    public RuleName addRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName updateRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
