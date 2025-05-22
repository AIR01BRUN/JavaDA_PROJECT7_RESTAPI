package com.nnk.springboot.domain;

import javax.validation.constraints.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 50, message = "MoodysRating cannot be blank")
    @Column
    private String moodysRating;
    @Size(min = 1, max = 50, message = "SandPRating cannot be blank")
    @Column
    private String sandPrating;
    @Size(min = 1, max = 50, message = "DitchRating; cannot be blank")
    @Column
    private String fitchRating;
    @NotNull(message = "Order is required")
    @Positive(message = "Order must be positive")
    @Column
    private Integer orderNumber;

    public Rating(String string, String string2, String string3, int i) {
        this.moodysRating = string;
        this.sandPrating = string2;
        this.fitchRating = string3;
        this.orderNumber = i;
    }

    public Rating() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPrating() {
        return sandPrating;
    }

    public void setSandPrating(String sandPrating) {
        this.sandPrating = sandPrating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
