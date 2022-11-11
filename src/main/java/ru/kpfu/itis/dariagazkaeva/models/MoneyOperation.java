package ru.kpfu.itis.dariagazkaeva.models;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyOperation that = (MoneyOperation) o;
        return authorId.equals(that.authorId) && sum.equals(that.sum) && date.equals(that.date) && Objects.equals(categoryId, that.categoryId) && income.equals(that.income) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, sum, date, categoryId, income, description);
    }

    @Override
    public String toString() {
        return "MoneyOperation{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", sum=" + sum +
                ", date='" + date + '\'' +
                ", categoryId=" + categoryId +
                ", income=" + income +
                ", description='" + description + '\'' +
                '}';
    }
}

