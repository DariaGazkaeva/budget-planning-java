package ru.kpfu.itis.dariagazkaeva.models;

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
}
