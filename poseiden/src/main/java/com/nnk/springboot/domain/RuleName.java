package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rulename")
public class RuleName {

    /**
     * Unique identifier for the rule
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the rule
     */
    @Size(min = 1, max = 50, message = "Name cannot be blank")
    @Column
    private String name;

    /**
     * Description of the rule
     */
    @Size(min = 1, max = 50, message = "Description cannot be blank")
    @Column
    private String description;
    @Size(min = 1, max = 50, message = "DitchRating; cannot be blank")
    @Column
    private String json;
    @Size(min = 1, max = 50, message = "DitchRating; cannot be blank")
    @Column
    private String template;
    @Size(min = 1, max = 50, message = "DitchRating; cannot be blank")
    @Column
    private String sqlStr;
    @Size(min = 1, max = 50, message = "DitchRating; cannot be blank")
    @Column
    private String sqlPart;

    public RuleName(String string, String string2, String string3, String string4, String string5, String string6) {
        this.name = string;
        this.description = string2;
        this.json = string3;
        this.template = string4;
        this.sqlStr = string5;
        this.sqlPart = string6;
    }

    public RuleName() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
