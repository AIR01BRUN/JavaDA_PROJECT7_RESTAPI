package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurveService {

    private CurvePointRepository curvePointRepository;

    public CurveService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
