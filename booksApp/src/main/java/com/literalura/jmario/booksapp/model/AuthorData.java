package com.literalura.jmario.booksapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author_name;
    private Integer birth_year;
    private Integer death_year;

    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookClass book;

    public AuthorData(AuthorDataRecord author) {
        this.author_name = author.author_name();
        this.birth_year = author.birth_year();
        this.death_year = author.death_year();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public BookClass getBooks() {
        return book;
    }

    public void setBooks(BookClass book) {
        book.setAuthor(this);
        this.book = book;
    }

    @Override
    public String toString() {
        return "AuthorData{" +
                "death_year=" + death_year +
                ", birth_year=" + birth_year +
                ", author_name='" + author_name + '\'' +
                '}';
    }
}
