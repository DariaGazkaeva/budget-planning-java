package ru.kpfu.itis.dariagazkaeva.models;

import java.util.Date;

public class MoneyOperation {

    private Long id;
    private Long authorId;
    private Float sum;
    private String date;
    private Long categoryId;

    private Boolean income;

    private String description;

    public MoneyOperation(Long id, Long authorId, Float sum, String date, Long categoryId, Boolean income, String description) {
        this.id = id;
        this.authorId = authorId;
        this.sum = sum;
        this.date = date;
        this.categoryId = categoryId;
        this.income = income;
        this.description = description;
    }

    public MoneyOperation(Long authorId, Float sum, String date, Long categoryId, Boolean income, String description) {
        this.authorId = authorId;
        this.sum = sum;
        this.date = date;
        this.categoryId = categoryId;
        this.income = income;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MoneyOperation(Long authorId, Float sum, String date, Long categoryId, Boolean income) {
        this.authorId = authorId;
        this.sum = sum;
        this.date = date;
        this.categoryId = categoryId;
        this.income = income;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

