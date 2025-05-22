package com.nnk.springboot.domain;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import jakarta.persistence.*;

import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer curveId;
    @Column
    private Timestamp asOfDate;

    @Positive(message = "Term quantity must be positive")
    @NotNull(message = "Term is required")

    @Column
    private Double term;

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be positive")
    @Column
    private Double value;

    @Column
    private Timestamp creationDate;

    public CurvePoint(int i, double d, double e) {
        this.value = e;
        this.curveId = i;
        this.term = d;
    }

    public CurvePoint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
