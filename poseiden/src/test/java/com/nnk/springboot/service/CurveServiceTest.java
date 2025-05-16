package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CurveServiceTest {

    private CurvePointRepository curvePointRepository;
    private CurveService curveService;

    @BeforeEach
    void setUp() {
        curvePointRepository = mock(CurvePointRepository.class);
        curveService = new CurveService(curvePointRepository);
    }

    @Test
    void testGetAllCurvePoints() {
        CurvePoint cp1 = new CurvePoint();
        CurvePoint cp2 = new CurvePoint();
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(cp1, cp2));

        List<CurvePoint> result = curveService.getAllCurvePoints();

        assertEquals(2, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void testGetCurvePointById_Found() {
        CurvePoint cp = new CurvePoint();
        cp.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));

        CurvePoint result = curveService.getCurvePointById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void testGetCurvePointById_NotFound() {
        when(curvePointRepository.findById(2)).thenReturn(Optional.empty());

        CurvePoint result = curveService.getCurvePointById(2);

        assertNull(result);
        verify(curvePointRepository, times(1)).findById(2);
    }

    @Test
    void testAddCurvePoint() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.save(cp)).thenReturn(cp);

        CurvePoint result = curveService.addCurvePoint(cp);

        assertEquals(cp, result);
        verify(curvePointRepository, times(1)).save(cp);
    }

    @Test
    void testUpdateCurvePoint() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.save(cp)).thenReturn(cp);

        CurvePoint result = curveService.updateCurvePoint(cp);

        assertEquals(cp, result);
        verify(curvePointRepository, times(1)).save(cp);
    }

    @Test
    void testDeleteCurvePoint() {
        curveService.deleteCurvePoint(3);

        verify(curvePointRepository, times(1)).deleteById(3);
    }
}