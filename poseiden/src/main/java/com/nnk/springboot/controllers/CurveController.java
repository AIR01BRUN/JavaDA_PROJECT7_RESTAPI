package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;

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

/**
 * Controller class handling all curve point related operations.
 * Provides endpoints for CRUD operations on CurvePoint entities.
 */
@Controller
public class CurveController {

    private final CurveService curveService;

    /**
     * Constructs a new CurveController with the specified CurveService.
     *
     * @param curveService the service handling curve point business logic
     */
    public CurveController(CurveService curveService) {
        this.curveService = curveService;
    }

    /**
     * Displays the list of all curve points.
     *
     * @param model the model to add attributes to
     * @return the view name for displaying the curve points list
     */
    @GetMapping("/curvePoint/list")
    public String showListForm(Model model) {
        List<CurvePoint> curvePoints = curveService.getAllCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        return "curvePoint/list";
    }

    /**
     * Displays the form for adding a new curve point.
     *
     * @param bid the CurvePoint object to be populated
     * @return the view name for the add form
     */
    @GetMapping("/curvePoint/add")
    public String showAddForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Displays the form for updating an existing curve point.
     *
     * @param id    the id of the CurvePoint to be updated
     * @param model the model to add attributes to
     * @return the view name for the update form
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curvePoint", curveService.getCurvePointById(id));
        return "curvePoint/update";
    }

    /**
     * Validates and saves a new curve point.
     *
     * @param curvePoint the CurvePoint object to be saved
     * @param result     the binding result for validation errors
     * @param model      the model to add attributes to
     * @return the redirect URL or the view name for the add form in case of errors
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "curvePoint/add";
        }
        curveService.addCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Validates and updates an existing curve point.
     *
     * @param id         the id of the CurvePoint to be updated
     * @param curvePoint the updated CurvePoint object
     * @param result     the binding result for validation errors
     * @param model      the model to add attributes to
     * @return the redirect URL or the view name for the update form in case of
     *         errors
     */
    @PutMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
            BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "curvePoint/update";
        }
        curveService.updateCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Deletes a curve point by its id.
     *
     * @param id    the id of the CurvePoint to be deleted
     * @param model the model to add attributes to
     * @return the redirect URL for the curve points list
     */
    @DeleteMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curveService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
