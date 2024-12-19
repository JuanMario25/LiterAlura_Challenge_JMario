package com.literalura.jmario.booksapp.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")

public class BookClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Language languages;

    private Integer download_count;

    @OneToOne
    @JoinColumn(name = "author_id")
    private AuthorData author;

    public BookClass(){}

    public BookClass(BookRecord book){
        this.title = book.title();
        this.languages = Language.fromString(book.languages().get(0));
        this.download_count = book.download_count();

        List <AuthorData> authorData = book.authors().
                stream()
                .map(AuthorData::new)
                .toList();
        this.author = authorData.get(0);



    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public AuthorData getAuthor() {
        return author;
    }

    public void setAuthor(AuthorData author) {
       this.author = author;
    }

    @Override
    public String toString() {
        return "BookClass{" +
                "title='" + title + '\'' +
                ", languages=" + languages +
                ", download_count=" + download_count +
                ", author=" + author +
                '}';
    }
}
