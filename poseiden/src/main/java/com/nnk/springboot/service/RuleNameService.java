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

    /**
     * Retrieves all RuleName entities from the repository.
     *
     * @return a list of all RuleName objects stored in the database
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Retrieves a RuleName entity by its unique identifier.
     *
     * @param id the unique identifier of the RuleName to retrieve
     * @return the RuleName entity if found, or {@code null} if no entity with the
     *         given id exists
     */
    public RuleName getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new RuleName entry to the repository.
     *
     * @param ruleName the RuleName entity to be added
     * @return the saved RuleName entity
     */
    public RuleName addRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Updates an existing RuleName entry in the repository.
     * If the RuleName does not exist, it will be created.
     *
     * @param ruleName the RuleName entity to update
     * @return the updated RuleName entity
     */
    public RuleName updateRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Deletes a RuleName entry from the repository.
     *
     * @param id the unique identifier of the RuleName to delete
     */
    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
