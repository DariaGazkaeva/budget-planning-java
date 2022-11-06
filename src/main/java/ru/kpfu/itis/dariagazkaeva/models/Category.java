package ru.kpfu.itis.dariagazkaeva.models;

public class Category {
    private Long id;
    private String name;
    private Long authorId;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, Long authorId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
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

    // TODO переопределить икуалс и хэшкод
}
