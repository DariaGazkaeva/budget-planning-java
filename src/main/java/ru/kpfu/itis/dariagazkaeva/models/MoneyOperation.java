package ru.kpfu.itis.dariagazkaeva.models;

import java.util.Date;

public class MoneyOperation {

    private Long id; //TODO а почему тут лонг с большой?..
    private Long authorId;
    private Float sum;
    private Date date;
    private Long categoryId;

    public MoneyOperation(Long id, Long authorId, Float sum, Date date, Long categoryId) {
        this.id = id;
        this.authorId = authorId;
        this.sum = sum;
        this.date = date;
        this.categoryId = categoryId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

