package ru.kpfu.itis.dariagazkaeva.models;

import java.util.Objects;

public class Category {
    private Long id;
    private String name;
    private Long authorId;
    private Boolean income;

    public Category(Long authorId, String name, Boolean income) {
        this.authorId = authorId;
        this.name = name;
        this.income = income;
    }

    public Category(Long id, Long authorId) {
        this.id = id;
        this.authorId = authorId;
    }

    public Category(Long id, String name, Long authorId, Boolean income) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) && Objects.equals(authorId, category.authorId) && income.equals(category.income);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authorId, income);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", income=" + income +
                '}';
    }
}
