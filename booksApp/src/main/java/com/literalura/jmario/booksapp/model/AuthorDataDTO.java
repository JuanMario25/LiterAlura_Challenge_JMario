package com.literalura.jmario.booksapp.model;

import java.util.List;

public class AuthorDataDTO{
    private String author_name;
    private List<String> ids;
    private Integer birth_year;
    private Integer death_year;
    private List<String> book_ids;
    private List<String> book_titles;

    public AuthorDataDTO(String author_name, List<String> ids, Integer birth_year, Integer death_year, List<String> book_ids, List<String> book_titles) {
        this.author_name = author_name;
        this.ids = ids;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.book_ids = book_ids;
        this.book_titles = book_titles;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
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

    public List<String> getBook_ids() {
        return book_ids;
    }

    public void setBook_ids(List<String> book_ids) {
        this.book_ids = book_ids;
    }

    public List<String> getBook_titles() {
        return book_titles;
    }

    public void setBook_titles(List<String> book_titles) {
        this.book_titles = book_titles;
    }

    @Override
    public String toString() {
        return """
           
                author_name = '%s'
                birth_year = %d
                death_year = %d
                book_titles = %s
            """.formatted(
                        author_name,
                        birth_year,
                        death_year,
                        book_titles);
    }
}
