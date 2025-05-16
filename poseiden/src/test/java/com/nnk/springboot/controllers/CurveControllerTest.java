package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurveControllerTest {

    private CurveService curveService;
    private CurveController curveController;
    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        curveService = mock(CurveService.class);
        curveController = new CurveController(curveService);
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(20.0);
        curvePoint.setValue(30.0);
    }

    @Test
    void showListForm_ShouldReturnListViewWithCurvePoints() {
        List<CurvePoint> curvePoints = Arrays.asList(curvePoint);
        Model model = mock(Model.class);
        when(curveService.getAllCurvePoints()).thenReturn(curvePoints);

        String view = curveController.showListForm(model);

        assertEquals("curvePoint/list", view);
        verify(model).addAttribute("curvePoints", curvePoints);
    }

    @Test
    void showAddForm_ShouldReturnAddView() {
        String view = curveController.showAddForm(new CurvePoint());
        assertEquals("curvePoint/add", view);
    }

    @Test
    void showUpdateForm_ShouldReturnUpdateViewWithCurvePoint() {
        Model model = mock(Model.class);
        when(curveService.getCurvePointById(1)).thenReturn(curvePoint);

        String view = curveController.showUpdateForm(1, model);

        assertEquals("curvePoint/update", view);
        verify(model).addAttribute("curvePoint", curvePoint);
    }

    @Test
    void validate_WithValidCurvePoint_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(curveService.addCurvePoint(any(CurvePoint.class))).thenReturn(curvePoint);
        Model model = mock(Model.class);

        String view = curveController.validate(curvePoint, result, model);

        assertEquals("redirect:/curvePoint/list", view);
        verify(curveService).addCurvePoint(curvePoint);
    }

    @Test
    void validate_WithInvalidCurvePoint_ShouldReturnAddView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = curveController.validate(curvePoint, result, model);

        assertEquals("curvePoint/add", view);
        verify(curveService, never()).addCurvePoint(any());
    }

    @Test
    void updateBid_WithValidCurvePoint_ShouldRedirectToList() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(curveService.updateCurvePoint(any(CurvePoint.class))).thenReturn(curvePoint);
        Model model = mock(Model.class);

        String view = curveController.updateBid(1, curvePoint, result, model);

        assertEquals("redirect:/curvePoint/list", view);
        verify(curveService).updateCurvePoint(curvePoint);
    }

    @Test
    void updateBid_WithInvalidCurvePoint_ShouldReturnUpdateView() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String view = curveController.updateBid(1, curvePoint, result, model);

        assertEquals("curvePoint/update", view);
        verify(curveService, never()).updateCurvePoint(any());
    }

    @Test
    void deleteBid_ShouldRedirectToList() {
        Model model = mock(Model.class);

        String view = curveController.deleteBid(1, model);

        assertEquals("redirect:/curvePoint/list", view);
        verify(curveService).deleteCurvePoint(1);
    }
}
