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

    /**
     * Retrieves all CurvePoint entities from the repository.
     *
     * @return a list of all CurvePoint objects stored in the database
     */
    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    /**
     * Retrieves a CurvePoint entity by its unique identifier.
     *
     * @param id the unique identifier of the CurvePoint to retrieve
     * @return the CurvePoint entity if found, or {@code null} if no entity with the
     *         given id exists
     */
    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new CurvePoint entry to the repository.
     *
     * @param curvePoint the CurvePoint entity to be added
     * @return the saved CurvePoint entity
     */
    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Updates an existing CurvePoint entry in the repository.
     * If the CurvePoint does not exist, it will be created.
     *
     * @param curvePoint the CurvePoint entity to update
     * @return the updated CurvePoint entity
     */
    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Deletes a CurvePoint entry from the repository.
     *
     * @param id the unique identifier of the CurvePoint to delete
     */
    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
