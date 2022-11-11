package ru.kpfu.itis.dariagazkaeva.models;

import java.util.Objects;

public class CashSaving {
    private Long id;
    private String name;
    private Long authorId;
    private Float sum;

    public CashSaving(Long id, String name, Long authorId, Float sum) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.sum = sum;
    }

    public CashSaving(String name, Long authorId, Float sum) {
        this.name = name;
        this.authorId = authorId;
        this.sum = sum;
    }

    public CashSaving(Long id, String name, Float sum) {
        this.id = id;
        this.name = name;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "CashSaving{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashSaving that = (CashSaving) o;
        return name.equals(that.name) && authorId.equals(that.authorId) && sum.equals(that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authorId, sum);
    }
}
