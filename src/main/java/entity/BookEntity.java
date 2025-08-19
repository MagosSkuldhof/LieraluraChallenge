package com.tuusuario.literatura.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private int downloadCount;

    @ManyToOne(cascade = CascadeType.ALL) // Inserta automáticamente el autor si es nuevo
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    public BookEntity() {}

    public BookEntity(String title, String language, int downloadCount, AuthorEntity author) {
        this.title = title;
        this.language = language;
        this.downloadCount = downloadCount;
        this.author = author;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }

    public AuthorEntity getAuthor() { return author; }
    public void setAuthor(AuthorEntity author) { this.author = author; }
}
