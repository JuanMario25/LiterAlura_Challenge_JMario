package com.literalura.jmario.booksapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDataRecord(
        @JsonAlias("name") String author_name,
        Integer birth_year,
        Integer death_year
) {
}
